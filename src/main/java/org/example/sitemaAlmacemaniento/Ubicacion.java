package org.example.sitemaAlmacemaniento;

import java.util.Objects;

public class Ubicacion {
    private int estanteria;
    private int modulo;
    private int nivel;

    public Ubicacion(int estanteria, int modulo, int nivel) {
        this.estanteria = estanteria;
        this.modulo = modulo;
        this.nivel = nivel;
    }
    public int getEstanteria() {
        return estanteria;
    }

    public int getModulo() {
        return modulo;
    }

    public int getNivel() {
        return nivel;
    }

    @Override
    public String toString() {
        return "Estantería: " + estanteria + ", Módulo: " + modulo + ", Nivel: " + nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return estanteria == ubicacion.estanteria &&
                modulo == ubicacion.modulo &&
                nivel == ubicacion.nivel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(estanteria, modulo, nivel);
    }

    // Getters
}
