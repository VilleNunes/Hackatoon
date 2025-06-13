<?php
    if(!auth()){
      header("Location: login");
      die();
    }

    $id_evento = $_POST["id_evento"];
    $api = new SimpleApiClient('http://localhost:3333');
  
    $api->setToken(token());

    $response = $api->post("/inscricao",[
      "evento_id"=>$id_evento,
    ]);

    if($response["http_code"] != "200"){
      flash()->push("error",$response["data"]);
      header("Location:  dashboard");
      die();
    }

    header("Location: dashboard");
    die();
?>