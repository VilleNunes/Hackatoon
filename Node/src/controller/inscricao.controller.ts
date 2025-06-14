import { Request, Response, NextFunction } from "express";
import { knex } from "../database/knex";
import { AppError } from "../utils/AppError";
import { z } from "zod";
import { console } from "node:inspector";

export class InscricaoController {
    async create(req: Request, res: Response, next: NextFunction) {
        try {
            const schemaBody = z.object({
                evento_id: z.string()
            });

            const { evento_id } = schemaBody.parse(req.body);

            const evento = await knex("evento").where("id", evento_id).first();

            if (!evento) {
                throw new AppError("Evento não existe", 404);
            }

            const inscriacoExist = await knex("inscricao")
                .where({ user_id: req.user?.id_user, evento_id })
                .first();

            if (inscriacoExist) {
                throw new AppError("Já foi realizada a inscrição nesse curso", 401);
            }

            await knex("inscricao").insert({ user_id: req.user?.id_user, evento_id });

            res.send();
            return;
        } catch (error) {
            next(error);
        }
    }

    async index(req: Request, res: Response, next: NextFunction) {
        try {
            if (req.user?.role === "user") {
                const { nome } = req.query;

                let query = knex("inscricao")
                    .join("evento", "inscricao.evento_id", "=", "evento.id")
                    .select(
                        "inscricao.id as id",
                        "evento.id as eventos_id",
                        "evento.titulo",
                        "evento.descricao",
                        "evento.data_inicio",
                        "evento.data_fim",
                        "evento.localizacao",
                        "inscricao.validado"
                    )
                    .where("inscricao.user_id", req.user?.id_user);

                if (nome && typeof nome === "string") {
                    query = query.andWhere("evento.nome", "like", `%${nome}%`);
                }

                const eventosDoUsuario = await query;

                res.json(eventosDoUsuario);
                return;
            }

            if (req.user?.role === "admin") {
                const { evento, nome } = req.query;
                console.log(evento);

                let query = knex("inscricao")
                    .leftJoin("users", "inscricao.user_id", "users.id")
                    .leftJoin("evento", "inscricao.evento_id", "evento.id")
                    .select(
                        "inscricao.id",
                        "inscricao.validado",
                        "inscricao.created_at",
                        "users.nome as user_nome",
                        "users.email",
                        "evento.titulo",
                        "evento.descricao"
                    )
                    .where("inscricao.validado", 0);

                if (evento) {
                    query = query.andWhere("evento.nome", "like", `%${evento}%`);
                }

                if (nome) {
                    query = query.andWhere("users.nome", "like", `%${nome}%`);
                }

                const inscricoesPendentes = await query;
                res.send(inscricoesPendentes);
                return;
            }
        } catch (error) {
            next(error);
        }
    }

    async validadInscricao(req: Request, res: Response, next: NextFunction) {
        try {
            const { id } = req.body;

            const inscricao = await knex("inscricao").where("id", id).first();

            if (!inscricao) {
                throw new AppError("Inscrição não encontrada", 404);
            }

            await knex("inscricao")
                .where("id", inscricao.id)
                .update({ validado: 1 });

            res.send();
            return;
        } catch (error) {
            next(error);
        }
    }

    async delete(req: Request, res: Response, next: NextFunction) {
        try {
            const { id } = req.body;

            const inscricao = await knex("inscricao").where("id", id).first();

            if (!inscricao) {
                throw new AppError("Inscrição não encontrada", 404);
            }

            if (inscricao.validado !== 0) {
                throw new AppError("Não foi possível excluir essa inscrição");
            }

            await knex("inscricao").where("id", id).delete();
            res.send();
            return;
        } catch (error) {
            next(error);
        }
    }
}
