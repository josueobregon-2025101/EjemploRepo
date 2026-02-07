package com.example.Ejemplo.service;

import com.example.Ejemplo.model.Repuesto;
import com.example.Ejemplo.repository.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoServiceImplements implements RepuestoService {
    public final RepuestoRepository repuestoRepository;

    public RepuestoServiceImplements(RepuestoRepository repuestoRepository){
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public List<Repuesto> getAllRepuestos() {
        return repuestoRepository.findAll();
    }

    @Override
    public Repuesto getRepuestoById(Integer id) {
        return repuestoRepository.findById(id).orElse(null);
    }

    @Override
    public Repuesto saveRepuesto(Repuesto repuesto) throws RuntimeException {
        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto updateRepuesto(Integer id, Repuesto repuesto) {
        Optional<Repuesto> repuestoExistente = repuestoRepository.findById(id);
        if(repuestoExistente.isPresent()){
            Repuesto repuestoNew = repuestoExistente.get();
            repuestoNew.setNombreRepuesto(repuesto.getNombreRepuesto());
            repuestoNew.setCategoriaRepuesto(repuesto.getCategoriaRepuesto());
            repuestoNew.setPrecioCompra(repuesto.getPrecioCompra());
            repuestoNew.setPrecioVenta(repuesto.getPrecioVenta());
            repuestoNew.setIdProveedor(repuesto.getIdProveedor());

            return repuestoRepository.save(repuestoNew);
        }else return null;
    }

    @Override
    public void deleteRepuesto(Integer id) {
    repuestoRepository.deleteById(id);
    }
}
