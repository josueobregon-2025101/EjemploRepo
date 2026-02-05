package com.example.Ejemplo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;

    @Column(name = "apellido_empleado")
    private String apellidoEmpleado;

    @Column(name = "puesto_empleado")
    private String puestoEmpleado;

    @Column(name = "email_empleado")
    private String emailEmpleado;

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombre_empleado) {
        this.nombreEmpleado = nombre_empleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer id_empleado) {
        this.idEmpleado = id_empleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellido_empleado) {
        this.apellidoEmpleado = apellido_empleado;
    }

    public String getPuestoEmpleado() {
        return puestoEmpleado;
    }

    public void setPuestoEmpleado(String puesto_empleado) {
        this.puestoEmpleado = puesto_empleado;
    }

    public String getEmailEmpleado() {
        return emailEmpleado;
    }

    public void setEmailEmpleado(String email_empleado) {
        this.emailEmpleado = email_empleado;
    }
}
