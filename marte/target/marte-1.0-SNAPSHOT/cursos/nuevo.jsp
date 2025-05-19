<%-- 
    Document   : nuevo
    Created on : 22 oct 2024, 7:46:29 p.m.
    Author     : fercho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Curso</title>
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
                              Nuevo Curso
                            </div>
                            <div class="card-body">
                                <form action="http://localhost:8080/marte/admin/cursos/nuevo" method="post" autocomplete="off">
                                    <div class="form-group">
                                        <label>Nombre</label>
                                        <input type="text" class="form-control" id="nombre" name="nombre">
                                    </div>

                                    <div class="form-group">
                                        <label>Descripción</label>
                                        <input type="text" class="form-control" id="descripcion" name="descripcion">
                                    </div>

                                    <div class="form-group">
                                        <label>NRC</label>
                                        <input type="text" class="form-control" id="nrc" name="nrc">
                                    </div>

                                    <div class="form-group">
                                        <label>Profesor</label>
                                        <input type="text" class="form-control" id="profesor" name="profesor">
                                    </div>

                                    <div class="form-group">
                                        <label>Créditos</label>
                                        <input type="text" class="form-control" id="creditos" name="creditos">
                                    </div>

                                    <div class="form-group">
                                        <label>Horas</label>
                                        <input type="text" class="form-control" id="horas" name="horas">
                                    </div>

                                    <div class="form-group">
                                        <label>Horario</label>
                                        <input type="text" class="form-control" id="horario" name="horario">
                                    </div>
                                    
                                    <div class="justify-content-between mt-3">
                                        <button type="submit" class="btn btn-primary">Registrar</button>
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
