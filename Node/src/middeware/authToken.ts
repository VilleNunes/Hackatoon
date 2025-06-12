import { NextFunction, Request,Response,} from "express";
import {AppError} from "../utils/AppError"
import {verify} from "jsonwebtoken"


interface TokenType{
  role : string,
  sub: string
}
export function authToken(req: Request, res: Response, next: NextFunction):void{
  try{
    const headersToken = req.headers.authorization

    if(!headersToken){
      throw new AppError("Token de autenticação não fornecido", 401);
    }
  
    const [,token] = headersToken.split(" ");

    const {role,sub: id_user} =  verify(token,"segredo") as TokenType
    req.user = {
      role,
      id_user
    }

    next()
    
  }catch(error){
    throw new AppError("Token invalido",401)
  }
}