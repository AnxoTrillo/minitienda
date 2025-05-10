<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="java.util.HashMap"%>
<%@page import="java.util.StringTokenizer"%>
<%@page session="true" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carrito de Compras</title>
</head>
<body bgcolor="#FDF5E6">
    <% HashMap<String, Integer> carrito = (HashMap<String, Integer>) session.getAttribute("carrito");
        if (carrito != null && carrito.size() > 0) {
    %>
    <h1 align="center">Carrito de Compras</h2>
    <table align="center" border="1" width="100%" style="background-color: #ffffff;">
        <tr>
            <th>Titulo del CD</th>
            <th>Cantidad</th>
            <th style=\"width: 100px;\">Importe</th>
            <th style=\"width: 80px;\">Eliminar</th>
        </tr>

    <% float total = 0;
        String[] keys = carrito.keySet().toArray(new String[carrito.size()]);
        for (int i = 0; i < carrito.size(); i++) {
            String item = keys[i];
            int cantidad = carrito.get(item);
            StringTokenizer t = new StringTokenizer(item,"|");
            t.nextToken();
            t.nextToken();
            t.nextToken();
            String precioString = t.nextToken();
            precioString = precioString.replace('$',' ').trim();
            float precio = Float.parseFloat(precioString);
            float importe = precio * cantidad;
            total += importe;
    %>
        <tr>
            <td><%= item %></td>
            <td><%= cantidad %></td>
            <td style="width: 100px;"><%= String.format("%.2f", importe) %> euros</td>
            <td style="width: 80px;">
                <form action="ver_carro" method="POST">
                    <input type="hidden" name="descripcionCD" value= "<%= item %>">
                    <input type="submit" value="Eliminar">
                </form>
            </td>
        </tr>

    <% } %>
        <tr>
            <td></td>
            <td>IMPORTE TOTAL</td>
            <td><%= String.format("%.2f", total) %> euros</td>
            <td></td>
        </tr>
    </table>

    <div style="display: flex; justify-content: center;">
        <figure style="text-align: center; margin-right: 20px;">
            <a href="index.html"><img src="imagenes/carrito_compra.gif" alt="Seguir comprando" style="height:150px;"></a>
            <figcaption><a href="index.html">Seguir comprando</a></figcaption>
        </figure>
        <figure style="text-align: center; margin-left: 20px; ">
            <a href="pagar"><img src="imagenes/pago_importe2.jpg" alt="Me largo a pagar" style="height: 150px;"></a>
            <figcaption><a href="pagar">Me largo a pagar</a></figcaption>
        </figure>
    <% }
        else {
    %>

        <center>
            <h2>Carrito de Compras Vacio</h2>
            <p>No hay productos en el carrito de compras.</p>
            <p>Para agregar productos, visite la <a href="index.html">Tienda</a>.</p>
        </center>
    <% } %>

    </div>
</body>
</html>



