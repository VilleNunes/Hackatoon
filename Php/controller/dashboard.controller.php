<?php

if(!auth()){
   header("Location: index");
}

if(auth()['role'] == "admin"){
    header("Location: admin");
    die();
}

$api = new SimpleApiClient('http://localhost:3333');
$api->setToken(token());
$nome = isset($_GET['query']) ? $_GET['query'] : null;


$endpoint = '/meus-eventos';
if ($nome) {
    $endpoint .= '?nome='. $nome;
}

$response = $api->get($endpoint);



if (is_array($response) && isset($response['status']) && $response['status'] === 'success' && $response['http_code'] === 200) {
    $eventos = is_array($response['data']) ? $response['data'] : [];
}else{
    $eventos = [];
}


view("home","dashboard",["eventos" => $eventos]);
