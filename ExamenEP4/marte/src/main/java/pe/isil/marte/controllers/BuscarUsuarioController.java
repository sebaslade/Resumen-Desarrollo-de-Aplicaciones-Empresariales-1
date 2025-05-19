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
 * @author fercho
 */
@WebServlet(name = "BuscarUsuarioController", urlPatterns = {"/buscar_usuario"})
public class BuscarUsuarioController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
        request.getRequestDispatcher("/buscar_usuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //1. Todo lo convertimos en UTF-8
            request.setCharacterEncoding("UTF-8");

            //2. Capturar u obtener los datos del formulario es de decir cada input que tiene el name o id
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String celular = request.getParameter("celular");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String repetir_password = request.getParameter("password2");

            //Validamos que todos los campos son obligatorios, es decir deben tener valores
            if (nombre.trim() == "" || apellido.trim() == "" || celular.trim() == "" || email.trim() == "" || password.trim() == ""
                    || repetir_password.trim() == "") {
                request.setAttribute("mensaje_error", "Todos los campos son obligatorios");
                request.getRequestDispatcher("/buscar_usuario.jsp").forward(request, response);
            }

         
            //3. Crea el ad y registrar el usuario
            AD_Usuario ad_usuario = new AD_Usuario();
                      

            //validamos la existencia del email en base de datos
            if (ad_usuario.existeEmail(email) == true) {
                request.setAttribute("mensaje_error", "Ya existe un usuario registrado con ese email");
                request.getRequestDispatcher("/buscar_usuario.jsp").forward(request, response);
            }
            
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCelular(Integer.SIZE);
            usuario.setEmail(email);

            //3.1 encriptar el password
            StrongPasswordEncryptor encriptador = new StrongPasswordEncryptor();
            String PasswordEncriptado = encriptador.encryptPassword(password);
            //asignamos el password encriptado
            usuario.setPassword(PasswordEncriptado);

            //4. Insertamos o registramos al usuario en la base de datos
            ad_usuario.insertar(usuario);

            //5. redireccionar            
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("mensaje_exito", "Registro realizado con éxito!");
            response.sendRedirect(request.getContextPath() + "/login");

        } catch (SQLException ex) {
            Logger.getLogger(BuscarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
