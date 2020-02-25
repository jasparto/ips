<?php
include "../lib/config.php";

// Check user login or not
//Write action to txt log
$log = "User: " . $_SERVER['REMOTE_ADDR'] . ' - ' . date("F j, Y, g:i a") . PHP_EOL .
        "Attempt: " . (isset($_SESSION['uname'])) . PHP_EOL .
        "User: " . isset($_SESSION['uname']) . PHP_EOL .
        "-------------------------" . PHP_EOL;
//-
file_put_contents('./log_' . date("j.n.Y") . '.txt', $log, FILE_APPEND);
if (!isset($_SESSION['uname'])) {
    header('Location: ../index.html');
}

// logout
if (isset($_POST['but_logout'])) {
    session_destroy();
    header('Location: ../index.html');
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <title>MVC</title>
</head>
<body>
	<div class="panel">
	<h1> SISTEMA DE PRODUCTOS</h1>