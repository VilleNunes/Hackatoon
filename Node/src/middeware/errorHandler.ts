import { Request,Response,NextFunction } from "express";
import { AppError } from "../utils/AppError";
import { ZodError } from "zod";

export function errorHandler(error:any,req: Request, res: Response, next: NextFunction):any{
  if(error instanceof AppError){
    return res.status(error.statusCode).json(error.mensagem);
  }

  if(error instanceof ZodError){
    return res.status(500).json(
      "Error de Validação nos campos"
    )
  }

  return res.status(500).json(error.message);
}