import { Request, Response, NextFunction } from "express";
import { knex } from "../database/knex";
import { AppError } from "../utils/AppError";
import { z } from "zod";


export class InscricaoController {
    async create(req: Request, res: Response, next: NextFunction) {
        try {
            const schemaBody = z.object({
                evento_id: z.string()
            })

            const { evento_id } = schemaBody.parse(req.body);

            const evento = knex("eventos").where("id", evento_id).first();

            if (!evento) {
                throw new AppError("Evento não existe", 404);
            }

            const inscriacoExist = await knex("inscricao").where({ user_id: req.user?.id_user, evento_id: evento_id }).first();

            if (inscriacoExist) {
                throw new AppError("Já foi realizada a inscrição nesse curso", 401);
            }

            await knex("inscricao").insert({ user_id: req.user?.id_user, evento_id: evento_id });

            res.send();
            return;
        } catch (error) {
            next(error);
        }
    }
    async index(req: Request, res: Response, next: NextFunction) {
        try {
            const { nome } = req.query;
            if (req.user?.role === "user") {
                let query = knex("inscricao")
                    .join("eventos", "inscricao.evento_id", "=", "eventos.id")
                    .select(
                        "eventos.id",
                        "eventos.nome",
                        "eventos.descricao",
                        "inscricao.validado"
                    ).where("inscricao.user_id", req.user?.id_user);

                if (nome && typeof nome === "string") {
                    query = query.andWhere("eventos.nome", "like", `%${nome}%`);
                }

                const eventosDoUsuario = await query;

                res.json(eventosDoUsuario);
                return;
            }

            if (req.user?.role == "admin") {
                const inscricoesPendentes = await knex("inscricao")
                    .join("users", "inscricao.user_id", "=", "users.id")
                    .join("eventos", "inscricao.evento_id", "=", "eventos.id")
                    .select(
                        "inscricao.id as inscricao_id",
                        "inscricao.validado",
                        "users.nome as user_nome",
                        "users.email",
                        "eventos.nome",
                        "eventos.descricao"
                    ).where("validado","=","0");
                res.send(inscricoesPendentes);
                return;
            }
        } catch (error) {
            next(error);
        }
    }


}