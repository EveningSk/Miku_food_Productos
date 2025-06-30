package com.example.Miku_food_Productos.controller;

import com.example.Miku_food_Productos.model.Producto;
import com.example.Miku_food_Productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService servicio;

    // Mostrar todos los productos
    @GetMapping
    public List<Producto> listar() {
        return servicio.listarTodos();
    }

    // Crear un producto nuevo
    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return servicio.crear(producto);
    }

    // Buscar un producto por su ID
    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    // Actualizar un producto por su ID
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return servicio.actualizar(id, producto);
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }

    // Buscar productos 
    @GetMapping("/buscar")
    public List<Producto> buscarPorNombre(@RequestParam String nombre) {
        return servicio.buscarPorNombre(nombre);
    }

    // Buscar productos por categoria 
    @GetMapping("/categoria/{categoria}")
    public List<Producto> buscarPorCategoria(@PathVariable String categoria) {
        return servicio.buscarPorCategoria(categoria);
    }

    // Buscar productos por precio entre un minimo y un maximo
    @GetMapping("/precio")
    public List<Producto> buscarPorRangoPrecio(@RequestParam double min, @RequestParam double max) {
        return servicio.buscarPorRangoPrecio(min, max);
    }
}
