<?php

ini_set('display_errors', 1);
error_reporting(E_ALL);
$from = "juliano@audifarma.com.co";
$to = "jaspart@gmail.com";
$subject = "Checking PHP mail";
$message = "PHP mail works just fine";

// Always set content-type when sending HTML email
$headers = "MIME-Version: 1.0" . "\r\n";
$headers .= "Content-type:text/html;charset=UTF-8" . "\r\n";

$headers = "From:" . $from;
mail($to, $subject, $message, $headers);
echo "The email message was sent.";
?>