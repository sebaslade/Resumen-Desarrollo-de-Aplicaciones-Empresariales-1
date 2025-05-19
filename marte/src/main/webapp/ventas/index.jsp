<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-Commerce</title>
        
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
                <h1>Ventas</h1>
                <div class="row">
                    <div class="col-6">
                        <div class="form-group">
                            <div class="input-group">
                                <input type="search" class="form-control" name="producto" id="producto" placeholder="Buscar...">
                                <div class="input-group-addon">
                                    <button type="button" class="btn btn-default" onclick="BuscarProducto()">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </div>
                            </div>
                            <div>
                                <table class="table table-hover tabler-border table-sm">
                                    <thead>
                                        <th>ID</th>
                                        <th>PRODUCTO</th>
                                        <th>PRECIO</th>
                                        <th>CANTIDAD</th>
                                    </thead>
                                    <tbody id="div_productos">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        
                    </div>
                </div>
                
            </div>    
        </div>

        <script>
            function BuscarProducto(){
                const nombre = document.getElementById('producto').value;
                const enlace = "${URL_APLICACION}/admin/ventas";
                $.ajax({
                    method:"POST",
                    url:enlace,
                    data:{
                        "p_nombre":nombre
                    }
                }).done(
                    function(resultado){
                        $('#div_productos').html(resultado)
                    }
                );
            }
            
            function AgregarCarrito(id){
                const enlace = "${URL_APLICACION}/admin/ventas/agregar_producto";
                $.ajax({
                    method:"POST",
                    url:enlace,
                    data:{
                        "producto_id" : id,
                        "cantidad" : $("#txtCantidad"+id).val()
                    }
                }).done(
                    function(resultado){
                        $('#div_carrito').html(resultado)
                    }
                );
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
                
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/js/all.min.js" 
                integrity="sha512-6sSYJqDreZRZGkJ3b+YfdhB3MzmuP9R7X1QZ6g5aIXhRvR1Y/N/P47jmnkENm7YL3oqsmI6AK+V6AD99uWDnIw==" 
                crossorigin="anonymous" 
                referrerpolicy="no-referrer"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" 
                integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" 
                crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </body>
</html>
