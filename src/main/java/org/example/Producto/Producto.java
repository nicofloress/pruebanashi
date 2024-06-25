package org.example.Producto;

import org.example.enums.Empresa;
import org.example.enums.Prioridad;

import java.util.Objects;


    public abstract class Producto {
        private Integer hashProducto;
        private String marca;
        private String articulo;
        private Integer talle;
        private Integer stock; //STOCK TOTAL en el CENTRO DE DISTRIBUCION
        private Double volumen;
        private Prioridad prioridad;
        private Empresa empresa;

        public Producto(String marca, String articulo, Integer talle, Integer stock, Double volumen, Prioridad prioridad, Empresa empresa) {
            this.hashProducto = Objects.hash(marca, articulo, talle);
            this.marca = marca;
            this.articulo = articulo;
            this.talle = talle;
            this.stock = stock;
            this.volumen = volumen;
            this.prioridad = prioridad;
            this.empresa = empresa;
        }

        public Integer getHashProducto() {
            return hashProducto;
        }

        public void setHashProducto(Integer hashProducto) {
            this.hashProducto = hashProducto;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public String getArticulo() {
            return articulo;
        }

        public void setArticulo(String articulo) {
            this.articulo = articulo;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

        public Integer getTalle() {
            return talle;
        }

        public void setTalle(Integer talle) {
            this.talle = talle;
        }

        public Double getVolumen() {
            return volumen;
        }

        public void setVolumen(Double volumen) {
            this.volumen = volumen;
        }

        public Prioridad getPrioridad() {
            return prioridad;
        }

        public void setPrioridad(Prioridad prioridad) {
            this.prioridad = prioridad;
        }

        public Empresa getEmpresa() {
            return empresa;
        }

        public void setEmpresa(Empresa empresa) {
            this.empresa = empresa;
        }
        @Override
        public String toString() {
            return "Producto [marca=" + marca + ", articulo=" + articulo + ", talle=" + talle +
                    ", stock=" + stock + ", volumen=" + volumen + ", prioridad=" + prioridad +
                    ", empresa=" + empresa + "]";
        }
    }
