package com.quenty.quentyfund.entity;

/**
 * Created by DavorLimachi on 10/7/15.
 */
public class User {

    private int id;
    private String firstName;
    private String email;
    private String lastName;
    private String telefono;
    private String direccion;
    private String socialId;


    public User() {
        this.id = -1;
        this.firstName = "";
        this.email = "";
        this.lastName = "";
        this.telefono = "";
        this.direccion = "";
        this.socialId = "";
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
