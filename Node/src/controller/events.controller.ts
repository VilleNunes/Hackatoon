import { Request, Response, NextFunction } from "express";
import { knex } from "../database/knex";


export class EventsController {
    async index(req: Request, res: Response, next: NextFunction) {
        try {
            const { nome } = req.query;

            let query = knex("eventos").select("*");

            if (nome && typeof nome === "string") {
                query = query.where("nome", "like", `%${nome}%`);
            }

            const eventos = await query;

            res.json(eventos);
        } catch (error) {
            next(error);
        }
    }
}