package com.company.proyecto_jsp.domain.entities;

import com.company.proyecto_jsp.domain.enums.Rol;

public class Usuario {
    private String id;
    private String nombre;
    private String correo;
    private String clave;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String correo, String clave, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return correo;
    }

    public void setEmail(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
