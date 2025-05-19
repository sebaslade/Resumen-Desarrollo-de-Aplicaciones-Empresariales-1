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
        <title>Editar Curso</title>
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
                              Editar Curso
                            </div>
                            <div class="card-body">
                                <form action="http://localhost:8080/marte/admin/cursos/editar/<%=curso.getId() %>" method="post" autocomplete="off">
                                    <div class="form-group">
                                        <label>Nombre</label>
                                        <input type="text" class="form-control" id="nombre" name="nombre" value="<%=curso.getNombre() %>">
                                    </div>

                                    <div class="form-group">
                                        <label>Descripción</label>
                                        <input type="text" class="form-control" id="descripcion" name="descripcion" value="<%=curso.getDescripcion() %>">
                                    </div>

                                    <div class="form-group">
                                        <label>NRC</label>
                                        <input type="text" class="form-control" id="nrc" name="nrc" value="<%=curso.getNrc() %>">
                                    </div>

                                    <div class="form-group">
                                        <label>Profesor</label>
                                        <input type="text" class="form-control" id="profesor" name="profesor" value="<%=curso.getProfesor() %>">
                                    </div>

                                    <div class="form-group">
                                        <label>Créditos</label>
                                        <input type="text" class="form-control" id="creditos" name="creditos" value="<%=curso.getCreditos() %>">
                                    </div>

                                    <div class="form-group">
                                        <label>Horas</label>
                                        <input type="text" class="form-control" id="horas" name="horas" value="<%=curso.getHoras()%>">
                                    </div>

                                    <div class="form-group">
                                        <label>Horario</label>
                                        <input type="text" class="form-control" id="horario" name="horario" value="<%=curso.getHorario()%>">
                                    </div>
                                    
                                    <div class="justify-content-between mt-3">
                                        <button type="submit" class="btn btn-primary">Actualizar</button>
                                        <a href="http://localhost:8080/marte/admin/cursos" class="btn btn-secondary">Cancelar</a>
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
