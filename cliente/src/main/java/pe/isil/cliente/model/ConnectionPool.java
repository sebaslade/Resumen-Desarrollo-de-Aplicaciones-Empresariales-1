package pe.isil.cliente.model;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPool {
     //elementos para realizar la conexion a la base de datos MYSQL
    private String usuario = "root";
    private String password = "admin";
    private String database = "cliente";
    private String url = "jdbc:mysql://localhost:3306/" + database + "?serverTimezone=America/Lima";
    
    private static ConnectionPool datasource;
    private BasicDataSource basicDataSource = null;

    public ConnectionPool() {
        basicDataSource = new BasicDataSource(); //instancio la clase
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(usuario);
        basicDataSource.setPassword(password);
        basicDataSource.setUrl(url);
        
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxTotal(50);
        basicDataSource.setMaxWaitMillis(-1);
    }
    
    //obtener o crear nuevas instancias de conexion a la base de datos
    public static ConnectionPool getInstance()
    {
        if (datasource == null) { // ==  comparar, = asignar valores
            datasource = new ConnectionPool();
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
}