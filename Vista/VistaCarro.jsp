<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="java.util.HashMap"%>
<%@page import="java.util.StringTokenizer"%>
<%@page session="true"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="minitienda.*"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Carrito de Compras</title>
</head>



<body bgcolor="#FDF5E6">
    <c:if test="${not empty sessionScope.carrito.listaCD}">
        <c:set var="carrito" value="${sessionScope.carrito}"/>

        <h1 align="center">Carrito de Compras</h2>
        <table align="center" border="1" width="100%" style="background-color: #ffffff;">
            <tr>
                <th>Titulo del CD</th>
                <th>Cantidad</th>
                <th style=\"width: 100px;\">Importe</th>
                <th style=\"width: 80px;\">Eliminar</th>
            </tr>

            <c:set var="total" value="${carrito.totalAmount}" />

            <c:forEach items="${carrito.listaCD}" var="CDActual">
                <tr>
                    <td><c:out value="${CDActual.key.descripcion}"/></td>
                    <td><c:out value="${CDActual.value}"/></td>
                    <td style="width: 100px;"><c:out value="${CDActual.key.precio}"/> euros</td>
                    <td style="width: 80px;">
                        <form action="Controlador?accion=deleteCD" method="POST">
                            <input type="hidden" name="descripcionCD" value="${CDActual.key.descripcion}">
                            <input type="submit" name="eliminar" value="Eliminar">
                        </form>
                    </td>
                </tr>

            </c:forEach>

            <tr>
                <td></td>
                <td>IMPORTE TOTAL</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${total}"/> euros</td>
                <td></td>
            </tr>
        </table>

        <div style="display: flex; justify-content: center;">
            <figure style="text-align: center; margin-right: 20px;">
                <a href="index.html"><img src="imagenes/carrito_compra.gif" alt="Seguir comprando" style="height:150px;"></a>
                <figcaption><a href="index.html">Seguir comprando</a></figcaption>
            </figure>
            <figure style="text-align: center; margin-left: 20px; ">
                <a href="Controlador?accion=checkout"><img src="imagenes/pago_importe2.jpg" alt="Me largo a pagar" style="height: 150px;"></a>
                <figcaption><a href="Controlador?accion=checkout">Me largo a pagar</a></figcaption>
            </figure>
    
    </c:if>
    <c:if test="${empty sessionScope.carrito.listaCD}">

        <center>
            <h2>Carrito de Compras Vacio</h2>
            <p>No hay productos en el carrito de compras.</p>
            <p>Para agregar productos, visite la <a href="index.html">Tienda</a>.</p>
        </center>

    </c:if>
    </div>
</body>
</html>



