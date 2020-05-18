<?php

require "Authorization.php";

$uname = mysqli_real_escape_string($con, $_GET['username']);
$password = mysqli_real_escape_string($con, $_GET['password']);

if ($uname != "" && $password != "") {
    $count = $userController->getCant($userController->validate($uname, $password));

    //Write action to txt log
    $log = "User: " . $_SERVER['REMOTE_ADDR'] . ' - ' . date("F j, Y, g:i a") . PHP_EOL .
            "Attempt: " . ($count == '1' ? 'Success' : 'Failed') . PHP_EOL .
            "User: " . $uname . PHP_EOL .
            "-------------------------" . PHP_EOL;
    //-
    file_put_contents('./log_' . date("j.n.Y") . '.txt', $log, FILE_APPEND);
    
    header('Content-type: application/json');
    echo '{"user":"' . $uname . '", "Count":"' . $count . '", "Status":"'.$uname.($count >= '1' ? 'Success' : 'Failed').'"}';
}