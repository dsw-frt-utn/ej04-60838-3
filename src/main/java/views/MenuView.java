package views;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {

    public MenuView() {
        initComponents();
    }

    private void initComponents() {
        JButton btnListar = new JButton("Listar Vehículos");
        JButton btnAgregar = new JButton("Agregar Vehículo");

        setTitle("Menú Principal - Logística");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(new Dimension(300, 150));
        setLocationRelativeTo(null); // Centrar en pantalla

        add(btnListar);
        add(btnAgregar);

        // Acción para abrir la lista de vehículos
        btnListar.addActionListener(e -> {
            new ListarVehiculosView().setVisible(true);
        });

        // Acción para abrir la ventana de agregar
        btnAgregar.addActionListener(e -> {
            new AgregarVehiculoView().setVisible(true);
        });
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new MenuView().setVisible(true);
        });
    }
}
