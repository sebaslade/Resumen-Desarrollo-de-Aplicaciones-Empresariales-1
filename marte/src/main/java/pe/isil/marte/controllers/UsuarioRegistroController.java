/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.isil.marte.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jasypt.util.password.StrongPasswordEncryptor;
import pe.isil.marte.beans.Usuario;
import pe.isil.marte.model.AD_Usuario;

/**
 *
 * @author pc
 */
@WebServlet(name = "UsuarioRegistroController", urlPatterns = {"/registro_usuario"})
public class UsuarioRegistroController extends HttpServlet {

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
            out.println("<title>Servlet UsuarioRegistroController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioRegistroController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/registro_usuario.jsp").forward(request,response);
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
        try {
            //1. Todo lo convertimos a UTF-8
            request.setCharacterEncoding("UTF-8");

            //2. Capturar u obtener los datos del formulario es de decir cada input que tiene el name o id
            String nombre = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String celular = request.getParameter("celular");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String repetir_password = request.getParameter("password2");

            if (nombre.trim().isEmpty() || apellidos.trim().isEmpty() || celular.trim().isEmpty() || 
                email.trim().isEmpty() || password.trim().isEmpty() || repetir_password.trim().isEmpty()) {
                request.setAttribute("mensaje_error", "Todos los campos son obligatorios");
                request.getRequestDispatcher("/registro_usuario.jsp").forward(request, response);
                return;
            }

            if (!password.equals(repetir_password)) {
                request.setAttribute("mensaje_error", "Las contraseñas no coinciden");
                request.getRequestDispatcher("/registro_usuario.jsp").forward(request, response);
                return;
            }

            AD_Usuario ad_usuario = new AD_Usuario();

            if (ad_usuario.existeEmail(email)) {
                request.setAttribute("mensaje_error", "Ya existe un usuario registrado con ese email");
                request.getRequestDispatcher("/registro_usuario.jsp").forward(request, response);
                return;
            }

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            usuario.setCelular(celular);
            usuario.setEmail(email);

            StrongPasswordEncryptor encriptador = new StrongPasswordEncryptor();
            String passwordEncriptado = encriptador.encryptPassword(password);
            usuario.setPassword(passwordEncriptado);

            ad_usuario.insertar(usuario);

            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("mensaje_exito", "Registro realizado con éxito!");
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRegistroController.class.getName()).log(Level.SEVERE, null, ex);
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
