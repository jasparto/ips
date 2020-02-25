<?php

include "config.php";
require "../controller/ControlUser.php";

$uname = mysqli_real_escape_string($con, $_POST['username']);
$password = mysqli_real_escape_string($con, $_POST['password']);

if ($uname != "" && $password != "") {

    $userController = new UserController();
    //$result = $userController->validate($uname, $password);
    //$sql_query = "select count(*) as cntUser from usuarios where usuario='".$uname."' and clave=md5('".$password."')";
    //$result = mysqli_query($con,$sql_query);
    //$row = mysqli_fetch_array($result);
    //$count = $row['cant'];
    /* $va2 = 'a';
      foreach ($result as $key => $value) {
      foreach ($value as $va) {
      $count = $va['cant'];
      $va2 = $va;
      }
      } */
    $count = $userController->getCant($userController->validate($uname, $password));

    //Write action to txt log
    $log = "User: " . $_SERVER['REMOTE_ADDR'] . ' - ' . date("F j, Y, g:i a") . PHP_EOL .
            "Attempt: " . ($count == '1' ? 'Success' : 'Failed') . PHP_EOL .
            "User: " . $uname . PHP_EOL .
            "-------------------------" . PHP_EOL;
    //-
    file_put_contents('./log_' . date("j.n.Y") . '.txt', $log, FILE_APPEND);

    if ($count > 0) {
        $_SESSION['uname'] = $uname;
        echo 1;
    } else {
        echo 0;
    }
}