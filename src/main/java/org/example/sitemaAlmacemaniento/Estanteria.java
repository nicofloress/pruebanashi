package org.example.sitemaAlmacemaniento;

import org.example.Producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Estanteria {
    private int numero;
    private List<Modulo> modulos;

    public Estanteria(int numero, int cantidadModulos, int nivelesPerModulo, double capacidadNivel) {
        this.numero = numero;
        this.modulos = new ArrayList<>();
        for (int i = 0; i < cantidadModulos; i++) {
            modulos.add(new Modulo(i + 1, nivelesPerModulo, capacidadNivel));
        }
    }
    public boolean agregarProducto(Producto producto) {
        for (Modulo modulo : modulos) {
            if (modulo.agregarProducto(producto)) {
                return true;
            }
        }
        return false;
    }

    public Producto removerProducto(Integer hashProducto) {
        for (Modulo modulo : modulos) {
            Producto producto = modulo.removerProducto(hashProducto);
            if (producto != null) {
                return producto;
            }
        }
        return null;
    }

    public Producto buscarProducto(Integer hashProducto) {
        for (Modulo modulo : modulos) {
            Producto producto = modulo.buscarProducto(hashProducto);
            if (producto != null) {
                return producto;
            }
        }
        return null;
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }
    public int getModuloDeProducto(Integer hashProducto) {
        for (int i = 0; i < modulos.size(); i++) {
            if (modulos.get(i).buscarProducto(hashProducto) != null) {
                return i + 1;
            }
        }
        return -1;
    }

    public int getNivelDeProducto(Integer hashProducto) {
        for (Modulo modulo : modulos) {
            int nivel = modulo.getNivelDeProducto(hashProducto);
            if (nivel != -1) {
                return nivel;
            }
        }
        return -1;
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        for (Modulo modulo : modulos) {
            productos.addAll(modulo.obtenerTodosLosProductos());
        }
        return productos;
    }

    public boolean agregarProductoEnUbicacion(Producto producto, int numeroModulo, int numeroNivel) {
        if (numeroModulo > 0 && numeroModulo <= modulos.size()) {
            return modulos.get(numeroModulo - 1).agregarProductoEnNivel(producto, numeroNivel);
        }
        return false;
    }

}