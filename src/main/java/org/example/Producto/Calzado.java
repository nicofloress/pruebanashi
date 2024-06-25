package org.example.Producto;

import org.example.enums.Empresa;
import org.example.enums.Prioridad;
import org.example.enums.Segmento;

public class Calzado extends Producto {
    private final Segmento segmento;


    public Calzado(String marca, String articulo, Integer talle, Integer stock, Double volumen, Prioridad prioridad, Empresa empresa, Segmento segmento) {
        super(marca, articulo, talle, stock, volumen, prioridad, empresa);
        this.segmento = segmento;
    }

    public Segmento getSegmento() {
        return segmento;
    }
    @Override
    public String toString() {
        return "Calzado: " + super.toString() + "Segmento: " + segmento;
    }
}
