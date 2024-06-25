package org.example;

import org.example.Producto.Calzado;
import org.example.Producto.Indumentaria;
import org.example.Producto.Producto;
import org.example.enums.Empresa;
import org.example.enums.Prioridad;
import org.example.enums.Segmento;
import org.example.sitemaAlmacemaniento.SistemaAlmacenamiento;
import org.example.sitemaAlmacemaniento.Ubicacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.List;

public class AlmacenamientoGUI extends JFrame {
    private SistemaAlmacenamiento sistema;
    private JTextField marcaField, articuloField, talleField, stockField, volumenField;
    private JComboBox<Prioridad> prioridadCombo;
    private JComboBox<Empresa> empresaCombo;
    private JComboBox<Segmento> segmentoCombo;
    private JTextArea resultadoArea;

    public AlmacenamientoGUI(SistemaAlmacenamiento sistema) {
        this.sistema = sistema;
        setTitle("Sistema de Almacenamiento");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.add(new JLabel("Marca:"));
        marcaField = new JTextField();
        inputPanel.add(marcaField);
        inputPanel.add(new JLabel("Artículo:"));
        articuloField = new JTextField();
        inputPanel.add(articuloField);
        inputPanel.add(new JLabel("Talle:"));
        talleField = new JTextField();
        inputPanel.add(talleField);
        inputPanel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        inputPanel.add(stockField);
        inputPanel.add(new JLabel("Volumen:"));
        volumenField = new JTextField();
        inputPanel.add(volumenField);
        inputPanel.add(new JLabel("Prioridad:"));prioridadCombo = new JComboBox<>(Prioridad.values());

        inputPanel.add(new JLabel("Empresa:"));
        empresaCombo = new JComboBox<>(Empresa.values());
        inputPanel.add(empresaCombo);
        inputPanel.add(new JLabel("Segmento:"));
        segmentoCombo = new JComboBox<>(Segmento.values());
        inputPanel.add(segmentoCombo);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        JButton almacenarButton = new JButton("Almacenar");
        JButton retirarButton = new JButton("Retirar");
        JButton buscarButton = new JButton("Buscar");
        JButton inventarioButton = new JButton("Mostrar Inventario");
        buttonPanel.add(almacenarButton);
        buttonPanel.add(retirarButton);
        buttonPanel.add(buscarButton);
        buttonPanel.add(inventarioButton);

        // Área de resultados
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        // Agregar componentes a la ventana
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Agregar listeners a los botones
        almacenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlmacenamientoGUI.this.almacenarProducto();
            }

        });

        retirarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retirarProducto();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        inventarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInventario();
            }
        });
    }

        private void almacenarProducto() {
            try {
                String marca = marcaField.getText();
                String articulo = articuloField.getText();
                int talle = Integer.parseInt(talleField.getText());
                int stock = Integer.parseInt(stockField.getText());
                double volumen = Double.parseDouble(volumenField.getText());
                Prioridad prioridad = (Prioridad) prioridadCombo.getSelectedItem();

                Empresa empresa = (Empresa) empresaCombo.getSelectedItem();
                Segmento segmento = (Segmento) segmentoCombo.getSelectedItem();

                // Crear producto (ahora solo Indumentaria, ya que no hay un segmento específico para Calzado)
                Producto producto = new Indumentaria(marca, articulo, talle, stock, volumen, prioridad, segmento, empresa);

                boolean almacenado = sistema.almacenarProducto(producto);
                if (almacenado) {
                    resultadoArea.setText("Producto almacenado con éxito.");
                } else {
                    resultadoArea.setText("No se pudo almacenar el producto. Almacén lleno.");
                }
            } catch (NumberFormatException ex) {
                resultadoArea.setText("Error: Ingrese valores numéricos válidos para talle, stock y volumen.");
            } catch (IllegalArgumentException ex) {
                resultadoArea.setText("Error: Valor inválido seleccionado para Prioridad, Empresa o Segmento.");
            } catch (Exception ex) {
                resultadoArea.setText("Error inesperado: " + ex.getMessage());
            }
        }

    private void retirarProducto() {
        String marca = marcaField.getText();
        String articulo = articuloField.getText();
        int talle = Integer.parseInt(talleField.getText());
        int hashProducto = Objects.hash(marca, articulo, talle);

        Producto retirado = sistema.retirarProducto(hashProducto);
        if (retirado != null) {
            resultadoArea.setText("Producto retirado: " + retirado.toString());
        } else {
            resultadoArea.setText("No se encontró el producto.");
        }
    }

    private void buscarProducto() {
        String marca = marcaField.getText();
        String articulo = articuloField.getText();
        int talle = Integer.parseInt(talleField.getText());
        int hashProducto = Objects.hash(marca, articulo, talle);

        Producto encontrado = sistema.obtenerProducto(hashProducto);
        if (encontrado != null) {
            Ubicacion ubicacion = sistema.obtenerUbicacion(hashProducto);
            resultadoArea.setText("Producto encontrado: " + encontrado.toString() + "\nUbicación: " + ubicacion.toString());
        } else {
            resultadoArea.setText("No se encontró el producto.");
        }
    }

    private void mostrarInventario() {
        List<Producto> inventario = sistema.obtenerTodosLosProductos();
        StringBuilder sb = new StringBuilder("Inventario:\n");
        for (Producto p : inventario) {
            sb.append(p.toString()).append("\n");
        }
        resultadoArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SistemaAlmacenamiento sistema = new SistemaAlmacenamiento(5, 3, 4, 100.0);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AlmacenamientoGUI(sistema).setVisible(true);
            }
        });
    }
}