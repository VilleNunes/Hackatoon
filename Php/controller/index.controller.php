<?php

require "./class/api.php";


$api = new SimpleApiClient('http://localhost:3333');


$eventos = $api->get("/index");

if(is_string($eventos)){
  $eventos = [];
}
var_dump($eventos);

view("index","app",["eventos" => $eventos]);