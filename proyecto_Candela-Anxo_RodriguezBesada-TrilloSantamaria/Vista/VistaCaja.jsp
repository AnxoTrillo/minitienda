<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page session="true" %>
<%@page import="minitienda.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page isELIgnored="false"%>


<html>
    <head>
        <title>Caja</title>
    </head>
<body bgcolor="#FDF5E6">
    <h1 align="center">Caja</h1>

    <c:if test="${not empty sessionScope.carrito.listaCD}">
        <c:set var="carrito" value="${sessionScope.carrito}"/>
        <c:set var="total" value="${carrito.totalAmount}"/>

        <table align="center" border="1" style="background-color: #ffffff;">
            <tr>
                <th>TOTAL A PAGAR</th>
            </tr>
            <tr>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${total}"/> euros</td>
            </tr>
        </table>

        <figure style="text-align: center; margin-right: 20px;">
            <a href="Controlador?accion=toLogin"><img src="/minitienda/imagenes/musica.gif" alt="Pagar y volver a la página principal" style="height:150px;"></a>
            <figcaption><a href="Controlador?accion=toLogin">Pagar y volver a la pagina principal</a></figcaption>
        </figure>
    </c:if>
</body>
</html>
