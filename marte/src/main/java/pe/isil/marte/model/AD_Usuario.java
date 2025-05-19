package pe.isil.marte.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.isil.marte.beans.Usuario;

public class AD_Usuario {
    private PreparedStatement pst = null;
    private ResultSet rst;
    
    //Insertar o registrar un nuevo usuario
    //Regla de negocio: El email no este previamente registrado, que cumpla las politicas de contraseñas de seguridad
    
    public boolean insertar(Usuario usuario) throws SQLException{
        boolean resultado = false;
        Connection conexion = null;
        try {
            conexion = ConnectionPool.getInstance().getConnection();
            if (conexion != null) {
            String SQL = "INSERT INTO usuario (nombres, apellidos, celular, email, password) VALUES(?,?,?,?,?)";
            pst = conexion.prepareStatement(SQL);
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getApellidos());
            pst.setString(3, usuario.getCelular());
            pst.setString(4, usuario.getEmail().toLowerCase());
            pst.setString(5, usuario.getPassword());
            
            if (pst.executeUpdate() > 0) {
                resultado = true;
            } else {
                resultado = false;
            }
        } else {
            System.out.println("Error en la conexion a la base de datos");
        }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(conexion);
        }
        return resultado;
    }
    
    public boolean existeEmail(String email) throws SQLException{
        boolean existe = false;
        Connection conexion = null;
        try {
            conexion = ConnectionPool.getInstance().getConnection();
            if(conexion != null){
                String SQL = "SELECT * FROM usuario WHERE email=?";
                pst = conexion.prepareStatement(SQL);
                pst.setString(1, email);
                
                rst = pst.executeQuery();
                
                while(rst.next()){
                    //si entra significa que si hay registros por lo tanto el correo existe en la base de datos
                    if(rst.getInt("Id") > 0){
                        existe = true;
                        return existe;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(conexion);
        }
        return existe;
    }
    
    public Usuario login(String email) throws SQLException{
        Usuario usuario = null;
        Connection conexion = null;
        try {
            conexion = ConnectionPool.getInstance().getConnection();
            if(conexion != null){
                String SQL = "SELECT * FROM usuario WHERE email = ?";
                pst = conexion.prepareStatement(SQL);
                pst.setString(1, email);
                rst = pst.executeQuery();
                while(rst.next()){
                    usuario = new Usuario();
                    usuario.setId(rst.getInt("id"));
                    usuario.setNombre(rst.getString("nombre"));
                    usuario.setEmail(rst.getString("email"));
                    usuario.setPassword(rst.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(conexion);
        }
        return usuario;
    }
    
    public Usuario getById(Integer id) throws SQLException{
        Usuario usuario = new Usuario();
        Connection conexion = null;
        try {
            conexion = ConnectionPool.getInstance().getConnection();
            if(conexion != null){
                String SQL = "SELECT * FROM usuario WHERE id = ?";
                pst = conexion.prepareStatement(SQL);
                pst.setInt(1, id);
                
                rst = pst.executeQuery();
                
                while(rst.next()){
                    usuario.setId(rst.getInt("id"));
                    usuario.setNombre(rst.getString("nombres"));
                    usuario.setApellidos(rst.getString("apellidos"));
                    usuario.setEmail(rst.getString("email"));
                    usuario.setCelular(rst.getString("celular"));
                    usuario.setPassword(rst.getString("password"));
                }
            }else{
                System.out.println("Error en la conexion a la db");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(conexion);
        }
        return usuario;
    }
    
    public ArrayList<Usuario> getByNombreApellido(String nombre, String apellido) throws SQLException{
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario usuario;
        Connection conexion = null;
        try {
            conexion = ConnectionPool.getInstance().getConnection();
            if(conexion != null){
                String SQL = "SELECT * FROM usuario WHERE nombres LIKE ? OR apellidos LIKE ?";
                pst = conexion.prepareStatement(SQL);
                pst.setString(1, "%"+nombre+"%");
                pst.setString(2, "%"+apellido+"%");
                
                rst = pst.executeQuery();
                
                while(rst.next()){
                    usuario = new Usuario();
                    usuario.setId(rst.getInt("id"));
                    usuario.setNombre(rst.getString("nombres"));
                    usuario.setApellidos(rst.getString("apellidos"));
                    usuario.setEmail(rst.getString("email"));
                    usuario.setCelular(rst.getString("celular"));
                    usuario.setPassword(rst.getString("password"));
                    usuarios.add(usuario);
                }
            }else{
                System.out.println("Error en la conexion a la db");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(conexion);
        }
        return usuarios;
    }
    
    public ArrayList<Usuario> getAll() throws SQLException{
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario usuario;
        Connection conexion = null;
        try {
            conexion = ConnectionPool.getInstance().getConnection();
            if(conexion != null){
                String SQL = "SELECT * FROM usuario";
                pst = conexion.prepareStatement(SQL);                
                rst = pst.executeQuery();
                
                while(rst.next()){
                    usuario = new Usuario();
                    usuario.setId(rst.getInt("id"));
                    usuario.setNombre(rst.getString("nombres"));
                    usuario.setApellidos(rst.getString("apellidos"));
                    usuario.setEmail(rst.getString("email"));
                    usuario.setCelular(rst.getString("celular"));
                    usuario.setPassword(rst.getString("password"));
                    usuarios.add(usuario);
                }
            }else{
                System.out.println("Error en la conexion a la db");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(conexion);
        }
        return usuarios;
    }
}
