import { Request, Response, NextFunction } from "express";
import { z } from "zod";
import {compare, hash} from "bcrypt"
import { knex } from "../database/knex";
import { AppError } from "../utils/AppError";
import {sign} from "jsonwebtoken";

 
export class UsersController{
    async create(req:Request,res:Response,next:NextFunction){
        try {
            const schemaBody = z.object({
                nome: z.string(),
                email: z.string().email(),
                senha: z.string()
            })

            const {nome,email,senha} = schemaBody.parse(req.body);

            const user = await knex("users").where("email",email).first();

            if(user){
                throw new AppError("Email já está sendo utilizado",409);
                
            }
            
            const senha_hash = await hash(senha,8);
            await knex('users').insert({nome,email,senha:senha_hash});

            res.send();
            return;
       } catch (error) {
         next(error);
       }
    }

    async session(req:Request,res:Response,next:NextFunction){
        try {
            const schemaBody = z.object({
                email: z.string().email(),
                senha: z.string()
            });

            const {email,senha} = schemaBody.parse(req.body);

            const user = await knex("users").where("email",email).first();

            if(!user){
                throw new AppError("Email ou senha Inválidos",404);
            }

            const password = await compare(senha,user.senha);

            if(!password){
                throw new AppError("Email ou senha Inválidos",404);
            }

           
            const token = sign({role: user.role ?? "user"},"segredo",{
                subject: String(user.id),
                expiresIn: "2d",
            });
            const {senha: _,...userInfo} = user
            
            res.json({
                token,
                user:userInfo
            });
            return;
        } catch (error) {
            next(error);
        }
    }
}
