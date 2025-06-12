import { Request, Response, Router } from "express";
import { EventsController } from "../controller/events.controller";


const routes = Router();
const eventsController = new EventsController();

routes.get("/index",eventsController.index);

export {
    routes
}