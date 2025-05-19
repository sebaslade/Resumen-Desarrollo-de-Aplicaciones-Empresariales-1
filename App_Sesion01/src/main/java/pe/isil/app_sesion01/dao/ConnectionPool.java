package pe.isil.app_sesion01.dao;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author sebas
 */
public class ConnectionPool {
    // Elementos para realizar la conexion a la base de datos MySql
    private String usuario = "root";
    private String password = "admin";
    private String database = "marte";
    private String url = "jdbc:mysql://localhost:3306/"+database+"?serverTimezone=America/Lima";
    
    private static ConnectionPool datasource;
    private BasicDataSource basicDataSource = null;

    public ConnectionPool() {
        basicDataSource = new BasicDataSource(); // Instancio la clase
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(usuario);
        basicDataSource.setPassword(password);
        basicDataSource.setUrl(url);
        
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxTotal(50);
        basicDataSource.setMaxWaitMillis(-1);
    } 
    
    //Obtener o crear nuevas instancias de conexión a la base de datos
    public static ConnectionPool getInstance(){
        if(datasource == null){
            datasource = new ConnectionPool();
            return datasource;
        }
        else{
            return datasource;
        }
    }
    
    public Connection getConnection() throws SQLException{
        return basicDataSource.getConnection();
    }
    
    public void closeConnection(Connection conexion) throws SQLException{
        conexion.close();
    }
}
