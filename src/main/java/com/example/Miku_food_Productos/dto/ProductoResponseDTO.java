package com.example.Miku_food_Productos.dto;

import com.example.Miku_food_Productos.model.Producto;

public class ProductoResponseDTO {


    //Atributos que se enviaran
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private Boolean disponible;


    //Permite crear un objeto ProductoResponseDTO pasando todos los datos manualmente
    public ProductoResponseDTO(Long id, String nombre, String descripcion, Double precio, String categoria, Boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.disponible = disponible;
    }


    //Constructor que recibe directamente un objeto Producto del modelo
    //Permite mapear automatico los datos desde la entidad Producto hacia el DTO
    public ProductoResponseDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.categoria = producto.getCategoria();
        this.disponible = producto.getDisponible();
    }

    //Metodo Getter y Setter (acceso y modificación de datos)   

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }
}
