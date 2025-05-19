<%@page import="pe.isil.marte.beans.Categoria"%>
<%@page import="java.util.List"%>
<%
    List<Categoria> listado_categorias = (List<Categoria>)request.getAttribute("listado_categorias");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Categorias</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
    </head>
    <body>
        <h1>Categorias</h1>
        
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NOMBRE</th>
                    <th>OPCIONES</th>
                </tr>
            </thead>
            <tbody>
                <%
                for(Categoria categoria : listado_categorias)
                {
                %>
                <tr>
                    <td> <%=categoria.getId() %> </td>
                    <td> <%=categoria.getNombre() %> </td>
                    <td></td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
