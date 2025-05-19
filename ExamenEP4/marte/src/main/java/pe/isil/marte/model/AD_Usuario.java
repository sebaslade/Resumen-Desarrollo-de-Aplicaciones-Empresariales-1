package pe.isil.marte.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.isil.marte.beans.Usuario;

public class AD_Usuario {
    
    private PreparedStatement pst = null;
    private ResultSet rst;
    
    //insertar o registar un nuevo usuario
    //Regla de negocio
    // EL email no este registrado previamente
    //, que cumpla la politicas de contraseña de seguridad
    public boolean insertar(Usuario usuario) throws SQLException
    {
        boolean resultado = false;
        Connection Conexion = null;
        
        try {
            Conexion = ConnectionPool.getInstance().getConnection();
            if(Conexion != null)
            {
                String SQL = "INSERT INTO usuario(email, password, nombre, apellido, celular) VALUES(?, ?, ?)";
                pst = Conexion.prepareStatement(SQL);
                pst.setString(1, usuario.getEmail().toLowerCase());
                pst.setString(2, usuario.getPassword());
                pst.setString(3, usuario.getNombre());
                pst.setString(4, usuario.getApellido());
                pst.setInt(5, usuario.getCelular());
                
                
                if(pst.executeUpdate() > 0)
                {
                    resultado = true;
                }
                else{
                    resultado = false;
                }
            }
            else{
                System.out.println("Error en la conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(Conexion);
        }
        return resultado;
    }
    
    
    public boolean existeEmail(String email) throws SQLException
    {
        boolean existe = false;
        Connection Conexion = null;        
        try {
            Conexion = ConnectionPool.getInstance().getConnection();
            if (Conexion != null)
            {
                String SQL = "SELECT * FROM usuario WHERE email = ?";
                pst = Conexion.prepareStatement(SQL);
                pst.setString(1, email);                
                rst = pst.executeQuery();                
                while(rst.next()){
                    //si entra significar que si hay registros
                    //por lo tanto el correo existe en la base de datos
                    if (rst.getInt("id") > 0) {
                        existe = true;
                        return existe;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(Conexion);
        }
        return existe;
    }
    
    
    public Usuario login(String email) throws SQLException
    {
        Usuario usuario = null;
        Connection Conexion = null;
        try {
            Conexion = ConnectionPool.getInstance().getConnection();
            if (Conexion != null) {
                String SQL = "SELECT * FROM usuario WHERE email = ?";
                pst = Conexion.prepareStatement(SQL);
                pst.setString(1, email);
                rst = pst.executeQuery();
                while (rst.next()) {
                    usuario = new Usuario();
                    usuario.setId(rst.getInt("id"));
                    usuario.setNombre(rst.getString("nombre"));
                    usuario.setApellido(rst.getString("apellido"));
                    usuario.setCelular(rst.getInt("celular"));
                    usuario.setEmail(rst.getString("email"));
                    usuario.setPassword(rst.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(Conexion);
        }
        return usuario;
    }
}
