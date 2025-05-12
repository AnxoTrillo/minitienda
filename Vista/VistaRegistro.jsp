<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="minitienda.*"%>

<html>
<head>
    <title>Sign In</title>
</head>
<body>
    <h2>Registrarse</h2>
    <form action="Controlador?accion=signin" method="POST">
        Correo: <input type="email" name="correo" required><br><br>
        Contraseña: <input type="password" name="clave" required><br><br>
        Tipo de tarjeta:
        <select name="tipo_tarjeta" required>
            <option value="Visa">Visa</option>
            <option value="MasterCard">MasterCard</option>
        </select><br><br>
        Número de tarjeta: <input type="text" name="numero_tarjeta" required pattern="\\d+"><br><br>
        <input type="submit" value="Registrarse">
    </form>
</body>
</html>