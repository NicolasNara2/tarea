package org.example;


import javax.xml.crypto.Data;

/*Tenemos que crear una entidad que se llame Pacientes
        en la base de datos H2 que tenga los siguientes campos:
        nombre, apellido, domicilio, DNI, fecha de alta, usuario y password.*/
public class Paciente {
    private String nombre;
    private final String apellido;
    private final String domicilio;
    private final Long dni;
    private final Data fechaDeAlta;
    private final String username;
    private final String password;


    public Paciente(String nombre, String apellido, String domicilio, Long dni, Data fechaDeAlta, String username, String password, Long id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaDeAlta = fechaDeAlta;
        this.username = username;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public Long getDni() {
        return dni;
    }

    public Data getFechaDeAlta() {
        return fechaDeAlta;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
