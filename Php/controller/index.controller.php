<?php

$api = new SimpleApiClient('http://localhost:3333');

$response = $api->get("/eventos");

if (is_array($response) && isset($response['status']) && $response['status'] === 'success' && $response['http_code'] === 200) {
  $eventos = is_array($response['data']) ? $response['data'] : [];
}


view("index","app",["eventos" => $eventos]);