<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="minitienda.*"%>

<html>
<head>
    <title>Log In</title>
</head>
<body>
    <h2>Iniciar sesión</h2>
    <form action="Controlador?accion=login" method="POST">
        Correo: <input type="email" name="correo" required><br><br>
        Contraseña: <input type="password" name="password" required><br><br>
        <input type="submit" value="Iniciar sesión">
    </form>

    <p>¿No tienes cuenta? <a href="/Vista/VistaRegistro.jsp">Regístrate aquí</a></p>
</body>
</html>