package pe.isil.marte.controllers.listeners;

import io.github.cdimascio.dotenv.Dotenv;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class AplicacionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //
        System.out.println("Aplicacion Web Iniciada");
        //Escribir una variable de entorno global que dure durante todo el ciclo de vida
        Dotenv dotenv = Dotenv.load();
        
        sce.getServletContext().setAttribute("URL_APLICACION", dotenv.get("URL_APLICACION"));
        System.out.println("URL: " + dotenv.get("URL_APLICACION"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Aplicacion Web Terminada!");
    }
}
