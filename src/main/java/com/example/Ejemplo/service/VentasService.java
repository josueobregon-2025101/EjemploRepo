package com.example.Ejemplo.service;

import com.example.Ejemplo.model.Ventas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VentasService {
    List<Ventas> getAllVentas();
    Ventas getVentaById(Integer id);
    Ventas saveVenta(Ventas ventas)throws RuntimeException;
    Ventas updateVenta(Integer id, Ventas ventas);
    void deleteVenta(Integer id);
}
