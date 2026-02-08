package com.example.Ejemplo.repository;

import com.example.Ejemplo.model.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentasRepository extends JpaRepository<Ventas,Integer> {
}
