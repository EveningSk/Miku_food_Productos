package com.example.Miku_food_Productos.controllertest;

import com.example.Miku_food_Productos.controller.ProductoController;
import com.example.Miku_food_Productos.model.Producto;
import com.example.Miku_food_Productos.service.ProductoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    private Producto producto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto(1L, "Pizza", "Con queso", 7990.0, "Comida", true);
    }

    @Test
    public void testListarProductos() {
        when(productoService.listarTodos()).thenReturn(Arrays.asList(producto));
        List<Producto> resultado = productoController.listar();
        assertEquals(1, resultado.size());
    }

    @Test
    public void testBuscarPorIdExiste() {
        when(productoService.obtenerPorId(1L)).thenReturn(Optional.of(producto));
        Producto resultado = productoController.obtener(1L);
        assertNotNull(resultado);
        assertEquals("Pizza", resultado.getNombre());
    }

    @Test
    public void testBuscarPorIdNoExiste() {
        when(productoService.obtenerPorId(2L)).thenReturn(Optional.empty());
        Producto resultado = productoController.obtener(2L);
        assertNull(resultado);
    }

    @Test
    public void testCrearProducto() {
        when(productoService.crear(any())).thenReturn(producto);
        Producto creado = productoController.crear(producto);
        assertEquals("Pizza", creado.getNombre());
    }

    @Test
    public void testActualizarProducto() {
        when(productoService.actualizar(eq(1L), any())).thenReturn(producto);
        Producto actualizado = productoController.actualizar(1L, producto);
        assertEquals("Pizza", actualizado.getNombre());
    }

    @Test
    public void testEliminarProducto() {
        doNothing().when(productoService).eliminar(1L);
        productoController.eliminar(1L);
        verify(productoService, times(1)).eliminar(1L);
    }

    @Test
    public void testBuscarPorNombre() {
        when(productoService.buscarPorNombre("pizza")).thenReturn(Arrays.asList(producto));
        List<Producto> lista = productoController.buscarPorNombre("pizza");
        assertEquals(1, lista.size());
    }

    @Test
    public void testBuscarPorCategoria() {
        when(productoService.buscarPorCategoria("Comida")).thenReturn(Arrays.asList(producto));
        List<Producto> lista = productoController.buscarPorCategoria("Comida");
        assertEquals(1, lista.size());
    }

    @Test
    public void testBuscarPorRangoPrecio() {
        when(productoService.buscarPorRangoPrecio(5000, 8000)).thenReturn(Arrays.asList(producto));
        List<Producto> lista = productoController.buscarPorRangoPrecio(5000, 8000);
        assertEquals(1, lista.size());
    }
}
