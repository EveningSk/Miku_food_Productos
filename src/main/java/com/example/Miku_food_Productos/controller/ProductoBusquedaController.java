package com.example.Miku_food_Productos.controller;

import com.example.Miku_food_Productos.dto.ProductoResponseDTO;
import com.example.Miku_food_Productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//Controlador encargado de la búsqueda de productos usando filtros
@RestController
@RequestMapping("/api/v1/productos/busqueda")
@Tag(name = "Busqueda de Productos", description = "Filtros por nombre, categoria y rango de precios")
public class ProductoBusquedaController {

    @Autowired
    private ProductoService servicio;

    @Operation(summary = "Buscar por nombre", description = "Busca productos por su nombre")
    @GetMapping("/nombre")
    public List<ProductoResponseDTO> buscarPorNombre(@RequestParam String nombre) {
        return servicio.buscarPorNombre(nombre).stream()    //Llama al servicio y convierte la lista
                .map(ProductoResponseDTO::new)  //Convierte cada Producto a ProductoResponseDTO
                .collect(Collectors.toList());  //Devuelve una lista de DTOs
    }

    @Operation(summary = "Buscar por categoria", description = "Obtiene productos de una categoria")
    @GetMapping("/categoria/{categoria}")
    public List<ProductoResponseDTO> buscarPorCategoria(@PathVariable String categoria) {
        return servicio.buscarPorCategoria(categoria).stream()
                .map(ProductoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Buscar por rango de precios", description = "Busca productos dentro de un rango")
    @GetMapping("/precio")
    public List<ProductoResponseDTO> buscarPorRangoPrecio(@RequestParam double min, @RequestParam double max) {
        return servicio.buscarPorRangoPrecio(min, max).stream()
                .map(ProductoResponseDTO::new)
                .collect(Collectors.toList());
    }
}
