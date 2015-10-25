package com.quenty.quentyfund.entity;

/**
 * Created by DavorLimachi on 10/21/15.
 */
public class Proyecto {
    private int proyectoID;
    private String nombre;
    private String descripcion;
    private int monto;
    private int diasVigencia;
    private int categoriaID;

    public Proyecto(int proyectoID, String nombre, String descripcion, int monto, int diasVigencia, int categoriaID) {
        this.proyectoID = proyectoID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.monto = monto;
        this.diasVigencia = diasVigencia;
        this.categoriaID = categoriaID;
    }
    public Proyecto() {
        this.proyectoID = -1;
    }

    public int getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(int categoriaID) {
        this.categoriaID = categoriaID;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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





}
