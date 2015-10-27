package com.quenty.quentyfund.entity;

/**
 * Created by Diego on 26/10/15. v_monto int(11),v_social_id int(11),v_proyecto_id int(11)
 */
public class Contribucion {
    private int monto;
    private int socialID;
    private int proyectoID;
    private String nombreUsuario;
    private String urlImagePerfil;

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getSocialID() {
        return socialID;
    }

    public void setSocialID(int socialID) {
        this.socialID = socialID;
    }

    public int getProyectoID() {
        return proyectoID;
    }

    public void setProyectoID(int proyectoID) {
        this.proyectoID = proyectoID;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUrlImagePerfil() {
        return urlImagePerfil;
    }

    public void setUrlImagePerfil(String urlImagePerfil) {
        this.urlImagePerfil = urlImagePerfil;
    }
}
