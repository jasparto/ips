<?php

include "../lib/config.php";
require "../controller/ControlUser.php";

$auth = $_SERVER['HTTP_AUTHORIZATION'];
$security = explode(":", base64_decode($auth));
$userController = new UserController();

if ($userController->getCant($userController->validate($security[0], $security[1])) == 0) {
    die("No puedes acceder a este recurso");
}