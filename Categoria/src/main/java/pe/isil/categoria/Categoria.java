package pe.isil.categoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import pe.isil.categoria.beans.EE_Categoria;
import pe.isil.categoria.dao.AD_Categoria;


public class Categoria {

    public static void main(String[] args) throws SQLException {
        Connection conexion = null;
        try {
            conexion = AD_Categoria.getInstance().getConnection();
            if (conexion != null){
                System.out.println("Conexión exitosa");
            }else{
               System.out.println("Problemas de conexión"); 
            }
            
            AD_Categoria ad_Categoria = new AD_Categoria();
            EE_Categoria categoria = new EE_Categoria(); 
            System.out.println("La categoria 1:");
            categoria = ad_Categoria.getCategoriaById(1);
            System.out.println("---------------");

            //Validacion
            if(categoria.getId() != null){
                System.out.println(categoria.getNombre());

                List<EE_Categoria> categorias = ad_Categoria.getCategorias();
                System.out.println("Todas las categorias:");
                System.out.println(categorias.get(0).getNombre());
                System.out.println("---------------");
            }else{
                System.out.println("No se obtuvieron los datos");
            }

            //insert
            EE_Categoria categoria_insertar = new EE_Categoria();
            categoria_insertar.setNombre("Competitiva");


            if(ad_Categoria.addCategoria(categoria_insertar) == true){
                System.out.println("Categoria registrada exitosamente");
                System.out.println("---------------");
            }

            //update
            EE_Categoria categoria_actualizar = new EE_Categoria();
            categoria_actualizar.setNombre("Personalizada");
            categoria_actualizar.setId(2);

            if(ad_Categoria.updateCategoria(categoria_actualizar) == true){
                System.out.println("Categoria actualizada exitosamente");
                System.out.println("---------------");
            }

            //DELETE
            if(ad_Categoria.deleteCategoria(1) == true){
                System.out.println("Categoria eliminada exitosamente");
                System.out.println("---------------");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            
            AD_Categoria.getInstance().closeConnection(conexion);
        }
    }
}
