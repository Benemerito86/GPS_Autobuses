package paquetePrincipal;
import vista.*;

public class Main {
    public static void main(String[] args) {

        GestionAutobuses gestionAutobuses = new GestionAutobuses();
        GestionAutobuses gestion = new GestionAutobuses();
        gestion.simularMovimientos(10);
        // VentanaPrincipal frame = new VentanaPrincipal();
        // frame.setVisible(true);
    }
}