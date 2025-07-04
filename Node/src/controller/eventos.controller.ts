import { Request, Response, NextFunction } from "express";
import { knex } from "../database/knex";
import { AppError } from "../utils/AppError";

export class EventsController {
    async index(req: Request, res: Response, next: NextFunction) {
        try {
            const { nome } = req.query;

            let query = knex("evento").select("*").orderBy("id", "desc");

            if (nome && typeof nome === "string") {
                query = query.where("titulo", "like", `%${nome}%`);
            }

            const eventos = await query;

            res.json(eventos);
        } catch (error) {
            next(error);
        }
    }

    async show(req: Request, res: Response, next: NextFunction) {
        try {
            const { id } = req.params;

            const evento = await knex("evento")
            .join("curso","evento.id_curso","=","curso.id")
            .join("palestrante","evento.id_palestrante","=","palestrante.id")
            .select(
                "evento.id",
                "evento.titulo",
                "evento.descricao",
                "evento.data_inicio",
                "evento.data_fim",
                "evento.imagem",
                "evento.localizacao",
                "curso.nome",
                "palestrante.nome as nome_palestrante",
                "palestrante.minicurriculo",
                "palestrante.foto"
            ).where("evento.id",id).first();

            if (!evento) {
                throw new AppError("Esse evento não existe", 404);
            }

            res.json(evento);
            return;
        } catch (error) {
            next(error);
        }
    }

    async eventoNotValidad(req: Request, res: Response, next: NextFunction) {
        try {
            const { nome } = req.query;

            let query = knex("evento")
                .whereNotIn("id", function () {
                    this.select("evento_id")
                        .from("inscricao")
                        .where("user_id", req.user?.id_user);
                });

            if (nome && typeof nome === "string") {
                query = query.andWhere("titulo", "like", `%${nome}%`);
            }

            const eventosDisponiveis = await query;

            res.json(eventosDisponiveis);
            return;
        } catch (error) {
            next(error);
        }
    }
}
