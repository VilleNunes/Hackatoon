import express from "express";
import { routes } from "./routes";
import { errorHandler } from "./middeware/errorHandler";

const app = express();
const PORT = 3333;
app.use(express.json());
app.use(routes)
app.use(errorHandler);


app.listen(PORT,()=>{
  console.log(`Server is running on port ${PORT}`);
});

