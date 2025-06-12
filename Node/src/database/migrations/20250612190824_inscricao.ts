import { Knex } from "knex";

export async function up(knex: Knex): Promise<void> {
  await knex.schema.createTable("inscricao", (table) => {
    table.increments("id").primary();

    table
      .integer("user_id")
      .notNullable()
      .references("id")
      .inTable("users")

    table
      .integer("evento_id")
      .notNullable()
      .references("id")
      .inTable("eventos");

    table.boolean("validado").defaultTo(false);

    table.timestamps(true, true);
  });
}

export async function down(knex: Knex): Promise<void> {
  await knex.schema.dropTableIfExists("inscricao");
}
