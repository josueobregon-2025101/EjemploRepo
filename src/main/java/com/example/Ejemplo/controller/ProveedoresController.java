package com.example.Ejemplo.controller;

import com.example.Ejemplo.model.Proveedores;
import com.example.Ejemplo.service.ProveedoresService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/Proveedores")
public class ProveedoresController {
    public final ProveedoresService proveedoresService;

    public ProveedoresController(ProveedoresService proveedoresService){
        this.proveedoresService = proveedoresService;
    }

    @GetMapping
    public List<Proveedores> getAllProveedores(){
        return proveedoresService.getAllProveedores();
    }

    @GetMapping({"/"})
    public ResponseEntity<?> getEmpleadoSinId(){
        return ResponseEntity.badRequest().body("Error: Ingrese un id");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getProveedorById(@PathVariable Integer id){
        try {
            Proveedores proveedores = proveedoresService.getProveedorById(id);
            if (proveedores != null){
                return ResponseEntity.ok(proveedores);
            }else {
                return ResponseEntity.status(404).body("No se encontro el proveedor");
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveProveedores(@Valid @RequestBody Proveedores proveedores){
        try {
            if (proveedores.getNombreProveedor() == null || proveedores.getNombreProveedor().isBlank()) {
                return ResponseEntity.status(400).body("El nombre del proveedor es necesario");
            }
            if (proveedores.getDireccion() == null || proveedores.getDireccion().isBlank()) {
                return ResponseEntity.badRequest().body("La direccion del proveedor es necesario");
            }
            if (proveedores.getEmailProveedor() == null || proveedores.getEmailProveedor().isBlank()) {
                return ResponseEntity.badRequest().body("El email del proveedor es necesario");
            }
            if (proveedores.getTelefono_proveedor() == null) {
                return ResponseEntity.badRequest().body("El telefono del proveedor es necesario");
            }else {
                Proveedores createdP = proveedoresService.saveProveedores(proveedores);
                    return new ResponseEntity<>(createdP, HttpStatus.CREATED);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProveedores(@Valid @PathVariable Integer id, @Valid @RequestBody Proveedores proveedores){
        try {
            if (proveedores.getNombreProveedor() == null || proveedores.getNombreProveedor().isBlank()) {
                return ResponseEntity.status(400).body("El nombre del proveedor es necesario");
            }
            if (proveedores.getDireccion() == null || proveedores.getDireccion().isBlank()) {
                return ResponseEntity.badRequest().body("La direccion del proveedor es necesario");
            }
            if (proveedores.getEmailProveedor() == null || proveedores.getEmailProveedor().isBlank()) {
                return ResponseEntity.badRequest().body("El email del proveedor es necesario");
            }
            if (proveedores.getTelefono_proveedor() == null) {
                return ResponseEntity.badRequest().body("El telefono del proveedor es necesario");
            }else {
                Proveedores existente = proveedoresService.getProveedorById(id);
                if (existente != null) {
                    Proveedores NewProveedor = proveedoresService.updateProveedores(id, proveedores);
                    return ResponseEntity.ok(NewProveedor);
                } else {
                    return ResponseEntity.status(404).body("No se encontro el proveedor id: " + id);
                }
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProveedor(@Valid @PathVariable Integer id){
        try {
            Proveedores existente = proveedoresService.getProveedorById(id);
            if (existente != null){
                proveedoresService.deleteProveedor(id);
                return ResponseEntity.ok("Se elimino correcta mente el proveedor id: "+id);
            }else {
                return ResponseEntity.status(404).body("No se encontro el proveedor id: "+id);
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
