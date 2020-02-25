<?php

require_once("../model/modelo.php");

class UserController {

    private $model;

    function __construct() {
        $this->model = new Modelo();
    }

    // VALIDATE
    function validate($user, $password) {
        $producto = new Modelo();
        $result = $producto->contar("usuarios", "usuario='" . $user . "' and clave=md5('" . $password . "')");
        return $result;
    }

    function getCant($result) {
        foreach ($result as $key => $value) {
            foreach ($value as $va) {
                $count = $va['cant'];
            }
        }
        return $count;
    }

}
