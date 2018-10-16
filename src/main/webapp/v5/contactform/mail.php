<?php

//echo htmlspecialchars($_POST['nombre']);
//echo htmlspecialchars($_POST['correo']);
//echo htmlspecialchars($_POST['telefono']);
//echo htmlspecialchars($_POST['mensaje']);

ini_set('display_errors', 1);
error_reporting(E_ALL);
$from = "sistesa@sistesa.com.co";
//$to = "sistesa@sistesa.com.co,bannyarva@gmail.com";
$to = "sistesa@sistesa.com.co";
$subject = "Mensaje de ".htmlspecialchars($_POST['nombre'])." (Mensaje Asesor Sistesa)";
//$message = "PHP mail works just fine";
//$message = htmlspecialchars($_POST['mensaje']);


$firma = '
    <div>
    <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tbody>
            <tr>
                <td width="100" align="left" valign="top">

                    <img src="http://www.sistesa.com.co/sistesa-rof6sttf69.png"
                         alt="Sistesa" height="150" width="150"/>
                </td>
                <td align="left" valign="middle">
                    <p>
                        <font face="Herculanum, Eras Demi ITC, sans-serif" color="#435152" style="font-size:18px;">John William Arboleda Valencia</font><br>    
                        <font face="Herculanum, Eras Demi ITC, sans-serif" color="#FF0100" style="font-size:12px;">Arquitecto / Director Proyectos</font><br>
                        <font face="Herculanum, Eras Demi ITC, sans-serif" style="font-size:12px"></font>
                        <a href="http://www.facebook.com/sistesasas">
                            <img src="http://www.sistesa.com.co/if_facebook_28291.png"
                                 alt="Facebook" border="0"></a>&nbsp;
                        <a href="http://www.instagram.com/SistesaColombia">
                            <img src="http://www.sistesa.com.co/if_Instagram_381384.png" 
                                 alt="Instagram" border="0">
                        </a>
                        <a href="http://www.twitter.com/SistesaColombia">
                            <img src="http://www.sistesa.com.co/if_twitter_28313.png" 
                                 alt="Twitter" border="0">
                        </a>
                        <a href="mailto:sistesa@sistesa.com.co">
                            <img src="http://www.sistesa.com.co/if_email_28290.png" 
                                 alt="sistesa@sistesa.com.co" border="0">
                        </a>
                        <br/>
                        <font face="Herculanum, Eras Demi ITC, sans-serif" color="#425151" style="font-size:12px;">Manizales</font><br>
                        <font face="Herculanum, Eras Demi ITC, sans-serif" color="#425151" style="font-size:12px;">Pereira</font><br>
                        <font face="Herculanum, Eras Demi ITC, sans-serif" color="#425151" size="-1">Movil: <strong>
                            <a href="https://wa.me/573187080338"><font color="#6B777A">+57 318 708 0338</font>
                        </a>
                        </strong>
                        <br/>
                        <a href="http://www.sistesa.com.co">www.sistesa.com.co</a></font>

                    </p>
                </td>
            </tr>
        </tbody>
    </table>
    </div>';

$message = "
<html>
<head>
<title>Solicitud Contacto</title>
</head>
<body>
<h3>Mensaje</h3>
<p>".htmlspecialchars($_POST['mensaje'])."</p>
<table>
<tr>
<td>Nombre: ".htmlspecialchars($_POST['nombre'])."</td></tr>
<tr>
<td>Contacto: ".htmlspecialchars($_POST['telefono'])."</td></tr>\r\n
<tr>
<td>Correo: ".htmlspecialchars($_POST['correo'])."</td></tr>\r\n
</tr>
</table>
<br><br/>
".$firma."
</body>
</html>
";


//// Always set content-type when sending HTML email
//$headers = "MIME-Version: 1.0" . "\r\n";
////$headers .= "Content-type: text/html; charset=UTF-8" . "\r\n";
//$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
//
//$headers = "From:" . $from;

// Always set content-type when sending HTML email
$headers = "MIME-Version: 1.0" . "\r\n";
$headers .= "Content-type:text/html;charset=UTF-8" . "\r\n";
$headers .= "Content-type:text/html;charset=ISO-8859-1" . "\r\n";

// More headers
// More headers
$headers .= 'From: <sistesa@sistesa.com.co>' . "\r\n";
//$headers .= 'Cc: sistesa@sistesa.com.co' . "\r\n";
$headers .= 'Bcc: sistesa@sistesa.com.co, jaspart@gmail.com, bannyarva@gmail.com' . "\r\n";


mail($to, $subject, $message, $headers);


$toResponse=htmlspecialchars($_POST['correo']);
$subjectResponse="Sistesa da Confianza (Mensaje Respuesta Automática)";

$messageResponse = "
<html>
<head>
<title>Mensaje Recibido</title>
<style type='text/css'>
        a {
            color: #6B777A;
            text-decoration: none;
        }
        a:hover {
            color: #435152;
        }
</style>
</head>
<body>
<p>Hola ".htmlspecialchars($_POST['nombre'])."</p><br/>
<p>Hemos recibido su mensaje, en poco tiempo nos pondremos en contacto para asesorarlo en su necesidad</p>
".$firma."
</body>
</html>
";

mail($toResponse, $subjectResponse, $messageResponse, $headers);

echo "Mensaje Enviado Correctamente";
?>