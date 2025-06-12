<?php
  if($_SERVER["REQUEST_METHOD"] == "POST"){
    $nome = $_POST["nome"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $api = new SimpleApiClient('http://localhost:3333');

    $response = $api->post("/register",[
      "nome"=>$nome,
      "senha"=>$password,
      "email"=>$email,
    ]);

    if($response["http_code"] != "200"){
      flash()->push("error",$response["data"]);
      header("Location: register");
      die();
    }

    $response = $api->post("/login",[
      "senha"=>$password,
      "email"=>$email,
    ]);

    if($response["http_code"] != "200"){
      flash()->push("error",$response["data"]);
      header("Location: register");
      die();
    }

    var_dump($response["data"]["token"]);
    die();
  
  }
  
view("register","auth");