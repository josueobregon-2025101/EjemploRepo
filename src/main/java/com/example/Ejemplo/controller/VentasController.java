package com.example.Ejemplo.controller;

import com.example.Ejemplo.model.Ventas;
import com.example.Ejemplo.service.VentasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Ventas")
public class VentasController {
    public VentasService ventasService;
    public VentasController(VentasService ventasService){
        this.ventasService = ventasService;
    }

    @GetMapping
    public List<Ventas> getAllVentas(){
        return ventasService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVentaById(@Valid @PathVariable Integer id){
        try {
            Ventas ventas = ventasService.getVentaById(id);
            if (ventas != null){
                return ResponseEntity.ok(ventas);
            }else return ResponseEntity.status(404).body("No se encontro la venta id: "+id);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
    // Id no ingresado
    @GetMapping({"/"})
    public ResponseEntity<?> getVentasSinId(){
        return ResponseEntity.badRequest().body("Error: Ingrese un id");
    }

    @PostMapping
    public ResponseEntity<?> saveVenta(@RequestBody Ventas ventas){
       try {
           Ventas createVenta = ventasService.saveVenta(ventas);
           return new ResponseEntity<>(createVenta, HttpStatus.CREATED);
       }catch (IllegalArgumentException e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenta(@PathVariable Integer id, @RequestBody Ventas ventas){
        try {
            Ventas NewVentas = ventasService.updateVenta(id,ventas);
            return ResponseEntity.ok(NewVentas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("No se encontro el id o no se ingreso algun dato" +e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Integer id){
        try {
           Ventas existente = ventasService.getVentaById(id);
           if (existente != null){
               ventasService.deleteVenta(id);
               return ResponseEntity.ok("Se elimino Correctamente venta id: "+id);
           }else {
               return ResponseEntity.status(404).body("No se encontro la venta id: "+id);
           }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
