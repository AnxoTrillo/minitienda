<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page session="true" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="minitienda.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored=“false”  %> 


<html>
    <head>
        <title>Caja</title>
    </head>
<body bgcolor="#FDF5E6">
    <h1 align="center">Caja</h2>

    <c:if test="${not empty sessionScope.carrito.listaCDs}">
        <c:set var="carrito" value="${sessionScope.carrito}"/>
        <c:set var="total" value="${carrito.totalAmount}"/>

        <table align="center" border="1" style="background-color: #ffffff;">
            <tr>
                <th>TOTAL A PAGAR</th>
            </tr>
            <tr>
                <td><c:out value="${total}"/> euros</td>
            </tr>
        </table>

        <figure style="text-align: center; margin-right: 20px;">
            <a href="confirmarCompra"><img src="/minitienda/imagenes/musica.gif" alt="Pagar y volver a la página principal" style="height:150px;"></a>
            <figcaption><a href="confirmarCompra">Pagar y volver a la pagina principal</a></figcaption>
        </figure>
    </c:if>
</body>
</html>
