package com.example.Miku_food_Productos.service;

import com.example.Miku_food_Productos.model.Producto;
import com.example.Miku_food_Productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public List<Producto> listarTodos() {
        return repo.findAll();
    }

    public Producto crear(Producto producto) {
        return repo.save(producto);
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public Producto actualizar(Long id, Producto producto) {
        producto.setId(id);
        return repo.save(producto);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return repo.findByCategoriaIgnoreCase(categoria);
    }

    public List<Producto> buscarPorRangoPrecio(double min, double max) {
        return repo.findByPrecioBetween(min, max);
    }
}
