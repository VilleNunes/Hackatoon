import "express";

declare global {
  namespace Express {
    interface Request {
      user?: {
        id_user: string;
        role: string;
      };
    }
  }
}
