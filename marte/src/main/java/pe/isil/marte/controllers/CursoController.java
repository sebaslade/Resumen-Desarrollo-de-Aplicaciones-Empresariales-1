/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.isil.marte.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.isil.marte.beans.Curso;
import pe.isil.marte.model.AD_Curso;

/**
 *
 * @author fercho
 */
@WebServlet(name = "CursoController", urlPatterns = {"/admin/cursos/*"})
public class CursoController extends HttpServlet {

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
            out.println("<title>Servlet CursoController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CursoController Fercho at " + request.getContextPath() + "</h1>");
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
        try{
            //LISTA CURSOS        
            //FUNCION: OBTENER TODOS LOS CURSOS DE LA BASE DE DATOS
            //DESCRIPCION: OBTENER TODOS LOS CURSOS DE LA BASE DE DATOS Y MOSTRAR EN EL FORMULARIO WEB
            //URL: http://localhost:8080/marte/admin/cursos  o  http://localhost:8080/marte/admin/cursos/
            //METODO O VERBO HTTP : GET
            //VISTA: /cursos/index.jsp


            String URL = request.getPathInfo(); // Obtiente la subruta que aparece despues de admin/cursos

            if (URL == null || URL.equals("/") ) {
                //LISTA CURSOS
                //1. obtener los cursos de la base de datos
                AD_Curso aD_Curso = new AD_Curso(); //Creamos el acceso a datos de cursos
                List<Curso> Cursos = new ArrayList<Curso>(); // creamos un listado de cursos
                Cursos = aD_Curso.getCursos(); // asignamos los curso de datos al listado

                //2. enviar los cursos a la VISTA

                //2.1 agregar como atributo al request la lista de cursos
                request.setAttribute("listado_cursos", Cursos);

                //2.2 enviar el listado de curso al front o vista
                request.getRequestDispatcher("/cursos/index.jsp").forward(request, response);
            }
        
            //NUEVO CURSO       
            //FUNCION: NUEVO CURSO
            //DESCRIPCION: Carga un formulario en blanco, para agregar un nuevo curso, redireccionar al html nuevo.jsp
            //URL: http://localhost:8080/marte/admin/cursos/nuevo
            //METODO O VERBO HTTP : GET
            //VISTA: /cursos/nuevo.jsp

            if (URL.equals("/nuevo")) {
                //Redireccionamos a la vista nuevo.jsp
                request.getRequestDispatcher("/cursos/nuevo.jsp").forward(request, response);
            }
        
            //Editar Curso       
            //FUNCION: EDITAR CURSO
            //DESCRIPCION: Obtener el curso por ID y cargar los datos en nuevo formulario
            //URL: http://localhost:8080/marte/admin/cursos/editar/{id}
            //METODO O VERBO HTTP : GET
            //VISTA: /cursos/editar.jsp

            String accion = URL.substring(1); // -> editar/id
            String[] ruta = accion.split("/"); //-> ruta[0]="editar"; ruta[1]="id"
            if(ruta[0].equals("editar")){
                //1. obtenemos el ID
                Integer id_curso=Integer.parseInt(ruta[1]);
                //2. obtiene el curso por el id de la bd
                AD_Curso ad_curso = new AD_Curso();
                Curso curso = new Curso();
                curso=ad_curso.getCursoById(id_curso);
                //3.Vamos agregar un atributo curso
                request.setAttribute("curso", curso);
                //4.Redireccionamos a la vista del editar.jsp
                request.getRequestDispatcher("/cursos/editar.jsp").forward(request, response);
            }
            
            //ELIMINAR Curso       
            //FUNCION: ELIMINAR Curso       
            //DESCRIPCION: ELIMINAR
            //URL: http://localhost:8080/marte/admin/cursos/editar/{id}
            //METODO O VERBO HTTP : GET
            //VISTA: /cursos/eliminar.jsp
            
            if(ruta[0].equals("eliminar")){
                //1. obtenemos el ID
                Integer id_curso=Integer.parseInt(ruta[1]);
                //2. obtiene el curso por el id de la bd
                AD_Curso ad_curso = new AD_Curso();
                Curso curso = new Curso();
                curso=ad_curso.getCursoById(id_curso);
                //3.Vamos agregar un atributo curso
                request.setAttribute("curso", curso);
                //4.Redireccionamos a la vista del editar.jsp
                request.getRequestDispatcher("/cursos/eliminar.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
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
        //REGISTAR NUEVO CURSO       
        //FUNCION: REGISTRAR NUEVO CURSO
        //DESCRIPCION: REGISTRAR UN NUEVO CURSO EN LA BASE DE DATOS
        //URL: http://localhost:8080/marte/admin/cursos/nuevo
        //METODO O VERBO HTTP : POST
        //VISTA: /cursos/nuevo.jsp
        
        String URL = request.getPathInfo(); // Obtiente la subruta que aparece despues de admin/cursos
        try{
            if (URL.equals("/nuevo")) {
                //1.Obtener los campos del formulario de la vista curso: nuevo.jsp
                String nombre = request.getParameter("nombre");
                String nrc = request.getParameter("nrc");
                String descripcion = request.getParameter("descripcion");
                String profesor = request.getParameter("profesor");
                Integer creditos =Integer.parseInt(request.getParameter("creditos"));
                Integer horas = Integer.parseInt(request.getParameter("horas"));
                String horario = request.getParameter("horario");
                
                //2. Crear un objeto de clase curso y asignamos los valores
                Curso curso = new Curso();
                curso.setCreditos(creditos);
                curso.setDescripcion(descripcion);
                curso.setHorario(horario);
                curso.setHoras(horas);
                curso.setNombre(nombre);
                curso.setNrc(nrc);
                curso.setProfesor(profesor);
                
                //3. crear el objeto de ad_curso y llamo al metodo insertar
                AD_Curso ad_curso = new AD_Curso();
                ad_curso.addCurso(curso);
                
                //4. Redireccionamos a la pagina de inicio del listado de cursos.
                response.sendRedirect(request.getContextPath() + "/admin/cursos");
            } 
        
            //EDITAR CURSO       
            //FUNCION: EDITAR CURSO
            //DESCRIPCION: Obtener el curso por ID y cargar los datos en nuevo formulario
            //URL: http://localhost:8080/marte/admin/cursos/editar/{id}
            //METODO O VERBO HTTP : POST
            //VISTA: /cursos/editar.jsp
            String accion = URL.substring(1); // -> editar/id
            String[] ruta = accion.split("/"); //-> ruta[0]="editar"; ruta[1]="id"
            if(ruta[0].equals("editar")){
                //1. obtenemos el id de curso a actualizar
                Integer id_curso = Integer.parseInt(ruta[1]);

                //2. creamos un objeto curso y asignamos los valores enviados desde la vista
                Curso curso = new Curso();

                //3. obtenemos los campos del form
                String nombre = request.getParameter("nombre");
                String nrc = request.getParameter("nrc");
                String descripcion = request.getParameter("descripcion");
                String profesor = request.getParameter("profesor");
                Integer creditos =Integer.parseInt(request.getParameter("creditos"));
                Integer horas = Integer.parseInt(request.getParameter("horas"));
                String horario = request.getParameter("horario");

                curso.setId(id_curso);
                curso.setCreditos(creditos);
                curso.setDescripcion(descripcion);
                curso.setNrc(nrc);
                curso.setNombre(nombre);
                curso.setProfesor(profesor);
                curso.setHoras(horas);
                curso.setHorario(horario);

                //4. Actualizar en base de datos
                AD_Curso ad_curso = new AD_Curso();
                
                //5. rediriccionamos a la lista de cursos
                response.sendRedirect(request.getContextPath()+"/admin/cursos");
                ad_curso.updateCurso(curso);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
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
