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

?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Sistesa</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicons -->
        <link href="../../img/s.png" rel="icon">
        <link href="../../img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700" rel="stylesheet">

        <!-- Bootstrap CSS File -->
        <link href="../../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Libraries CSS Files -->
        <link href="../../lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <link href="../../lib/animate/animate.min.css" rel="stylesheet">
        <link href="../../lib/ionicons/css/ionicons.min.css" rel="stylesheet">
        <link href="../../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="../../lib/lightbox/css/lightbox.min.css" rel="stylesheet">

        <!-- Main Stylesheet File -->
        <link href="../../css/style.css" rel="stylesheet">
        <link href="../../css/cupola.css" rel="stylesheet">
        <link href="../../css/aside.css" rel="stylesheet">


    </head>

    <body>

        <!--==========================
         Header
       ============================-->

        <header id="header">
            <div class="container-fluid">
                <div id="logo-menu" class="pull-left">
                    <button type="button" id="sidebarCollapse" class="btn">
                        <i class="ion-android-menu"></i>
                    </button>
                </div>

                <div id="logo-cupola" class="pull-left">
                    <h1><a href="#intro" class="scrollto" >SISTESA<hr><h3>DA CONFIANZA</h3></a></h1>

                    <!-- Uncomment below if you prefer to use an image logo -->
                    <!-- <a href="#intro"><img src="img/logo.png" alt="" title="" /></a>-->
                </div>

            </div>




            <!--                                <nav id="nav-menu-container">
                                                <ul class="nav-menu">
                                                    <li class="menu-active"><a href="#intro">Inicio</a></li>
                                                    <li><a href="#about">La Empresa</a></li>
                                                    <li><a href="#services">Servicios</a></li>
                                                    <li><a href="#portfolio">Proyectos</a></li>
                                                    <li><a href="#clients">Clientes</a></li>
                                                    <li><a href="#contact">Contactanos</a></li>
                                                                            <li class="menu-has-children"><a href="blog.html">Blog</a>
                                                                                <ul>
                                                                                    <li><a href="#">Articulo 1</a></li>
                                                                                    <li><a href="#">Articulo 2</a></li>
                                                                                    <li><a href="#">Articulo 3</a></li>
                                                                                    <li><a href="#">Articulo 4</a></li>
                                                                                </ul>
                                                                            </li>
                            
                                                </ul>
                                            </nav>
                             #nav-menu-container -->
        </div>
    </header>

    <main id="main">

        <div class="wrapper">
            <!-- Sidebar  -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <h3>SISTESA</h3>
                    <strong>S</strong>
                </div>

                <ul class="list-unstyled components">
                    <li class="active">
                        <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                            <i class="ion-ios-home"></i>
                            Home
                        </a>
                        <ul class="collapse list-unstyled" id="homeSubmenu">
                            <li>
                                <a href="#">Home 1</a>
                            </li>
                            <li>
                                <a href="#">Home 2</a>
                            </li>
                            <li>
                                <a href="#">Home 3</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">
                            <i class="ion-ios-list"></i>
                            About
                        </a>
                        <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                            <i class="ion-log-out"></i>
                            Pages
                        </a>
                        <ul class="collapse list-unstyled" id="pageSubmenu">
                            <li>
                                <a href="#">Page 1</a>
                            </li>
                            <li>
                                <a href="#">Page 2</a>
                            </li>
                            <li>
                                <a href="#">Page 3</a>
                            </li>
                        </ul>
                        <a href="logOut.php" >
                            <i class="ion-log-out"></i>
                            Salir
                        </a>

                    </li>
                </ul>

            </nav>