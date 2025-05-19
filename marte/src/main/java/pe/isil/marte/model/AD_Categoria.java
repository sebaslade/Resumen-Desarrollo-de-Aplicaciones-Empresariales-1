package pe.isil.marte.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.isil.marte.beans.Categoria;

public class AD_Categoria {
    private PreparedStatement pst = null; //objeto que permite ejecutar una sentencia SQL con o sin parametros
    private ResultSet rst; //conjunto de datos, equivalente al resultado de un SELECT, filas.
    
    public List<Categoria> getAll() throws SQLException
    {
        List<Categoria> Categorias = new ArrayList<>();
        Categoria categoria; //un curso en especifico
        Connection Conexion = null; //creo el objecto conexion
        try {
            Conexion = ConnectionPool.getInstance().getConnection();
            if (Conexion != null) {
                String SQL = "SELECT * FROM categoria";
                pst = Conexion.prepareStatement(SQL);
                rst = pst.executeQuery();
                
                while (rst.next()) {
                    categoria = new Categoria();
                    categoria.setId(rst.getInt("id"));
                    categoria.setNombre(rst.getString("nombre"));
                    Categorias.add(categoria);
                }
            }
            else
            {
                System.out.println("Error en la conexion a la base de datos");
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(Conexion);
        }
        return Categorias;
    }
}
