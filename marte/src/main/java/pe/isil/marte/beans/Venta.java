/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.marte.beans;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class Venta {
    private Integer id;
    private Date fecha;
    private Double total;
    private Integer cliente_id;
    private Integer total_items;

    public Venta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Integer getTotal_items() {
        return total_items;
    }

    public void setTotal_items(Integer total_items) {
        this.total_items = total_items;
    }
    
    
}
