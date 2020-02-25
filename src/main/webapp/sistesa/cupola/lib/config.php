<?php
session_start();
$host = "localhost"; /* Host name */
$user = "sistesas_root"; /* User */
$password = "xpL7Yv[qsG@;"; /* Password */
$dbname = "sistesas_cupola"; /* Database name */

$con = mysqli_connect($host, $user, $password,$dbname);
// Check connection
if (!$con) {
  die("Connection failed: " . mysqli_connect_error());
}