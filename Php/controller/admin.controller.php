<?php
if (!auth()) {
    header("Location: index");
    exit;
}

$api = new SimpleApiClient('http://localhost:3333');
$api->setToken(token());

$nome = isset($_GET['nome']) ? $_GET['nome'] : null;
$evento = isset($_GET['evento']) ? $_GET['evento'] : null;

$endpoint = '/inscricao/user';
$params = [];

if ($nome) {
    $params['nome'] = $nome;
}

if ($evento) {
    $params['evento'] = $evento;
}

if (!empty($params)) {
    $endpoint .= '?' . http_build_query($params);
}

$response = $api->get($endpoint);


if (is_array($response) && isset($response['status']) && $response['status'] === 'success' && $response['http_code'] === 200) {
    $inscricoes = is_array($response['data']) ? $response['data'] : [];
} else {
    $inscricoes = [];
}

view("homeAdmin", "dashboard", ["inscricoes" => $inscricoes]);

          