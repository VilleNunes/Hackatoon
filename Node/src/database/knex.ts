import config from "../../knexfile"
import {knex as knexConfig} from "knex"

export const knex = knexConfig(config);