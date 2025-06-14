<?php

if(!auth()){
    header("location: index");
    die();
}

$id_inscricao = $_POST['id_inscricao'];
$api = new SimpleApiClient('http://localhost:3333');
$api->setToken(token());
$response = $api->delete("/inscricao/deletar",["id"=>$id_inscricao]);

if (is_array($response) && isset($response['status']) && $response['status'] === 'success' && $response['http_code'] === 200) {
   flash()->push('sucess',"Inscrição deletada com sucesso");
}else{
    flash()->push('error',$response['data']);
}

header("Location: minhas-inscricoes");
