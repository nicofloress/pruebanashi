package org.example.Producto;

import org.example.enums.Empresa;
import org.example.enums.Prioridad;
import org.example.enums.Segmento;

public class Indumentaria extends Producto {
    private final Segmento segmento;

    public Indumentaria(String marca, String articulo, Integer talle, Integer stock, Double volumen, Prioridad prioridad, Segmento segmento, Empresa empresa) {
        super(marca, articulo, talle, stock, volumen, prioridad, empresa);
        this.segmento = segmento;
    }

    public Segmento getSegmento() {
        return segmento;
    }
    @Override
    public String toString() {
        return "Indumentaria: " + super.toString()+" Segmento: " + segmento;
    }
}
