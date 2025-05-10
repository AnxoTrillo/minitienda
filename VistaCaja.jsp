<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page session="true" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.StringTokenizer"%>

<html>
    <head>
        <title>Caja</title>
    </head>
<body bgcolor="#FDF5E6">
    <h1 align="center">Caja</h2>
    <% HashMap<String, Integer> carrito = (HashMap<String, Integer>) session.getAttribute("carrito");
        float total = 0;
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
        }
    %>

    <table align="center" border="1" style="background-color: #ffffff;">
        <tr>
            <th>TOTAL A PAGAR</th>
        </tr>
        <tr>
            <td><%= String.format("%.2f", total)%> euros</td>
        </tr>
    </table>

    <figure style="text-align: center; margin-right: 20px;">
        <a href="confirmarCompra"><img src="/minitienda/imagenes/musica.gif" alt="Pagar y volver a la página principal" style="height:150px;"></a>
        <figcaption><a href="confirmarCompra">Pagar y volver a la pagina principal</a></figcaption>
    </figure>
</body>
</html>
