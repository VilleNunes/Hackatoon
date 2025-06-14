<?php

if(!auth()){
   header("Location: index");
}

$api = new SimpleApiClient('http://localhost:3333');
$api->setToken(token());
$nome = isset($_GET['query']) ? $_GET['query'] : null;


$endpoint = '/inscricao/user';
if ($nome) {
    $endpoint .= '?nome='. $nome;
}

$response = $api->get($endpoint);


if (is_array($response) && isset($response['status']) && $response['status'] === 'success' && $response['http_code'] === 200) {
   $inscricoes = is_array($response['data']) ? $response['data'] : [];
} 

        
view("homeAdmin","dashboard",["inscricoes"=>$inscricoes]);
          