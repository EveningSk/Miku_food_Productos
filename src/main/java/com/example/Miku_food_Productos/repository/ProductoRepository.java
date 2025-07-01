package com.example.Miku_food_Productos.repository;

import com.example.Miku_food_Productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar por nombre que contenga un texto 
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por categoria exacta  (Recuerda importar import java.util.List;)
    List<Producto> findByCategoriaIgnoreCase(String categoria);

    // Buscar productos dentro de un rango de precios
    List<Producto> findByPrecioBetween(double min, double max);
}

