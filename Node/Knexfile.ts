export default {
  client: "mysql2",
  connection: {
    host: "localhost",
    user: "root",
    password:"", 
    database: "eventos", 
    port: 3306,
  },
  pool: {
    min: 2,
    max: 10,
  },
  migrations: {
    directory: "./src/database/migrations",
    extension: "ts", 
  },
  seeds: {
    directory: "./src/database/seeds",
    extension: "ts",
  },
};