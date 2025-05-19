<%@page import="pe.isil.marte.beans.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Usuario> usuarios= new ArrayList<>();
    usuarios = (ArrayList<Usuario>)request.getAttribute("usuarios");
%>
<%
    for(Usuario usuario : usuarios){
%>
<tr>
    <td><%=usuario.getNombre()%></td>
    <td><%=usuario.getApellido()%></td>
    <td><%=usuario.getEmail()%></td>
    <td><%=usuario.getCelular()%></td>
    <!--td>
        <input type="number" class="form-control input-sm" id="txtCantidad<%=usuario.getId()%>" value="1" min="1">
    </td-->
    <!--td>
        <button class="btn btn-primary btn-sm" type="button" onclick="AgregarCarrito(<%=usuario.getId()%>)">
            <i class="fa fa-plus-circle"></i>
        </button>
    </td-->
</tr>
<%
    }
%>