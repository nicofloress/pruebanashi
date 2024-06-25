package org.example.sitemaAlmacemaniento;

import org.example.Producto.Producto;

import java.util.*;

public class SistemaAlmacenamiento {
    private Map<Integer, Ubicacion> mapaProductos;
    private List<Estanteria> estanterias;

    public SistemaAlmacenamiento(int numeroEstanterias, int modulosPorEstanteria, int nivelesPorModulo, double capacidadNivel) {
        this.mapaProductos = new HashMap<>();
        this.estanterias = new ArrayList<>();

        for (int i = 0; i < numeroEstanterias; i++) {
            estanterias.add(new Estanteria(i + 1, modulosPorEstanteria, nivelesPorModulo, capacidadNivel));
        }
    }

    public boolean almacenarProducto(Producto producto) {
        for (Estanteria estanteria : estanterias) {
            if (estanteria.agregarProducto(producto)) {
                Ubicacion ubicacion = new Ubicacion(estanteria.getNumero(),
                        estanteria.getModuloDeProducto(producto.getHashProducto()),
                        estanteria.getNivelDeProducto(producto.getHashProducto()));
                mapaProductos.put(producto.getHashProducto(), ubicacion);
                return true;
            }
        }
        return false;
    }

    public Producto retirarProducto(Integer hashProducto) {
        Ubicacion ubicacion = mapaProductos.get(hashProducto);
        if (ubicacion != null) {
            Estanteria estanteria = estanterias.get(ubicacion.getEstanteria() - 1);
            Producto producto = estanteria.removerProducto(hashProducto);
            if (producto != null) {
                mapaProductos.remove(hashProducto);
                return producto;
            }
        }
        return null;
    }

    public Ubicacion obtenerUbicacion(Integer hashProducto) {
        return mapaProductos.get(hashProducto);
    }

    public Producto obtenerProducto(Integer hashProducto) {
        Ubicacion ubicacion = mapaProductos.get(hashProducto);
        if (ubicacion != null) {
            Estanteria estanteria = estanterias.get(ubicacion.getEstanteria() - 1);
            Producto producto = estanteria.buscarProducto(hashProducto);

            // Verificación adicional en caso de que la ubicación no esté actualizada
            if (producto == null) {
                // Buscar en todas las estanterías si no se encuentra en la ubicación esperada
                for (Estanteria e : estanterias) {
                    producto = e.buscarProducto(hashProducto);
                    if (producto != null) {
                        // Actualizar la ubicación si se encuentra en una estantería diferente
                        Ubicacion nuevaUbicacion = new Ubicacion(
                                e.getNumero(),
                                e.getModuloDeProducto(hashProducto),
                                e.getNivelDeProducto(hashProducto)
                        );
                        mapaProductos.put(hashProducto, nuevaUbicacion);
                        break;
                    }
                }
            }
            return producto;
        }
        return null;
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> todosLosProductos = new ArrayList<>();
        for (Estanteria estanteria : estanterias) {
            todosLosProductos.addAll(estanteria.obtenerTodosLosProductos());
        }
        return todosLosProductos;
    }

    public boolean moverProducto(Integer hashProducto, Ubicacion nuevaUbicacion) {
        Producto producto = retirarProducto(hashProducto);
        if (producto != null) {
            Estanteria nuevaEstanteria = estanterias.get(nuevaUbicacion.getEstanteria() - 1);
            if (nuevaEstanteria.agregarProductoEnUbicacion(producto, nuevaUbicacion.getModulo(), nuevaUbicacion.getNivel())) {
                mapaProductos.put(hashProducto, nuevaUbicacion);
                return true;
            } else {
                // Si no se pudo mover, lo volvemos a su ubicación original
                almacenarProducto(producto);
            }
        }
        return false;
    }

    public int getNumeroEstanterias() {
        return estanterias.size();
    }

    public Estanteria getEstanteria(int numero) {
        return estanterias.get(numero - 1);
    }

    public Ubicacion obtenerUbicacion(Producto producto) {
        return mapaProductos.get(producto.getHashProducto());
    }



    // Otros métodos según sea necesario
}

