<?php require "layout/header.php" ?>
<h1>NUEVO</h1>
<hr>
<form action="">
	<label for="">NOMBRE</label> <br>
	<input type="text" name="nombre"><br>
	<label for="">PRECIO</label><br>
	<input type="text" name="precio"><br>
	<input type="submit" name="btn">
	<input type="hidden" name="m" value="guardar">
</form>
<?php require "layout/footer.php" ?>