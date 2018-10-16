<?php
echo htmlspecialchars($_POST['nombre']);
echo htmlspecialchars($_POST['correo']);
echo htmlspecialchars($_POST['telefono']);
echo htmlspecialchars($_POST['mensaje']);

ini_set('display_errors', 1);
error_reporting(E_ALL);
$from = "sistesa@sistesa.com.co";
$to = "jaspart@gmail.com," . htmlspecialchars($_POST['correo']);
$subject = "Checking PHP mail";
//$message = "PHP mail works just fine";
$message = htmlspecialchars($_POST['mensaje']);

// Always set content-type when sending HTML email
$headers = "MIME-Version: 1.0" . "\r\n";
$headers .= "Content-type:text/html;charset=UTF-8" . "\r\n";

$headers = "From:" . $from;
mail($to, $subject, $message, $headers);
echo "The email message was sent.";
?>