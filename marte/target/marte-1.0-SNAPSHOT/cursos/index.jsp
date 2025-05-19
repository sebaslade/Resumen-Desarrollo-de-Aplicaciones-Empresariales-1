<%@page import="pe.isil.marte.beans.Curso"%>
<%@page import="java.util.List"%>
<%
    List<Curso> listado_cursos = (List<Curso>)request.getAttribute("listado_cursos");
    
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Cursos</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
              crossorigin="anonymous" 
              referrerpolicy="no-referrer" />
    </head>
    <body>        
        <div class="container">
            <div class="container-fluid">
                <h1>Cursos</h1>

                <div class="mt-3 mb-3">
                    <a href="http://localhost:8080/marte/admin/cursos/nuevo" class="btn btn-success"> Nuevo Curso</a>
                </div>

                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>NRC</th>
                            <th>NOMBRE</th>
                            <th>OPCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        for(Curso curso : listado_cursos)
                        {
                        %>
                        <tr>
                            <td> <%=curso.getId() %> </td>
                            <td> <%=curso.getNrc() %> </td>
                            <td> <%=curso.getNombre() %> </td>
                            <td>
                                <a href="http://localhost:8080/marte/admin/cursos/editar/<%=curso.getId() %>"
                                   class="btn btn-primary"><i class="fas fa-edit"></i></a>
                                <a href="http://localhost:8080/marte/admin/cursos/eliminar/<%=curso.getId() %>"
                                   class="btn btn-danger"><i class="fas fa-trash"></i></a>
                            </td>
                        </tr>
                        <%
                        }
                        %>
                    </tbody>
                </table>
            </div>    
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
                
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/js/all.min.js" 
                integrity="sha512-6sSYJqDreZRZGkJ3b+YfdhB3MzmuP9R7X1QZ6g5aIXhRvR1Y/N/P47jmnkENm7YL3oqsmI6AK+V6AD99uWDnIw==" 
                crossorigin="anonymous" 
                referrerpolicy="no-referrer"></script>
    </body>
</html>
