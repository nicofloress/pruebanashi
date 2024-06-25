package org.example.sitemaAlmacemaniento;

import org.example.Producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Modulo {
    private int numero;
    private List<Nivel> niveles;

    public Modulo(int numero, int cantidadNiveles, double capacidadNivel) {
        this.numero = numero;
        this.niveles = new ArrayList<>();
        for (int i = 0; i < cantidadNiveles; i++) {
            niveles.add(new Nivel(i + 1, capacidadNivel));
        }
    }
    public boolean agregarProducto(Producto producto) {
        for (Nivel nivel : niveles) {
            if (nivel.agregarProducto(producto)) {
                return true;
            }
        }
        return false;
    }

    public Producto removerProducto(Integer hashProducto) {
        for (Nivel nivel : niveles) {
            Producto producto = nivel.removerProducto(hashProducto);
            if (producto != null) {
                return producto;
            }
        }
        return null;
    }

    public Producto buscarProducto(Integer hashProducto) {
        for (Nivel nivel : niveles) {
            Producto producto = nivel.getProducto(hashProducto);
            if (producto != null) {
                return producto;
            }
        }
        return null;
    }

    public int getNumero() {
        return numero;
    }

    public List<Nivel> getNiveles() {
        return niveles;
    }
    public int getNivelDeProducto(Integer hashProducto) {
        for (int i = 0; i < niveles.size(); i++) {
            if (niveles.get(i).getProducto(hashProducto) != null) {
                return i + 1;
            }
        }
        return -1;
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        for (Nivel nivel : niveles) {
            productos.addAll(nivel.getProductos());
        }
        return productos;
    }

    public boolean agregarProductoEnNivel(Producto producto, int numeroNivel) {
        if (numeroNivel > 0 && numeroNivel <= niveles.size()) {
            return niveles.get(numeroNivel - 1).agregarProducto(producto);
        }
        return false;
    }


}
