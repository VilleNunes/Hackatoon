import config from "../../Knexfile"
import {knex as knexConfig} from "knex"

export const knex = knexConfig(config);