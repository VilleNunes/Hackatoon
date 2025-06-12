import express,{Request,Response} from "express";
import { routes } from "./route";
import cors from "cors"
import { errorHandler } from "./middlware/errorHandler";

const app = express();

app.use(cors());
app.use(express.json());
app.use(routes);
app.use(errorHandler);
app.listen(3333,()=>{
    console.log("rodando na porta 3333");
})