<?php

  if(auth()){
    header("Location: index");
    die();
  }

  if($_SERVER["REQUEST_METHOD"] == "POST"){
    $email = $_POST["email"];
    $password = $_POST["password"];
    $api = new SimpleApiClient('http://localhost:3333');
    
    $response = $api->post("/login",[
      "senha"=>$password,
      "email"=>$email,
    ]);

    if($response["http_code"] != "200"){
      flash()->push("error",$response["data"]);
      header("Location: login");
      die();
    }

    $_SESSION["token"] = $response["data"]["token"];
    $_SESSION["auth"] = $response["data"]["user"];
    header("Location: dashboard");
    die();
  }

  view("login","auth");