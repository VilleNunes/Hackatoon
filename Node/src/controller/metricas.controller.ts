import { Request, Response, NextFunction } from "express";
import { knex } from "../database/knex";


export class MetricasController {
    async index(req: Request, res: Response, next: NextFunction) {
        try {
            const totalUsuarios = await knex("users").count({ count: "*" });
            const totalInscricoes = await knex("inscricao").count({ count: "*" });
            const totalEventos = await knex("eventos").count({ count: "*" });

            res.json({
                totalUsuarios: totalUsuarios[0].count,
                totalInscricoes: totalInscricoes[0].count,
                totalEventos: totalEventos[0].count,
            });
            return;
        } catch (error) {
            next(error);
        }
    }
}