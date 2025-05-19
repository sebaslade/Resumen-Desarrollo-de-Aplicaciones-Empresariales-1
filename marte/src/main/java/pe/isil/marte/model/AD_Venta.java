package pe.isil.marte.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pe.isil.marte.beans.Cliente;
import pe.isil.marte.beans.Venta;
import pe.isil.marte.beans.VentaDetalle;

/**
 *
 * @author pc
 */
public class AD_Venta {
    private PreparedStatement pst = null;
    private ResultSet rst;
    
    public boolean insertar(Cliente cliente, Venta venta, ArrayList<VentaDetalle> detalle) throws SQLException{
        boolean resultado = false;
        Connection conexion = null;
        try {
            conexion = ConnectionPool.getInstance().getConnection();
            if(conexion != null){
                //DESACTIVAR EL COMMIT AUTOMATICO
                conexion.setAutoCommit(false);
                
                //INSERTAR CLIENTE
                String SQL = "INSERT INTO cliente(dni, nombre_apellidos) VALUES(?,?)";
                pst = conexion.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, cliente.getDni());
                pst.setString(2, cliente.getNombres_apellidos());
                pst.executeUpdate();
                //OBTENER EL ID DEL CLIENTE
                ResultSet generaID_cliente = pst.getGeneratedKeys();
                Integer id_generado_cliente = 0;
                if(generaID_cliente.next()){
                    id_generado_cliente = generaID_cliente.getInt(1);
                }
                
                //INSERTAR VENTA
                pst = null;
                SQL = "INSERT INTO venta(fecha, total, cliente_id, total_items) VALUES (?,?,?,?)";
                pst = conexion.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                pst.setDate(1, venta.getFecha());
                pst.setDouble(2, venta.getTotal());
                pst.setInt(3, id_generado_cliente);
                pst.setInt(4, venta.getTotal_items());
                pst.executeUpdate();
                //OBTENER EL ID DEL CLIENTE
                ResultSet generaID_venta = pst.getGeneratedKeys();
                Integer id_generado_venta = 0;
                if(generaID_venta.next()){
                    id_generado_venta = generaID_venta.getInt(1);
                }
                
                //INSERTAR DETALLE VENTA
                pst = null;
                for(int i = 0; i<detalle.size(); i++){
                    SQL = "INSERT INTO venta_detalle(venta_id, producto_id, cantidad, precio, total) VALUES(?,?,?,?,?)";
                    pst = conexion.prepareStatement(SQL);
                    pst.setInt(1, id_generado_venta);
                    pst.setInt(2, detalle.get(i).getProducto_id());
                    pst.setInt(3, detalle.get(i).getCantidad());
                    pst.setDouble(4, detalle.get(i).getPrecio());
                    pst.setDouble(5, detalle.get(i).getTotal());
                    pst.executeUpdate();
                }
                conexion.commit(); //CONFIRMAMOS LA TRANSACCION A LA BASE DE DATOS
                resultado = true;
            }
            else{
                System.out.println("Error de conexion a la base de datos");
            }
        } catch (Exception e) {
            if(conexion != null){
                conexion.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(conexion);
        }
        return resultado;
    }
}
