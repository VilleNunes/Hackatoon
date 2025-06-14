<?php

require "class/GerarPdf.php";


if(!auth()){
    header("location: index");
    die();
}


$evento = $_POST['evento'];
$aluno =  auth()['nome'];

$certificado = new CertificadoGenerator($evento, $aluno);
$certificado->gerar();
exit();

