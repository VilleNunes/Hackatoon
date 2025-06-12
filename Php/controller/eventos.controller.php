<?php
require "./class/api.php";

$api = new SimpleApiClient('http://localhost:3333');

$nome = isset($_GET['query']) ? $_GET['query'] : null;

$endpoint = '/index';
if ($nome) {
    $endpoint .= '?nome='. $nome;
}

$eventos = $api->get($endpoint);

if(is_string($eventos)){
  $eventos = [];
}


view("eventos", "app", ["eventos" => $eventos]);
