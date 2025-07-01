package com.example.Miku_food_Productos.controller;

import com.example.Miku_food_Productos.dto.ProductoRequestDTO;
import com.example.Miku_food_Productos.dto.ProductoResponseDTO;
import com.example.Miku_food_Productos.model.Producto;
import com.example.Miku_food_Productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productos/gestion")
@Tag(name = "Gestion de Actualizacion y Eliminacion", description = "Actualiza y elimina productos")
public class ProductoActualizarEliController {

    @Autowired
    private ProductoService servicio;

    @Operation(summary = "Actualizar producto", description = "Actualiza un producto mediante su ID")
    @PutMapping("/{id}")
    public ProductoResponseDTO actualizar(@PathVariable Long id, @RequestBody ProductoRequestDTO dto) {
        Producto producto = new Producto( //// Crea un objeto Producto con los datos recibidos en el DTO y el ID
                id,
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecio(),
                dto.getCategoria(),
                dto.getDisponible()
        );
        Producto actualizado = servicio.actualizar(id, producto);
        //Llama al servicio para actualizar el producto
        return new ProductoResponseDTO(actualizado); //Devuelve el producto actualizado como un DTO de respuesta
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto mediante su ID")
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return "Producto eliminado correctamente";
    }
}
