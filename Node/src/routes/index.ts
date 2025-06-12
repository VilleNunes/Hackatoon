import { Router } from "express";
import { EventsController } from "../controller/eventos.controller";
import { UsersController } from "../controller/users.controller";
import { authToken } from "../middeware/authToken";
import { InscricaoController } from "../controller/inscricao.controller";


const routes = Router();
const eventosController = new EventsController();
const usersController = new UsersController();
const inscricaoController = new InscricaoController();

routes.get("/eventos", eventosController.index);
routes.get("/eventos/:id", eventosController.show);
routes.post("/register",usersController.create);
routes.post("/login",usersController.session);

routes.use(authToken);
routes.post("/inscricao",inscricaoController.create);
routes.get("/inscricao/user",inscricaoController.index);
export {routes}