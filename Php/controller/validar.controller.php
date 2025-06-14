<?php

if(!auth()){
    header("Location: index");
    die();
}

if(auth()['role'] != "admin"){
    header("Location: dashboard");
    die();
}
$inscrica_id = $_POST['id'];

$api = new SimpleApiClient('http://localhost:3333');
$api->setToken(token());
$reponse = $api->post("/validar",["id"=>$inscrica_id]);

if ($response['http_code'] =! 200) {
    flash()->push("error","error a validar inscrição");
    header("Location: admin");
    die();
}

flash()->push("sucess","Inscrição validado com sucesso");
header("Location: admin");




