package org.example.sitemaAlmacemaniento;

import org.example.Producto.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nivel {
    private int numero;
    private Map<Integer, Producto> productos;
    private double capacidadMaxima;
    private double capacidadOcupada;

    public Nivel(int numero, double capacidadMaxima) {
        this.numero = numero;
        this.capacidadMaxima = capacidadMaxima;
        this.capacidadOcupada = 0;
        this.productos = new HashMap<>();
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setProductos(Map<Integer, Producto> productos) {
        this.productos = productos;
    }

    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(double capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public double getCapacidadOcupada() {
        return capacidadOcupada;
    }

    public void setCapacidadOcupada(double capacidadOcupada) {
        this.capacidadOcupada = capacidadOcupada;
    }

    public boolean agregarProducto(Producto producto) {
        if (capacidadOcupada + producto.getVolumen() <= capacidadMaxima) {
            productos.put(producto.getHashProducto(), producto);
            capacidadOcupada += producto.getVolumen();
            return true;
        }
        return false;
    }


    public Producto removerProducto(Integer hashProducto) {
        Producto producto = productos.remove(hashProducto);
        if (producto != null) {
            capacidadOcupada -= producto.getVolumen();
        }
        return producto;
    }

    public Producto getProducto(Integer hashProducto) {
        return productos.get(hashProducto);
    }

    public int getNumero() {
        return numero;
    }

    public double getCapacidadDisponible() {
        return capacidadMaxima - capacidadOcupada;
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos.values());
    }
}