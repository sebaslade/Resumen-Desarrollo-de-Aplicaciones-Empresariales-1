<%@page import="pe.isil.cliente.beans.Cliente"%>
<%@page import="java.util.List"%>
<%
    List<Cliente> listado_clientes = (List<Cliente>)request.getAttribute("listado_clientes");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Clientes</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <h1>Clientes</h1>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NOMBRE</th>
                    <th>APELLIDO</th>
                    <th>EMAIL</th>
                    <th>TELEFONO</th>
                    <th>GENERO</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for(Cliente cliente: listado_clientes){
                    %>
                    <tr>
                        <td><%=cliente.getId() %></td>
                        <td><%=cliente.getNombre()%></td>
                        <td><%=cliente.getApellido()%></td>
                        <td><%=cliente.getEmail()%></td>
                        <td><%=cliente.getTelefono()%></td>
                        <td><%=cliente.getGenero()%></td>
                        <td></td>
                    </tr>
                    <%
                    }
                %>
            </tbody>
        </table>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>

