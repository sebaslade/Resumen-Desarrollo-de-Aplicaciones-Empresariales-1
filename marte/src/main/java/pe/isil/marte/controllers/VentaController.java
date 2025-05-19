/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.isil.marte.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.isil.marte.beans.Producto;
import pe.isil.marte.beans.VentaDetalle;
import pe.isil.marte.model.AD_Producto;

/**
 *
 * @author pc
 */
@WebServlet(name = "VentaController", urlPatterns = {"/admin/ventas/*"})
public class VentaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VentaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VentaController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URL = request.getPathInfo();
        if(URL == null || URL.equals("/")){
            request.getRequestDispatcher("/ventas/index.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String URL = request.getPathInfo();
        try {
            //Buscador de productos
            if(URL == null || URL.equals("/")){
                //1. Obtener el parametro de busqueda de producto
                String p_nombre = request.getParameter("p_nombre"); //esta variable va ser enviada desde la vista
                AD_Producto ad_producto = new AD_Producto();
                ArrayList<Producto> productos = new ArrayList<>();
                
                //2. asignamos el resultado de la busqueda en productos
                if(p_nombre == null || p_nombre==""){
                    productos = ad_producto.getAll();
                }else{
                    productos = ad_producto.getByNombre(p_nombre);
                } 
                
                //3. Agregar el atributo productos para ser enviado a la vista
                request.setAttribute("productos", productos);
                
                //4. enviamos o derivamosa la vista-tabla.jsp
                request.getRequestDispatcher("/ventas/tabla.jsp").forward(request, response);
            }
            else{
                URL = request.getPathInfo();
                String accion = URL.substring(1);
                String ruta[] = accion.split("/");
                
                String producto_id=request.getParameter("producto_id");
                String cantidad = request.getParameter("cantidad");
                
                HttpSession sesion_carrito = request.getSession(true);
                ArrayList<VentaDetalle> carrito = new ArrayList<>();
                
                switch(ruta[0]){
                    case "agregar_producto":
                        if(sesion_carrito.getAttribute("carrito") != null){
                            carrito =(ArrayList<VentaDetalle>) sesion_carrito.getAttribute("carrito");
                        }
                        boolean existe = false;
                        int item = 0;
                        if(carrito != null){
                            for(int i = 0; i <carrito.size();i++){
                                if(carrito.get(i).getProducto_id() == Integer.parseInt(producto_id)){
                                    item = i;
                                    existe = true;
                                    break;
                                }
                            }
                        }
                        
                        if(existe){
                            carrito.get(item).setCantidad(carrito.get(item).getCantidad() + Integer.parseInt(cantidad));
                        }
                        else{
                            Producto producto = new Producto();
                            AD_Producto ad_producto = new AD_Producto();
                            producto = ad_producto.getById(Integer.parseInt(producto_id));
                            
                            VentaDetalle venta_detalle = new VentaDetalle();
                            venta_detalle.setProducto_id(Integer.parseInt(producto_id));
                            venta_detalle.setCantidad(Integer.parseInt(cantidad));
                            venta_detalle.setPrecio(producto.getPrecio());
                            venta_detalle.setTotal(producto.getPrecio() * Integer.parseInt(cantidad));
                            
                            carrito.add(venta_detalle);
                        }
                        sesion_carrito.setAttribute("carrito", carrito);
                        request.getRequestDispatcher("/ventas/carrito.jsp").forward(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
