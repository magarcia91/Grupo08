package com.example.janusdeb.loginapplication;

/**
 * Created by janusdeb on 24/10/18.
 */

import android.graphics.drawable.Drawable;


public class Usuarios {

    private String User;
    private String Nombre;
    private String Apellido;
    private String Email;
    private String Celular;
    private String Fecha;
    private String Genero;
    private String Becado;
    private int imagen;
    private String Completo;

    public Usuarios() {
        super();
    }

    public Usuarios(String user, String nombre, String apellido, String email, String celular, String fecha, String genero, String becado, int imagen) {
        super();
        this.User = user;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Email = email;
        this.Celular = celular;
        this.Fecha = fecha;
        this.Genero = genero;
        this.Becado = becado;
        this.imagen = imagen;
    }
    public Usuarios(String cadena){
        super();
        this.Completo=cadena;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getBecado() {
        return Becado;
    }

    public void setBecado(String becado) {
        Becado = becado;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}