<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<html>
<head>
    <title>Pedido Confirmado</title>
</head>
<body bgcolor="#FDF5E6">
    <h1 align="center">Pedido Confirmado</h1>

    <c:if test="${not empty requestScope.pedido}">
        <table align="center" border="1" style="background-color: #ffffff; margin-top: 20px;">
            <tr>
                <th colspan="2">Detalles del Pedido</th>
            </tr>
            <tr>
                <td><strong>ID Pedido</strong></td>
                <td>${pedido.id}</td>
            </tr>
            <tr>
                <td><strong>Usuario</strong></td>
                <td>${pedido.usuario}</td>
            </tr>
            <tr>
                <td><strong>Total</strong></td>
                <td>
                    <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${pedido.total}"/> euros
                </td>
            </tr>
        </table>
    </c:if>

    <figure style="text-align: center; margin-top: 30px;">
        <a href="index.html">
            <img src="/minitienda/imagenes/musica.gif" alt="Volver a la tienda" style="height:150px;">
        </a>
        <figcaption><a href="index.html">Volver a la pagina principal</a></figcaption>
    </figure>
</body>
</html>
