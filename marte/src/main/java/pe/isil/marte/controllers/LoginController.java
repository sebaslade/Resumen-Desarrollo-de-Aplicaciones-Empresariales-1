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
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        //redireccionamos la pagina de inicio de sesion: login.jsp
        request.getRequestDispatcher("/login.jsp").forward(request, response);
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
            //1. Capturar y validar los campos: usuario(email) y password
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            if(email.trim()=="" || password.trim() ==""){
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("mensaje_error","Por favor ingrese usuario y contraseña");
                response.sendRedirect(request.getContextPath()+"/login");
            }else{
                //2. Validar que el usuario
                AD_Usuario ad_usuario = new AD_Usuario();

                //2.1 declaramos un objeto y a la vez buscamos el usuario por email
                Usuario usuarioEncontrado = ad_usuario.login(email);

                if(usuarioEncontrado == null){
                    //crear un mensaje tipo flash de error
                    HttpSession sesion = request.getSession(true);
                    sesion.setAttribute("mensaje_error","Datos incorrectos, o email no existe");
                    response.sendRedirect(request.getContextPath()+"/login");
                }else{
                    //3. Validar que la contraseña ingresada en el form del login coincida con la de la base de datos que esta en usuarioEncontrado
                    StrongPasswordEncryptor encriptador = new StrongPasswordEncryptor();
                    if(encriptador.checkPassword(password, usuarioEncontrado.getPassword())){
                        //si coincide, creamos en la sesion
                        //4.Creamos un inicio de sesion
                        HttpSession sesion = request.getSession(true);
                        sesion.setAttribute("usuario_logueado",usuarioEncontrado);
                        response.sendRedirect(request.getContextPath()+"/admin");
                    }else{
                        //crear un mensaje tipo flash error
                        HttpSession sesion = request.getSession(true);
                        sesion.setAttribute("mensaje_error", "Datos incorrectos, la contraseña es incorrecta");
                        response.sendRedirect(request.getContextPath()+"/login");
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
