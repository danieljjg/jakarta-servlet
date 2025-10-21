package org.daniel.webapp.headers.models;

public class Producto {
    private Long id;
    private String nombre;
    private String tipo;
    private int precio;

    public Producto() {}

    public Producto(Long id, String nombre, String tipo, int Precio) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = Precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo() {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
