import { Router } from "express";
import { EventsController } from "../controller/eventos.controller";
import { UsersController } from "../controller/users.controller";
import { authToken } from "../middeware/authToken";
import { InscricaoController } from "../controller/inscricao.controller";
import { MetricasController } from "../controller/metricas.controller";


const routes = Router();
const eventosController = new EventsController();
const usersController = new UsersController();
const inscricaoController = new InscricaoController();
const metricasController = new MetricasController();

routes.get("/eventos", eventosController.index);
routes.get("/eventos/:id", eventosController.show);
routes.post("/register",usersController.create);
routes.post("/login",usersController.session);

routes.use(authToken);
routes.get("/meus-eventos",eventosController.eventoNotValidad);

routes.post("/inscricao",inscricaoController.create);
routes.get("/inscricao/user",inscricaoController.index);
routes.delete("/inscricao/deletar",inscricaoController.delete);

routes.post("/validar",inscricaoController.validadInscricao);

routes.get("/metricas",metricasController.index);
export {routes}