<?php
require "../controller/control.php";
require "layout/header.php";


$control = new modeloController();
$dato = $control->index();

//Write action to txt log
$log = "User: " . $_SERVER['REMOTE_ADDR'] . ' - ' . date("F j, Y, g:i a") . PHP_EOL .
        "Attempt: " . $dato . PHP_EOL .
        "User: yo2" . PHP_EOL .
        "-------------------------" . PHP_EOL;
//-
file_put_contents('./debug_' . date("j.n.Y") . '.txt', $log, FILE_APPEND);
?>



<!--==========================
  Contact Section
============================-->
<!--<section id="contact" class="section-bg-cupola wow fadeInUp" >-->




<div class="container" >


    <!--            <div class="section-header-cupola" style="margin-top: 100px;">
                    <h3>Sistema Integrado de Cotizaciones</h3>
                </div>-->

    <div class="form">

        <div class="section-header-cupola" style="margin-top: 100px;">
            <h3>Historial de Cotizaciones</h3>
        </div>


        <center>
            <table class="table table-striped table-responsive-md btn-table">
                <tr>
                    <th>Id</th>
                    <td>Nombre</td>
                    <td>Precio</td>
                    <td>Acción</td>
                </tr>
                <?php
                foreach ($dato as $key => $value) {

                    //Write action to txt log
                    $log = "User: " . $_SERVER['REMOTE_ADDR'] . ' - ' . date("F j, Y, g:i a") . PHP_EOL .
                            "Attempt: " . $value . PHP_EOL .
                            "User: yo3" . PHP_EOL .
                            "-------------------------foreach ($dato as $key => $value) {" . PHP_EOL;
                    //-
                    //file_put_contents('./debug_' . date("j.n.Y") . '.txt', $log, FILE_APPEND);

                    foreach ($value as $va) {
                        echo "<tr><th>" . $va['id'] . "</th><td>" . $va['nombre'] . "</td><td>S./" . $va['precio'] . "</td>";
                        echo "<td><button onclick=\"location.href='index.php?m=consultar&id=" . $va['id'] . "'\" type='button' class='btn btn-indigo btn-sm m-0 ion-search'> Consultar</button></td>";
                        echo "</tr>";
                    }
                }
                ?>
            </table>
        </center>


        <br/>

        <div id="sendmessage"></div>
        <div id="errormessage"></div>


    </div>

</div>

<!--</section> #contact -->






<?php require "layout/footer.php" ?>