package com.example.Miku_food_Productos.controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.example.Miku_food_Productos.controller.ProductoActualizarEliController;
import com.example.Miku_food_Productos.controller.ProductoBusquedaController;
import com.example.Miku_food_Productos.controller.ProductoController;
import com.example.Miku_food_Productos.dto.ProductoRequestDTO;
import com.example.Miku_food_Productos.dto.ProductoResponseDTO;
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

public class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @InjectMocks
    private ProductoBusquedaController productoBusquedaController;

    @InjectMocks
    private ProductoActualizarEliController productoActualizarEliController;

    private Producto producto;
    private List<Producto> listaProductos;
    private ProductoRequestDTO productoRequestDTO;

    // Metodo que se ejecuta antes de cada test para inicializar datos y mocks
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks

        //Crea un producto de prueba
        producto = new Producto(1L, "Sushi", "Sushi delicioso", 15.50, "Comida", true);

        // Crea la lista con ese producto
        listaProductos = Arrays.asList(producto);

        //DTO de peticion para pruebas de crear y actualizar
        productoRequestDTO = new ProductoRequestDTO();
        productoRequestDTO.setNombre("Sushi");
        productoRequestDTO.setDescripcion("Sushi delicioso");
        productoRequestDTO.setPrecio(15.50);
        productoRequestDTO.setCategoria("Comida");
        productoRequestDTO.setDisponible(true);
    }

    //Test para listar todos los productos usando el controlador
    @Test
    void testListarProductos() {
        when(productoService.listarTodos()).thenReturn(listaProductos);
        List<ProductoResponseDTO> response = productoController.listar();
        assertEquals(1, response.size());
        assertEquals("Sushi", response.get(0).getNombre());
    }

    //Test para agregar un producto usando el controlador
    @Test
    void testAgregarProducto() {
        when(productoService.crear(any(Producto.class))).thenReturn(producto);
        ProductoResponseDTO response = productoController.crear(productoRequestDTO);
        assertNotNull(response);
        assertEquals("Sushi", response.getNombre());
        verify(productoService, times(1)).crear(any(Producto.class));
    }


    //Test para buscar un producto por ID
    @Test
    void testBuscarIdExistente() {
        when(productoService.obtenerPorId(1L)).thenReturn(Optional.of(producto));
        ProductoResponseDTO response = productoController.obtener(1L);
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    //Test para buscar producto por nombre
    @Test
    void testBuscarPorNombre() {
        when(productoService.buscarPorNombre("Sushi")).thenReturn(listaProductos);
        List<ProductoResponseDTO> response = productoBusquedaController.buscarPorNombre("Sushi");
        assertFalse(response.isEmpty());
        assertEquals("Sushi", response.get(0).getNombre());
    }
     // Test para buscar productos por categoría
    @Test
    void testBuscarPorCategoria() {
        when(productoService.buscarPorCategoria("Comida")).thenReturn(listaProductos);
        List<ProductoResponseDTO> response = productoBusquedaController.buscarPorCategoria("Comida");
        assertFalse(response.isEmpty());
        assertEquals("Comida", response.get(0).getCategoria());
    }

    //Test para bucar productos por rango de precio
    @Test
    void testBuscarPorRangoPrecio() {
        when(productoService.buscarPorRangoPrecio(10.0, 20.0)).thenReturn(listaProductos);
        List<ProductoResponseDTO> response = productoBusquedaController.buscarPorRangoPrecio(10.0, 20.0);
        assertFalse(response.isEmpty());
        assertTrue(response.get(0).getPrecio() >= 10.0 && response.get(0).getPrecio() <= 20.0);
    }



    //Test para actualizar un producto existente
    @Test
    void testActualizarProducto() {
        Producto productoActualizado = new Producto(1L, "Sushi Premium", "Mejor calidad", 20.0, "Comida", true);
        when(productoService.actualizar(eq(1L), any(Producto.class))).thenReturn(productoActualizado); //Simula la actualización
        ProductoResponseDTO response = productoActualizarEliController.actualizar(1L, productoRequestDTO);
        assertNotNull(response); //Verifica que no sea null
        assertEquals("Sushi Premium", response.getNombre()); //Verifica que el nombre haya sido actualizado
    }


    // Test para eliminar un producto
    @Test
    void testEliminarProducto() {
        doNothing().when(productoService).eliminar(1L); // Simula la eliminación
        String result = productoActualizarEliController.eliminar(1L);   //Llama al pedido
        assertEquals("Producto eliminado correctamente", result);// Verifica el mensaje de confirmacion
        verify(productoService, times(1)).eliminar(1L); //Verifica que el método se haya llamado una vez
    }
}
