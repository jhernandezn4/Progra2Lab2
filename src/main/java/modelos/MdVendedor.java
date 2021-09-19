/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author GAMERS
 */
public class MdVendedor {
    private Integer id;
    private String nombre;
    private Double enero;
    private Double febrero;
    private Double marzo;
    private Double promedio;
    private Double total;

    @Override
    public String toString() {
        return "MdVendedor{" + "id=" + id + ", nombre=" + nombre + ", enero=" + enero + ", febrero=" + febrero + ", marzo=" + marzo + ", promedio=" + promedio + ", total=" + total + '}';
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getEnero() {
        return enero;
    }

    public void setEnero(Double enero) {
        this.enero = enero;
    }

    public Double getFebrero() {
        return febrero;
    }

    public void setFebrero(Double febrero) {
        this.febrero = febrero;
    }

    public Double getMarzo() {
        return marzo;
    }

    public void setMarzo(Double marzo) {
        this.marzo = marzo;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
}
