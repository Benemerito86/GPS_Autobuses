package paquetePrincipal;
import vista.*;

public class Main {
    public static void main(String[] args) {
        GestionAutobuses gestionAutobuses = new GestionAutobuses();
        GestionAutobuses gestion = new GestionAutobuses();
        gestion.resetearFolder();
        gestion.simularMovimientos(60);
        // VentanaPrincipal frame = new VentanaPrincipal();
        // frame.setVisible(true);
    }
}