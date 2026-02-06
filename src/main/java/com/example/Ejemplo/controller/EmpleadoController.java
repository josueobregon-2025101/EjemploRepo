package com.example.Ejemplo.controller;

import com.example.Ejemplo.model.Empleado;
import com.example.Ejemplo.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")

public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService){this.empleadoService = empleadoService;}

    @GetMapping
    public List<Empleado> getAllEmpleados(){return empleadoService.getAllEmpleados();}

    @PostMapping
    public ResponseEntity<Object> createEmpleado(@Valid @RequestBody Empleado empleado){
     try {
         Empleado createdEmpleado = empleadoService.saveEmpleado(empleado);
         return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED);
     } catch (IllegalArgumentException e) {
         return ResponseEntity.badRequest().body(e.getMessage());
     }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable Integer id,@RequestBody Empleado empleado){
       try {
           Empleado actualizado = empleadoService.updateEmpleado(id,empleado);
           if (actualizado != null ){
               return ResponseEntity.ok(actualizado);
           }
           else {
               return ResponseEntity.status(404).body("No se encontro el empleado");
           }
       } catch (IllegalArgumentException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Integer id){
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if(empleado != null){
            try {
                empleadoService.deleteEmpleado(id);
                return ResponseEntity.ok("Se elimino el Empleado " + id);
            }catch (IllegalArgumentException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }else{
            return ResponseEntity.status(404).body("No se encontro el empleado " + id);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpleadoById(@PathVariable Integer id){
        try {
            Empleado empleado = empleadoService.getEmpleadoById(id);
            if (empleado != null) {
                return ResponseEntity.ok(empleado);
            }else {
               return ResponseEntity.status(404).body("No se encontro el empleado");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
