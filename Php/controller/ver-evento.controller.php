<?php

if($_SERVER['REQUEST_METHOD'] == "GET"){
  require "./view/template/notFound.php";
  die();
}

$api = new SimpleApiClient('http://localhost:3333');

$response = $api->get("/eventos");

if (is_array($response) && isset($response['status']) && $response['status'] === 'success' && $response['http_code'] === 200) {
  $eventos = is_array($response['data']) ? $response['data'] : [];
}else{
    $eventos = [];
}

$id_evento = $_POST["id_evento"];

$response = $api->get("/eventos/$id_evento");

if (is_array($response) && isset($response['status']) && $response['status'] === 'success' && $response['http_code'] === 200) {
  $evento = is_array($response['data']) ? $response['data'] : [];
}else{
    $evento = [];
}

view("ver-evento","app",["eventos"=>$eventos,"evento"=>$evento]);