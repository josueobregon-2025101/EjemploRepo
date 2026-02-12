package com.example.Ejemplo.service;

import com.example.Ejemplo.model.Ventas;
import com.example.Ejemplo.repository.VentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImplements implements VentasService{
    public final VentasRepository ventasRepository;
    public VentaServiceImplements(VentasRepository ventasRepository){
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<Ventas> getAllVentas() {
        return ventasRepository.findAll();
    }

    @Override
    public Ventas getVentaById(Integer id) {
        return ventasRepository.findById(id).orElse(null);
    }

    @Override
    public Ventas saveVenta(Ventas ventas) throws RuntimeException {
        return ventasRepository.save(ventas);
    }

    @Override
    public Ventas updateVenta(Integer id, Ventas ventas) {
        Optional<Ventas> VentaExistente = ventasRepository.findById(id);
        if (VentaExistente.isPresent()){
            Ventas ventaNew = VentaExistente.get();
            ventaNew.setCantidad(ventas.getCantidad());
            ventaNew.setFechaVenta(ventas.getFechaVenta());
            ventaNew.setTotal(ventas.getTotal());
            ventaNew.setIdEmpleado(ventas.getIdEmpleado());
            ventaNew.setIdRepuesto(ventas.getIdRepuesto());

            return ventasRepository.save(ventaNew);
        }else {
            return null;
        }
    }

    @Override
    public void deleteVenta(Integer id) {
    ventasRepository.deleteById(id);
    }
}
