<%@page import="java.util.ArrayList"%>
<%@page import="pe.isil.marte.beans.Producto"%>
<%
    ArrayList<Producto> productos= new ArrayList<>();
    productos = (ArrayList<Producto>)request.getAttribute("productos");
%>
<%
    for(Producto producto : productos){
%>
<tr>
    <td><%=producto.getId()%></td>
    <td><%=producto.getNombre()%></td>
    <td><%=producto.getPrecio()%></td>
    <td>
        <input type="number" class="form-control input-sm" id="txtCantidad<%=producto.getId()%>" value="1" min="1">
    </td>
    <td>
        <button class="btn btn-primary btn-sm" type="button" onclick="AgregarCarrito(<%=producto.getId()%>)">
            <i class="fa fa-plus-circle"></i>
        </button>
    </td>
</tr>
<%
    }
%>