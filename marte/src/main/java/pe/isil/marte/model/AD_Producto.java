package pe.isil.marte.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.isil.marte.beans.Producto;

/**
 *
 * @author pc
 */
public class AD_Producto {
    private PreparedStatement pst = null;
    private ResultSet rst;
    
    public Producto getById(Integer id) throws SQLException{
        Producto producto = new Producto();
        Connection conexion = null;
        try {
            conexion = ConnectionPool.getInstance().getConnection();
            if(conexion != null){
                String SQL = "SELECT * FROM producto WHERE id = ?";
                pst = conexion.prepareStatement(SQL);
                pst.setInt(1, id);
                
                rst = pst.executeQuery();
                
                while(rst.next()){
                    producto.setId(rst.getInt("id"));
                    producto.setNombre(rst.getString("nombre"));
                    producto.setPrecio(rst.getDouble("precio"));
                    producto.setStock(rst.getInt("stock"));
                }
            }else{
                System.out.println("Error en la conexion a la db");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(conexion);
        }
        return producto;
    }
    
    public ArrayList<Producto> getByNombre(String nombre) throws SQLException{
        ArrayList<Producto> productos = new ArrayList<>();
        Producto producto;
        Connection conexion = null;
        try {
            conexion = ConnectionPool.getInstance().getConnection();
            if(conexion != null){
                String SQL = "SELECT * FROM producto WHERE nombre LIKE ?";
                pst = conexion.prepareStatement(SQL);
                pst.setString(1, "%"+nombre+"%");
                
                rst = pst.executeQuery();
                
                while(rst.next()){
                    producto = new Producto();
                    producto.setId(rst.getInt("id"));
                    producto.setNombre(rst.getString("nombre"));
                    producto.setPrecio(rst.getDouble("precio"));
                    producto.setStock(rst.getInt("stock"));
                    productos.add(producto);
                }
            }else{
                System.out.println("Error en la conexion a la db");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(conexion);
        }
        return productos;
    }
    
    public ArrayList<Producto> getAll() throws SQLException{
        ArrayList<Producto> productos = new ArrayList<>();
        Producto producto;
        Connection conexion = null;
        try {
            conexion = ConnectionPool.getInstance().getConnection();
            if(conexion != null){
                String SQL = "SELECT * FROM producto";
                pst = conexion.prepareStatement(SQL);                
                rst = pst.executeQuery();
                
                while(rst.next()){
                    producto = new Producto();
                    producto.setId(rst.getInt("id"));
                    producto.setNombre(rst.getString("nombre"));
                    producto.setPrecio(rst.getDouble("precio"));
                    producto.setStock(rst.getInt("stock"));
                    productos.add(producto);
                }
            }else{
                System.out.println("Error en la conexion a la db");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(conexion);
        }
        return productos;
    }
}
