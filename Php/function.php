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

?>