<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="minitienda.*"%>

<html>
<head>
    <title>Sign In</title>
</head>
<body style="background-color: #fdf5e6; font-family: Arial, sans-serif;">

    <div style="width: 350px; margin: 80px auto; padding: 20px; background-color: #ffffff; border: 1px solid #ddd; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); text-align: center;">
        <h2 style="color: #333;">Registrarse</h2>

        <form action="Controlador?accion=confirmarCompra" method="POST" style="margin-top: 20px;">
            <div style="margin-bottom: 15px;">
                <input type="email" name="correo" placeholder="Correo Electronico" required
                       style="width: 90%; padding: 8px; border-radius: 4px; border: 1px solid #ccc;">
            </div>

            <div style="margin-bottom: 15px;">
                <input type="password" name="password" placeholder="Contrasenha" required
                       style="width: 90%; padding: 8px; border-radius: 4px; border: 1px solid #ccc;">
            </div>

            <div style="margin-bottom: 15px;">
                <select name="tipo_tarjeta" required
                        style="width: 90%; padding: 8px; border-radius: 4px; border: 1px solid #ccc;">
                    <option value="" disabled selected>Tipo de tarjeta</option>
                    <option value="Visa">Visa</option>
                    <option value="MasterCard">MasterCard</option>
                </select>
            </div>

            <div style="margin-bottom: 15px;">
                <input type="text" name="numero_tarjeta" placeholder="Numero de tarjeta" required
                       style="width: 90%; padding: 8px; border-radius: 4px; border: 1px solid #ccc;">
            </div>

            <input type="submit" value="Registrarse"
                   style="padding: 8px 16px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer;">

        </form>
        <p style="margin-top: 20px;">
            <a href="Controlador?accion=toLogin" style="color: #333;">Inicia sesion aqui</a>
        </p>
    </div>

</body>
</html>
