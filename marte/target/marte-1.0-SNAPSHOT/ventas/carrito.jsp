<%@page import="pe.isil.marte.beans.VentaDetalle"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<VentaDetalle> detalle = new ArrayList<>();
    detalle = (ArrayList<VentaDetalle>) request.getAttribute("carrito");
%>

<table class="table table-bordered table-hover">
    <tr>
        <th>PRODUCTO</th>
        <th>CANTIDAD</th>
        <th>PRECIO</th>
        <th>TOTAL</th>
    </tr>
    <%
        Double total_venta = 0.0;
        for(VentaDetalle producto : detalle){
            total_venta = total_venta = producto.getTotal();
    %>
    <tr>
        <td><%=producto.getProducto_id()%></td>
        <td><%=producto.getCantidad()%></td>
        <td><%=producto.getPrecio()%></td>
        <td><%=producto.getTotal()%></td>
    </tr>
    <%
        
        }
    %>
    <tr>
        <td colspan="3" align="right"><b>MONTO TOTAL</b></td>
        <td><b><%=total_venta%></b></td>
    </tr>
</table>