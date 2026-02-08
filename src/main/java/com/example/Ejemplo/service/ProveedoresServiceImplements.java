package com.example.Ejemplo.service;

import com.example.Ejemplo.model.Proveedores;
import com.example.Ejemplo.repository.ProveedoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProveedoresServiceImplements implements ProveedoresService{
    public final ProveedoresRepository proveedoresRepository;

    public ProveedoresServiceImplements( ProveedoresRepository proveedoresRepository){
        this.proveedoresRepository = proveedoresRepository;
    }

    @Override
    public List<Proveedores> getAllProveedores() {
        return proveedoresRepository.findAll();
    }

    @Override
    public Proveedores getProveedorById(Integer id) {
        return proveedoresRepository.findById(id).orElse(null);
    }

    @Override
    public Proveedores saveProveedores(Proveedores proveedores) throws RuntimeException {
        return proveedoresRepository.save(proveedores);
    }

    @Override
    public Proveedores updateProveedores(Integer id, Proveedores proveedores) {
        Optional<Proveedores> existente = proveedoresRepository.findById(id);
        if (existente.isPresent()){
            Proveedores proveedorNew = existente.get();
            proveedorNew.setEmailProveedor(proveedores.getEmailProveedor());
            proveedorNew.setNombreProveedor(proveedores.getNombreProveedor());
            proveedorNew.setTelefono_proveedor(proveedores.getTelefono_proveedor());
            proveedorNew.setDireccion(proveedores.getDireccion());
            return proveedoresRepository.save(proveedorNew);
        }else return null;

    }

    @Override
    public void deleteProveedor(Integer id) {
    proveedoresRepository.deleteById(id);
    }
}
