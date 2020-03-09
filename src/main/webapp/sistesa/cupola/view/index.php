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
    <section id="contact" class="section-bg-cupola wow fadeInUp" >
        <div class="container" >
            <div class="section-header-cupola" style="margin-top: 100px;">
                <h3>Sistema Integrado de Cotizaciones</h3>
            </div>

            <div class="form">
                <div id="sendmessage">Mensaje enviado con éxito.</div>
                <div id="errormessage"></div>

                <a href="index.php?m=nuevo">NUEVO</a>
                <table border="1">
                    <tr>
                        <td>Id</td>
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
                        file_put_contents('./debug_' . date("j.n.Y") . '.txt', $log, FILE_APPEND);

                        foreach ($value as $va) {
                            echo "<tr><td>" . $va['id'] . "</td><td>" . $va['nombre'] . "</td><td>S./" . $va['precio'] . "</td>";
                            echo "<td><a href='index.php?m=editar&id=" . $va['id'] . "'>ACTUALIZAR</a> <a href='index.php?m=eliminar&id=" . $va['id'] . "'>ELIMINAR</a></td>";
                            echo "</tr>";
                        }
                    }
                    ?>
                </table>


                <br/>

                <div class="text-center">
                    <button type="submit"  name="but_submit2" style="width: 200px">Registrarse</button>
                </div>




            </div>

        </div>
    </section><!-- #contact -->






<?php require "layout/footer.php" ?>