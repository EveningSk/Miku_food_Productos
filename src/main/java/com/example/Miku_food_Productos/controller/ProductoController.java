package com.example.Miku_food_Productos.controller;

import com.example.Miku_food_Productos.dto.ProductoRequestDTO;
import com.example.Miku_food_Productos.dto.ProductoResponseDTO;
import com.example.Miku_food_Productos.model.Producto;
import com.example.Miku_food_Productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Gestion de Productos", description = "Operaciones basicas: crear, listar, obtener por ID")
public class ProductoController {

    @Autowired
    private ProductoService servicio;

    @Operation(summary = "Listar todos los productos", description = "Obtiene la lista completa de productos")
    @GetMapping
    public List<ProductoResponseDTO> listar() { 
        return servicio.listarTodos().stream()  //Obtiene la lista de productos
                .map(ProductoResponseDTO::new)  //Convierte cada Producto a ProductoResponseDTO
                .collect(Collectors.toList());  //Devuelve como lista
    }


    //endpoints para crear nuevos productos
    @Operation(summary = "Crear producto", description = "Crea un nuevo producto")
    @PostMapping
    public ProductoResponseDTO crear(@RequestBody ProductoRequestDTO dto) {
        Producto producto = new Producto(
                null,   //ID null porque lo genera la base de datos
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecio(),
                dto.getCategoria(),
                dto.getDisponible()
        );

        //Guarda el producto usando el servicio
        Producto creado = servicio.crear(producto);
        return new ProductoResponseDTO(creado); //Retorna el producto creado como DTO de respuesta
    }

    @Operation(summary = "Obtener producto por ID", description = "Obtiene un producto por su ID")
    @GetMapping("/{id}")
    public ProductoResponseDTO obtener(@PathVariable Long id) {
        return servicio.obtenerPorId(id)
                .map(ProductoResponseDTO::new) //Convierte el producto encontrado a DTO
                .orElse(null);
    }
}
