package vista;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {



            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 960, 540);
            setLocationRelativeTo(null);

            PanelContenido panel = new PanelContenido();

            add(panel);

            setVisible(true);


        }

}
