package com.example.Ejemplo.controller;

import com.example.Ejemplo.model.Repuesto;
import com.example.Ejemplo.service.RepuestoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.IllegalFormatException;
import java.util.List;

@RestController
@RequestMapping("/api/Repuesto")
public class RepuestoController {

    public final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService){
        this.repuestoService = repuestoService;
    }

    @GetMapping
    public List<Repuesto> getAllRepuestos(){return repuestoService.getAllRepuestos();}

    @GetMapping("/{id}")
    public ResponseEntity<?> getRepuestoById(@PathVariable Integer id) {
        try {
        Repuesto repuesto = repuestoService.getRepuestoById(id);
        if (repuesto != null){
            return ResponseEntity.ok(repuesto);
        }else {return ResponseEntity.status(404).body("No se encontro el repuesto");}

        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createRepuesto(@Valid @RequestBody Repuesto repuesto){
        try {
            Repuesto repuestoCreated = repuestoService.saveRepuesto(repuesto);
            return new ResponseEntity<>(repuestoCreated, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateRepuestos(@PathVariable Integer id, @Valid @RequestBody Repuesto repuesto){
        try {
            Repuesto repuestoExistente = repuestoService.getRepuestoById(id);
            if (repuestoExistente != null){
                try {
                    Repuesto repuestoNew = repuestoService.updateRepuesto(id,repuesto);
                    if (repuestoNew != null){
                        return ResponseEntity.ok(repuestoNew);
                    }else {
                       return ResponseEntity.badRequest().body("Ingreso datos incorrectos");
                    }
                }catch (IllegalArgumentException e){
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
            }else {
                return ResponseEntity.status(404).body("No se encontro el repuesto");
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRepuesto(@PathVariable Integer id){
        Repuesto repuesto = repuestoService.getRepuestoById(id);
        if (repuesto!=null){
            try {
                repuestoService.deleteRepuesto(id);
                return ResponseEntity.ok("se elimino correctamente");
            }catch (IllegalArgumentException e){
                return ResponseEntity.status(400).body("No se pudo eliminar");
            }
        }else {
            return ResponseEntity.status(404).body("No se encontro el Repuesto");
        }
    }
}
