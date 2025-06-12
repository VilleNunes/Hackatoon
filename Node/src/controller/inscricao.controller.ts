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

            const {evento_id} = schemaBody.parse(req.body);

            const evento = knex("eventos").where("id",evento_id).first();

            if(!evento){
                throw new AppError("Evento não existe",404);
            }

            const inscriacoExist = await knex("inscricao").where({user_id:req.user?.id_user, evento_id: evento_id}).first();

            if(inscriacoExist){
                throw new AppError("Já foi realizada a inscrição nesse curso",401); 
            }

            await knex("inscricao").insert({user_id:req.user?.id_user,evento_id:evento_id});

            res.send();
            return;
        } catch (error) {
            next(error);
        }
    }
    async index(req: Request, res: Response, next: NextFunction){
        try {
            if(req.user?.role == "user"){
            const eventosDoUsuario = await knex("inscricao")
            .join("eventos", "inscricao.evento_id", "=", "eventos.id")
            .where("inscricao.user_id", req.user?.id_user)
            .select(
                "eventos.id",
                "eventos.nome",
                "eventos.descricao",
                "inscricao.validado"
            ); 

            res.json(eventosDoUsuario);
            return;
        }

            if(req.user?.role == "admin"){
                const inscricoesPendentes = await knex("inscricao")
                .join("users", "inscricao.user_id", "=", "users.id")
                .join("eventos", "inscricao.evento_id", "=", "eventos.id")
                .whereNull("inscricao.validado")
                .select(
                    "inscricao.id as inscricao_id",
                    "users.id as user_id",
                    "users.nome as user_nome",
                    "users.email as user_email",
                    "eventos.id as evento_id",
                    "eventos.nome as evento_nome",
                    "eventos.descricao",
                    "inscricao.validado"
                );

                res.json(inscricoesPendentes);
                return;
            }
        } catch (error) {
            next(error);
        }
    }

   
}