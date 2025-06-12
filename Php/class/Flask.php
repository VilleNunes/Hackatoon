<?php
    class Flash{
        public function push($chave,$valor){
            $_SESSION[$chave] = $valor;
        }

        public function get($chave){
            if(!isset($_SESSION[$chave])){
                return false;
            }

            $valor = $_SESSION[$chave];

            unset($_SESSION[$chave]);

            return $valor;
        }
    }