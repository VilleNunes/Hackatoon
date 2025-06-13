<?php

$api = new SimpleApiClient('http://localhost:3333');

$nome = isset($_GET['query']) ? $_GET['query'] : null;

$endpoint = '/eventos';
if ($nome) {
    $endpoint .= '?nome='. $nome;
}

$response = $api->get($endpoint);

if (is_array($response) && isset($response['status']) && $response['status'] === 'success' && $response['http_code'] === 200) {
    $eventos = is_array($response['data']) ? $response['data'] : [];
}

view("eventos", "app", ["eventos" => $eventos]);
