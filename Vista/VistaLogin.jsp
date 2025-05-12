<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="minitienda.*"%>

<html>
<head>
    <title>Log In</title>
</head>
<body style="background-color: #fdf5e6; font-family: Arial, sans-serif;">

    <div style="width: 300px; margin: 100px auto; padding: 20px; background-color: #ffffff; border: 1px solid #ddd; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); text-align: center;">
        <h2 style="color: #333;">Iniciar sesion</h2>

        <c:if test="${not empty error}">
            <p style="color: red; font-size: 14px;">${error}</p>
        </c:if>

        <form action="Controlador?accion=login" method="POST" style="margin-top: 20px;">
            <div style="margin-bottom: 15px;">
                <input type="email" name="correo" placeholder="Correo Electronico" required
                       style="width: 90%; padding: 8px; border-radius: 4px; border: 1px solid #ccc;">
            </div>

            <div style="margin-bottom: 15px;">
                <input type="password" name="password" placeholder="Contrasenha" required
                       style="width: 90%; padding: 8px; border-radius: 4px; border: 1px solid #ccc;">
            </div>

            <input type="submit" value="Iniciar sesion"
                   style="padding: 8px 16px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer;">
        </form>

        <p style="margin-top: 20px;">
            <a href="Controlador?accion=toSignin" style="color: #333;">Registrate aqui</a>
        </p>
    </div>

</body>
</html>
