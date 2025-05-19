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
import pe.isil.marte.beans.Producto;
import pe.isil.marte.beans.Usuario;
import pe.isil.marte.model.AD_Producto;
import pe.isil.marte.model.AD_Usuario;

/**
 *
 * @author pc
 */
@WebServlet(name = "BuscarUsuarioController", urlPatterns = {"/admin/buscar_usuario/*"})
public class BuscarUsuarioController extends HttpServlet {

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
            out.println("<title>Servlet BuscarUsuarioController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuscarUsuarioController at " + request.getContextPath() + "</h1>");
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
            request.getRequestDispatcher("/buscar_usuario/index.jsp").forward(request, response);
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
                String u_nombre = request.getParameter("u_nombre"); 
                String u_apellido = request.getParameter("u_apellido");
                AD_Usuario ad_usuario = new AD_Usuario();
                ArrayList<Usuario> usuarios = new ArrayList<>();
                
                if ((u_nombre == null || u_nombre.isEmpty()) && (u_apellido == null || u_apellido.isEmpty())) {
                    usuarios = ad_usuario.getAll();
                }else{
                    usuarios = ad_usuario.getByNombreApellido(u_nombre, u_apellido);
                } 
                
                request.setAttribute("usuarios", usuarios);
                
                request.getRequestDispatcher("/buscar_usuario/tabla.jsp").forward(request, response);
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
