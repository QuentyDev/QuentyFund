package com.quenty.quentyfund.entity;

/**
 * Created by DavorLimachi on 10/25/15.
 */
public class Cuidad {
    private int id;
    private String nombre;
    private int valido;

    public Cuidad() {
        id = -1;
        nombre = "";
        valido = 0;
    }

    public int getValido() {
        return valido;
    }

    public void setValido(int valido) {
        this.valido = valido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
