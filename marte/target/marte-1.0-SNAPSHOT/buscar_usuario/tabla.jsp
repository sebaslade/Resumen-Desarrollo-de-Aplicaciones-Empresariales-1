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
    <td><%=usuario.getApellidos()%></td>
    <td><%=usuario.getEmail()%></td>
    <td><%=usuario.getCelular()%></td>
</tr>
<%
    }
%>