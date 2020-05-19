<?php
require "../controller/control.php";
require "layout/header.php";


$control = new modeloController();
$dato = $control->index();
?>

<!--==========================
  Quotes Section
============================-->

<div class="container" >

    <div class="form">


        <div class="section-header-cupola" style="margin-top: 100px;">
            <h3>Nueva Cotización</h3>
        </div>

        <form action="" method="post" role="form" class="quoteForm">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <input type="email" class="form-control" id="correo" name="correo" placeholder="Correo" data-rule="email" data-msg="Ingresa un correo valido" />
                    <div class="validation"></div>
                </div>
                <div class="form-group col-md-6">
                    <input type="text" name="nombre" class="form-control" id="name" placeholder="Nombre Cliente" data-rule="minlen:4" data-msg="Ingresa el nombre del cliente" />
                    <div class="validation"></div>
                </div>
            </div>
            <div class="form-group">
                <input type="number" class="form-control" id="telefono" name="telefono" placeholder="Teléfono" data-rule="minlen:4" data-msg="Ingresa telefono contacto" />
                <div class="validation"></div>
            </div>
            <div class="form-group">
                <textarea class="form-control" id="mensaje" name="mensaje" rows="5" data-rule="required" data-msg="Escribe las observaciones" placeholder="Observaciones"></textarea>
                <div class="validation"></div>
            </div>
            <div class="text-center"><button type="submit" style="width: 200px;">Guardar</button></div>
            <div id="sendmessage"></div>
            <div id="errormessage"></div>
        </form>
    </div>

</div>

<?php require "layout/footer.php" ?>