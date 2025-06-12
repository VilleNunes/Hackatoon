<?php

$api = new SimpleApiClient('http://localhost:3333');

$response = $api->get("/eventos");

if (is_array($response) && isset($response['status']) && $response['status'] === 'success' && $response['http_code'] === 200) {
    $eventos = is_array($response['data']) ? $response['data'] : [];
} else {
  $eventos = [];

  if (isset($response['message'])) {
      echo "Erro ao buscar eventos: " . $response['message'];
  }
}


view("index","app",["eventos" => $eventos]);