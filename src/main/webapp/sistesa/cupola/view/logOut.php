<?php

include "../lib/config.php";

session_destroy();
header('Location: ../index.html');
