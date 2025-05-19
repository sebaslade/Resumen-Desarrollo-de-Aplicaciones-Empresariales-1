<%@page import="pe.isil.marte.beans.Curso"%>
<%
    Curso curso=new Curso();
    curso = (Curso) request.getAttribute("curso");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Curso</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
    </head>
    <body>
        
        <div class="container">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-12">
                        <div class="card mt-3">
                            <div class="card-header">
                              Eliminar Curso
                            </div>
                            <div class="card-body">
                                <form action="http://localhost:8080/marte/admin/cursos/eliminar/<%=curso.getId() %>" method="post" autocomplete="off">
                                    <div class="form-group">
                                        <label>¿Estás seguro de eliminar el curso: <%=curso.getNombre() %>?</label>
                                    </div>
                                    <div class="justify-content-between mt-3">
                                        <button type="submit" class="btn btn-primary">Si</button>
                                        <a href="http://localhost:8080/marte/admin/cursos" class="btn btn-secondary">No</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>            
        </div>
        
        
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
