package pe.isil.categoria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;
import pe.isil.categoria.beans.EE_Categoria;

public class AD_Categoria {
    
    private String usuario = "root";
    private String password = "admin";
    private String database = "categoria";
    private String url = "jdbc:mysql://localhost:3306/"+ database + "?useSSL=false";
    
    
    private static AD_Categoria datasource;
    private BasicDataSource basicDataSource = null;
    
    public AD_Categoria(){
        basicDataSource = new BasicDataSource(); 
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(usuario);
        basicDataSource.setPassword(password);
        basicDataSource.setUrl(url);
        
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxTotal(50);
        basicDataSource.setMaxWaitMillis(-1);       

    }
    public static AD_Categoria getInstance()
    {
        if (datasource == null) {
            datasource = new AD_Categoria();
            return datasource;
        }
        else
        {
            return datasource;
        }
    }
    public Connection getConnection() throws SQLException
    {
      return basicDataSource.getConnection();        
       
    }
    
    public void closeConnection(Connection conexion) throws SQLException
    {
       conexion.close();
    }
    
    private PreparedStatement pst = null; 
    private ResultSet rst; 
    
    //SELECT por Id
    public EE_Categoria getCategoriaById(Integer id) throws SQLException{
        EE_Categoria categoria = new EE_Categoria();
        Connection Conexion = null; 
        
        try {
            
            Conexion = AD_Categoria.getInstance().getConnection();
            if(Conexion != null){
                
                String SQL = "SELECT * FROM categoria WHERE id = ?";
                pst = Conexion.prepareStatement(SQL);
                
                pst.setInt(1, id);
                
                rst = pst.executeQuery();
                
                while(rst.next()){
                    categoria.setId(rst.getInt("id"));
                    categoria.setNombre(rst.getString("nombre"));
                }
            }
            else{
                System.out.println("Error en la conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            AD_Categoria.getInstance().closeConnection(Conexion);
        }
        return categoria;
    }
    
    public List<EE_Categoria> getCategorias() throws SQLException{
        List<EE_Categoria> categorias = new ArrayList<>();
        EE_Categoria categoria;
        Connection Conexion = null; 
        
        try {
            
            Conexion = AD_Categoria.getInstance().getConnection();
            if(Conexion != null){
                
                String SQL = "SELECT * FROM categoria";
                pst = Conexion.prepareStatement(SQL);
                
                rst = pst.executeQuery();
                
                while(rst.next()){
                    categoria = new EE_Categoria();
                    categoria.setId(rst.getInt("id"));
                    categoria.setNombre(rst.getString("nombre"));
                    
                    categorias.add(categoria);
                }
            }
            else{
                System.out.println("Error en la conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            AD_Categoria.getInstance().closeConnection(Conexion);
        }
        return categorias;
    }
    
    public boolean addCategoria(EE_Categoria categoria) throws SQLException{
        Connection Conexion = null; 
        try {
            
            Conexion = AD_Categoria.getInstance().getConnection();
            if(Conexion != null){
                
                String SQL = "INSERT INTO categoria(nombre)"
                        +"VALUES(?)";
                pst = Conexion.prepareStatement(SQL);
                pst.setString(1, categoria.getNombre());
                
                int res = 0;
                res = pst.executeUpdate();
                if(res > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                System.out.println("Error en la conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            AD_Categoria.getInstance().closeConnection(Conexion);
        }
        return false;
    } 
    
    public boolean updateCategoria(EE_Categoria categoria) throws SQLException{
        
        Connection Conexion = null; 
        try {
          
            Conexion = AD_Categoria.getInstance().getConnection();
            if(Conexion != null){
                
                String SQL = "UPDATE categoria SET nombre = ?"
                        +"WHERE id = ?";
                pst = Conexion.prepareStatement(SQL);
                pst.setString(1, categoria.getNombre());
                pst.setInt(2, categoria.getId());
                
                int res = 0;
                res = pst.executeUpdate();
                if(res > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                System.out.println("Error en la conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            AD_Categoria.getInstance().closeConnection(Conexion);
        }
        return false;
    }
    
    public boolean deleteCategoria(int id) throws SQLException
    {
        Connection Conexion = null;       
        try {
            
            Conexion = AD_Categoria.getInstance().getConnection();            
            if(Conexion != null)
            {
                
                String SQL = "DELETE FROM categoria WHERE id = ?";
                pst = Conexion.prepareStatement(SQL);
                pst.setInt(1, id);
                
                int res = 0;
                res = pst.executeUpdate();
                if (res > 0) {
                    return true;
                } else {
                    return false;
                }                
            }
            else
            {
                System.out.println("Error en la conexion a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            AD_Categoria.getInstance().closeConnection(Conexion);
        }
        return false;
    }
}
