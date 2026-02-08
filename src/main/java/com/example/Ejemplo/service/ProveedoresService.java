package com.example.Ejemplo.service;

import com.example.Ejemplo.model.Proveedores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProveedoresService {
    List<Proveedores> getAllProveedores();
    Proveedores getProveedorById(Integer id);
    Proveedores saveProveedores(Proveedores proveedores) throws RuntimeException;
    Proveedores updateProveedores(Integer id, Proveedores proveedores);
    void deleteProveedor(Integer id);
}
