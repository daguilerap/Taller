/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Date;

/**
 *
 * @author David
 */
public class ReparacionVo {
    private int id;
    private int id_tra;
    private String tiporep;
    private String descripcion;
    private Double horas;
    private Double precio;
    private String fInicio;
    private String fFin;
    private Double precio_tot;

    public ReparacionVo(  int id, int id_tra, String tiporep, String descripcion, Double horas, Double precio, String fInicio, String fFin, Double precio_tot){
        this.id=id;
        this.id_tra=id;
        this.tiporep=tiporep;
        this.descripcion=descripcion;
        this.horas=horas;
        this.precio=precio;
        this.fInicio=fInicio;
        this.fFin=fFin;
        this.precio_tot=precio_tot;
    }

    public ReparacionVo(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tra() {
        return id_tra;
    }

    public void setId_tra(int id_tra) {
        this.id_tra = id_tra;
    }

    public String getTiporep() {
        return tiporep;
    }

    public void setTiporep(String tiporep) {
        this.tiporep = tiporep;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getfInicio() {
        return fInicio;
    }

    public void setfInicio(String fInicio) {
        this.fInicio = fInicio;
    }

    public String getfFin() {
        return fFin;
    }

    public void setfFin(String fFin) {
        this.fFin = fFin;
    }

    public Double getPrecio_tot() {
        return precio_tot;
    }

    public void setPrecio_tot(Double precio_tot) {
        this.precio_tot = precio_tot;
    }
}
