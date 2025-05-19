package pe.isil.cliente.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.isil.cliente.beans.Cliente;


public class AD_Cliente {
    private PreparedStatement pst = null; //objeto que permite ejecutar una sentencia sql con o sin parametros
    private ResultSet rst; //conjunto de datos equivalente al resultado de un SELECT, filas.
    
    public List<Cliente> getAll() throws SQLException{
        List<Cliente> Clientes = new ArrayList<>();
        Cliente cliente;
        Connection Conexion = null;
        try {
            Conexion = ConnectionPool.getInstance().getConnection();
            if(Conexion != null){
                String SQL = "SELECT * FROM cliente";
                pst = Conexion.prepareStatement(SQL);
                rst = pst.executeQuery();
                
                while(rst.next()){
                    cliente = new Cliente();
                    cliente.setId(rst.getInt("id"));
                    cliente.setNombre(rst.getString("nombre"));
                    cliente.setApellido(rst.getString("apellido"));
                    cliente.setEmail(rst.getString("email"));
                    cliente.setTelefono(rst.getString("telefono"));
                    cliente.setGenero(rst.getString("genero"));
                    Clientes.add(cliente);
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
        return Clientes;
    }
}