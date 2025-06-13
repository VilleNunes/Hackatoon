<?php

if(!auth()){
   header("Location: index");
}
$api = new SimpleApiClient('http://localhost:3333');
$api->setToken(token());
$nome = isset($_GET['query']) ? $_GET['query'] : null;
var_dump($nome);

$endpoint = '/meus-eventos';
if ($nome) {
    $endpoint .= '?nome='. $nome;
}

$response = $api->get($endpoint);

if (is_array($response) && isset($response['status']) && $response['status'] === 'success' && $response['http_code'] === 200) {
    $eventos = is_array($response['data']) ? $response['data'] : [];
} 

view("home","dashboard",["eventos" => $eventos]);
