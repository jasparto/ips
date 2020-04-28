<?php

require "../lib/config.php";
require "../controller/ControlUser.php";

$auth = $_SERVER['HTTP_AUTHORIZATION'];
$dieMessage = "No puedes acceder a este recurso";

if (!isset($auth)) {
    die($dieMessage);
}

$security = explode(":", base64_decode($auth));
$userController = new UserController();

if (isset($auth) || $userController->getCant($userController->validate($security[0], $security[1])) == 0) {
    die($dieMessage);
}