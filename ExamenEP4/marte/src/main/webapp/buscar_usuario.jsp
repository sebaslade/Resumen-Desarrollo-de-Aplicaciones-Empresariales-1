<%
    String mensaje = "";
    if (request.getAttribute("mensaje_error") != null) {
        mensaje = (String)request.getAttribute("mensaje_error");
        //remuevo el atributo, porque solo quiero que se muestre una sola vez
        request.removeAttribute("mensaje_error");
    }

%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        
        <div class="container-fluid ">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-4 col-md-6 col-sm-12 ">
                    <div class="card mt-3 ">
                        <div class="card-header">
                            Registro de Usuario
                        </div>
                        <div class="card-body">
                            
                            <%
                                if (mensaje != "") 
                                {
                            %>
                                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                        <%=mensaje%>
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                          <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                            <%
                                }
                            %>
                            
                            <form action="" method="post" autocomplete="off" id="registro">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Nombre" name="nombre" id="nombre">
                                    <div class="input-group-append">
                                        <div class="input-group-text">
                                            <span class="fas fa-user"></span>
                                        </div>    
                                    </div>
                                </div>
                                
                                 <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Apellido" name="apellido" id="Apellido">
                                    <div class="input-group-append">
                                        <div class="input-group-text">
                                            <span class="fas fa-user"></span>
                                        </div>    
                                    </div>
                                </div>
                                
                                 <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Celular" name="celular" id="celular">
                                    <div class="input-group-append">
                                        <div class="input-group-text">
                                            <span class="fas fa-user"></span>
                                        </div>    
                                    </div>
                                </div>

                                <div class="input-group mb-3">
                                    <input type="email" class="form-control" placeholder="Email" name="email" id="email">
                                    <div class="input-group-append">
                                        <div class="input-group-text">
                                            <span class="fas fa-envelope"></span>
                                        </div>    
                                    </div>
                                </div>

                                <div class="input-group mb-3">
                                    <input type="password" class="form-control" placeholder="Password" name="password" id="password">
                                    <div class="input-group-append">
                                        <div class="input-group-text">
                                            <span class="fas fa-lock"></span>
                                        </div>    
                                    </div>
                                </div>
                                
                                <div class="input-group mb-3">
                                    <input type="password" class="form-control" placeholder="Repite Password" name="password2" id="password2">
                                    <div class="input-group-append">
                                        <div class="input-group-text">
                                            <span class="fas fa-lock"></span>
                                        </div>    
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-6">
                                        <button type="submit" class="btn btn-primary btn-block">Registrar</button>
                                    </div>    
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/js/all.min.js" 
        integrity="sha512-6sSYJqDreZRZGkJ3b+YfdhB3MzmuP9R7X1QZ6g5aIXhRvR1Y/N/P47jmnkENm7YL3oqsmI6AK+V6AD99uWDnIw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    
    </body>
</html>
