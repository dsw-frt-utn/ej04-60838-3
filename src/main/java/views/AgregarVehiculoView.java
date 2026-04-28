package views;

import data.Persistencia;
import domain.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AgregarVehiculoView extends JFrame {
    private JTextField txtPatente, txtModelo, txtAnio, txtCapacidad, txtKwhBase, txtKmLitro, txtLitrosExtra;
    private JComboBox<Marca> comboMarca;
    private JComboBox<Sucursal> comboSucursal;
    private JComboBox<String> comboTipo;
    private JPanel panelEspecifico;
    private CardLayout cardLayout;

    public AgregarVehiculoView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Agregar Nuevo Vehículo");
        setSize(400, 550);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panelForm = new JPanel(new GridLayout(0, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelForm.add(new JLabel("Patente:"));
        txtPatente = new JTextField();
        panelForm.add(txtPatente);

        panelForm.add(new JLabel("Marca:"));
        comboMarca = new JComboBox<>();
        comboMarca.setEditable(true); // ARREGLO: Podés elegir o escribir la marca!
        comboMarca.addItem(new Marca("Renault"));
        comboMarca.addItem(new Marca("Ford"));
        comboMarca.addItem(new Marca("Iveco"));
        comboMarca.addItem(new Marca("Mercedes"));
        panelForm.add(comboMarca);

        panelForm.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        panelForm.add(txtModelo);

        panelForm.add(new JLabel("Año:"));
        txtAnio = new JTextField();
        panelForm.add(txtAnio);

        panelForm.add(new JLabel("Capacidad Carga (kg):"));
        txtCapacidad = new JTextField();
        panelForm.add(txtCapacidad);

        panelForm.add(new JLabel("Sucursal:"));
        comboSucursal = new JComboBox<>();
        for (Sucursal s : Persistencia.getSucursales()) {
            comboSucursal.addItem(s);
        }
        panelForm.add(comboSucursal);

        panelForm.add(new JLabel("Tipo de Vehículo:"));
        comboTipo = new JComboBox<>(new String[]{"ELECTRICO", "COMBUSTIBLE"});
        panelForm.add(comboTipo);

        add(panelForm, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        panelEspecifico = new JPanel(cardLayout);
        panelEspecifico.setBorder(BorderFactory.createTitledBorder("Datos Específicos"));

        JPanel pnlElectrico = new JPanel(new GridLayout(0, 2, 10, 10));
        pnlElectrico.add(new JLabel("kWh Base:"));
        txtKwhBase = new JTextField();
        pnlElectrico.add(txtKwhBase);
        panelEspecifico.add(pnlElectrico, "ELECTRICO");

        JPanel pnlCombustible = new JPanel(new GridLayout(0, 2, 10, 10));
        pnlCombustible.add(new JLabel("Km por Litro:"));
        txtKmLitro = new JTextField();
        pnlCombustible.add(txtKmLitro);
        pnlCombustible.add(new JLabel("Litros Extra (cada 15km):"));
        txtLitrosExtra = new JTextField();
        pnlCombustible.add(txtLitrosExtra);
        panelEspecifico.add(pnlCombustible, "COMBUSTIBLE");

        add(panelEspecifico, BorderLayout.CENTER);

        comboTipo.addActionListener(e -> {
            cardLayout.show(panelEspecifico, (String) comboTipo.getSelectedItem());
        });

        JButton btnGuardar = new JButton("Guardar Vehículo");
        btnGuardar.addActionListener(e -> guardarVehiculo());
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void guardarVehiculo() {
        try {
            String patente = txtPatente.getText();
            Object item = comboMarca.getSelectedItem();
            Marca marca = (item instanceof Marca) ? (Marca)item : new Marca(item.toString());
            
            String modelo = txtModelo.getText();
            int anio = Integer.parseInt(txtAnio.getText());
            double capacidad = Double.parseDouble(txtCapacidad.getText());
            Sucursal sucursal = (Sucursal) comboSucursal.getSelectedItem();
            String tipo = (String) comboTipo.getSelectedItem();

            Vehiculo nuevo;
            if (tipo.equals("ELECTRICO")) {
                double kwh = Double.parseDouble(txtKwhBase.getText());
                nuevo = new VehiculoElectrico(patente, marca, modelo, anio, capacidad, sucursal, kwh);
            } else {
                double kmL = Double.parseDouble(txtKmLitro.getText());
                double extra = Double.parseDouble(txtLitrosExtra.getText());
                nuevo = new VehiculoCombustible(patente, marca, modelo, anio, capacidad, sucursal, kmL, extra);
            }

            Persistencia.agregarVehiculo(nuevo);
            JOptionPane.showMessageDialog(this, "Vehículo guardado con éxito.");
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
        }
    }
}
