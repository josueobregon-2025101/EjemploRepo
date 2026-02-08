package com.example.Ejemplo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Proveedores")
public class Proveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

   @Column(name = "nombre_proveedor")
   private String nombreProveedor;

    @Column(name = "telefono_proveedor")
    private Integer telefono_proveedor;

    @Column(name = "direccion")
    private  String direccion;

    @Column(name = "email_proveedor")
    private String emailProveedor;

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getTelefono_proveedor() {
        return telefono_proveedor;
    }

    public void setTelefono_proveedor(Integer telefono_proveedor) {
        this.telefono_proveedor = telefono_proveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }
}
