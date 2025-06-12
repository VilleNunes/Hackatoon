export class AppError{
  mensagem: string
  statusCode: number
  

  constructor(message: string, statusCode: number = 500){
    this.mensagem = message
    this.statusCode = statusCode
  }
}