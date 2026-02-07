package com.example.Ejemplo.service;

import com.example.Ejemplo.model.Repuesto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RepuestoService {
    List<Repuesto> getAllRepuestos();
    Repuesto getRepuestoById(Integer id);
    Repuesto saveRepuesto(Repuesto repuesto)throws RuntimeException;
    Repuesto updateRepuesto( Integer id, Repuesto repuesto);
    void deleteRepuesto(Integer id);

}
