import knex from "knex"

declare module "knex/types/tables"{
  export interface Tables{
    users:{
      id: number,
      nome: string,
      email: string,
      senha: string,
      role: string,
    },
  }
}

