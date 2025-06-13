<?php
    function view($view,$template = "app",$dados = []){
        foreach($dados as $key => $value ){
            $$key = $value;
        }
        require "view/template/{$template}.php";
    }
    function flash(){
        return new Flash;
    }

    function auth(){
        if(!isset($_SESSION['auth'])){
            return false;
        }

     return $_SESSION['auth'];
    }

    function token(){
        if(!isset($_SESSION['token'])){
            return false;
        }

     return $_SESSION['token'];
    }

?>