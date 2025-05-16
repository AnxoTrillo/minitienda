<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="minitienda.*"%>

<html>
<head>
    <title>Error</title>
</head>
<body bgcolor="#FDF5E6">
    <div style="text-align:center;">
        <h1 style="color: black;">Ha habido un error al acceder a tu cuenta</h1>
        <p style="font-size: 18px;">La credenciales introducidas no son validas.</p>
        <p style="font-size: 18px;">Si ya tienes una cuenta, por favor <a href="Controlador?accion=toLogin">inicia sesion</a>.</p>
        <p style="font-size: 18px;">Si no tienes una cuenta, puedes <a href="Controlador?accion=toSignin">registrarte</a>.</p>
    </div>
</body>
</html>
