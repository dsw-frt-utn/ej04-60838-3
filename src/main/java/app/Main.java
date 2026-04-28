package app;

import data.Persistencia;
import views.MenuView;

public class Main {
    public static void main(String[] args) {
        // Inicializamos los datos (sucursales, responsables)
        Persistencia.inicializar();
        
        // Abrimos el Menú Principal en lugar de la lista directamente
        java.awt.EventQueue.invokeLater(() -> {
            new MenuView().setVisible(true);
        });
    }
}
