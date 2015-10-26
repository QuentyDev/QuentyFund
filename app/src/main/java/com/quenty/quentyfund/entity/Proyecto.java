package com.quenty.quentyfund.entity;

/**
 * Created by DavorLimachi on 10/21/15.
 */
public class Proyecto {
    private int proyectoID;
    private String nombre;
    private String descripcionLarga;
    private String descripcionCorta;
    private int monto;
    private int diasVigencia;
    private String categoria;
    private String ciudad;
    private String estado;
    private String urlImage;

    public Proyecto(int proyectoID, String nombre, String descripcion, int monto, int diasVigencia, String categoria) {
        this.proyectoID = proyectoID;
        this.nombre = nombre;
        this.descripcionLarga = descripcion;
        this.monto = monto;
        this.diasVigencia = diasVigencia;
        this.categoria = categoria;
    }
    public Proyecto() {
        this.proyectoID = -1;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getDiasVigencia() {
        return diasVigencia;
    }

    public void setDiasVigencia(int diasVigencia) {
        this.diasVigencia = diasVigencia;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProyectoID() {
        return proyectoID;
    }

    public void setProyectoID(int proyectoID) {
        this.proyectoID = proyectoID;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
