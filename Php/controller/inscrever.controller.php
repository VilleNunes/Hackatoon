<?php
    if(!auth()){
      flash()->push("error","Para se inscrever, você precisa estar conectado com sua conta.");
      header("Location: login");
      die();
    }

     if(auth()['role'] == "admin"){
      flash()->push("error","Para fazer uma inscrição, é necessário estar logado em uma conta de usuário.");
      header("Location: index");
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
    flash()->push("sucess","Inscrição feita com sucesso");
    header("Location: minhas-inscricoes");
    die();
?>