package com.example.Miku_food_Productos.servicetest;

import com.example.Miku_food_Productos.model.Producto;
import com.example.Miku_food_Productos.repository.ProductoRepository;
import com.example.Miku_food_Productos.service.ProductoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository repo;

    @InjectMocks
    private ProductoService servicio;

    // 1. Listar
    @Test
    public void testListar() {
        Producto producto = new Producto(1L, "Pizza", "Con queso", 7990.0, "Comida", true);
        when(repo.findAll()).thenReturn(Arrays.asList(producto));

        List<Producto> lista = servicio.listarTodos();

        assertEquals(1, lista.size(), "La lista debe tener 1 producto");
    }

    // 2. Crear
    @Test
    public void testCrear() {
        Producto nuevo = new Producto(null, "Sushi", "Salmón", 9990.0, "Comida", true);
        Producto guardado = new Producto(1L, "Sushi", "Salmón", 9990.0, "Comida", true);

        when(repo.save(nuevo)).thenReturn(guardado);

        Producto creado = servicio.crear(nuevo);

        assertEquals("Sushi", creado.getNombre(), "El nombre del producto debe ser Sushi");
    }

    // 3. Buscar por ID
    @Test
    public void testObtenerPorId() {
        Producto producto = new Producto(1L, "Empanada", "Pino", 1500.0, "Snack", true);
        when(repo.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = servicio.obtenerPorId(1L);

        assertTrue(resultado.isPresent(), "El producto debe estar presente");
    }

    // 4. Actualizar
    @Test
    public void testActualizar() {
        Producto datos = new Producto(null, "Bebida", "Cola 500ml", 1200.0, "Bebestible", true);

        when(repo.save(any())).thenReturn(new Producto(5L, datos.getNombre(), datos.getDescripcion(),
                datos.getPrecio(), datos.getCategoria(), datos.getDisponible()));

        Producto actualizado = servicio.actualizar(5L, datos);

        assertEquals("Bebida", actualizado.getNombre(), "El nombre actualizado debe ser Bebida");
    }

    // 5. Eliminar
    @Test
    public void testEliminar() {
        Long id = 1L;
        doNothing().when(repo).deleteById(id);

        servicio.eliminar(id);

        verify(repo, times(1)).deleteById(id);
    }

    // 6. Buscar por nombre
    @Test
    public void testBuscarPorNombre() {
        Producto producto = new Producto(2L, "Torta", "Chocolate", 5000.0, "Postre", true);
        when(repo.findByNombreContainingIgnoreCase("torta")).thenReturn(Arrays.asList(producto));

        List<Producto> lista = servicio.buscarPorNombre("torta");

        assertEquals(1, lista.size(), "Debe encontrar 1 producto con nombre parecido a 'torta'");
    }

    // 7. Buscar por categoría
    @Test
    public void testBuscarPorCategoria() {
        Producto producto = new Producto(3L, "Helado", "Vainilla", 3000.0, "Postre", true);
        when(repo.findByCategoriaIgnoreCase("Postre")).thenReturn(Arrays.asList(producto));

        List<Producto> lista = servicio.buscarPorCategoria("Postre");

        assertEquals(1, lista.size(), "Debe encontrar 1 producto en categoría 'Postre'");
    }

    // 8. Buscar por rango de precio
    @Test
    public void testBuscarPorRangoPrecio() {
        Producto producto = new Producto(4L, "Jugo", "Natural", 2500.0, "Bebida", true);
        when(repo.findByPrecioBetween(2000, 3000)).thenReturn(Arrays.asList(producto));

        List<Producto> lista = servicio.buscarPorRangoPrecio(2000, 3000);

        assertEquals(1, lista.size(), "Debe encontrar 1 producto dentro del rango de precios");
    }
}
