<?php
require "../controller/control.php";
require "layout/header.php";


$control = new modeloController();
$dato = $control->index();
?>



<!--==========================
  Quotes Section
============================-->
<!--<section id="contact" class="section-bg-cupola wow fadeInUp" >-->




<div class="container" >

    <div class="form">


        <!--<form action="http://rs-pvapp.rhcloud.com/webresources/generic/post/correo" target="_blank" method="post" role="form" class="contactForm">-->
        <!--<form action="http://186.147.244.139:8180/apache/webresources/generic/post/correoAsunto" target="_blank" method="post" role="form" >-->
        <form action="" method="post" role="form" class="quoteForm">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <input type="text" name="nombre" class="form-control" id="name" placeholder="Nombre Contacto" data-rule="minlen:4" data-msg="Ingresa tu nombre" />
                    <div class="validation"></div>
                </div>
                <div class="form-group col-md-6">
                    <input type="email" class="form-control" id="correo" name="correo" placeholder="Correo" data-rule="email" data-msg="Ingresa un correo valido" />
                    <div class="validation"></div>
                </div>
            </div>
            <div class="form-group">
                <input type="number" class="form-control" id="telefono" name="telefono" placeholder="Teléfono" data-rule="minlen:4" data-msg="Ingresa telefono contacto" />
                <div class="validation"></div>
            </div>
            <div class="form-group">
                <textarea class="form-control" id="mensaje" name="mensaje" rows="5" data-rule="required" data-msg="Escribe tus comentarios" placeholder="Mensaje"></textarea>
                <div class="validation"></div>
            </div>
            <div class="text-center"><button type="submit" style="width: 200px;">Guardar</button></div>
            <div id="sendmessage"></div>
            <div id="errormessage"></div>
        </form>
    </div>

</div>

<!--</section> #contact -->






<?php require "layout/footer.php" ?>