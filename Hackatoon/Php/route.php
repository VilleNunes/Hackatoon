<?php

$controller = str_replace("/","",parse_url($_SERVER["REQUEST_URI"])["path"]);


if(!$controller){
    $controller = "index";
}

if(!file_exists("controller/{$controller}.controller.php")){
    echo("Not FOund");
    die();
}

require "controller/{$controller}.controller.php";