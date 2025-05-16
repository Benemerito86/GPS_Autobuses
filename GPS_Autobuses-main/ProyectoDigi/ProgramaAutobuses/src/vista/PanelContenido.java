package vista;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class PanelContenido extends JPanel {

    private Image background;
    private JTextField texto; // Campo de texto como atributo
    Random rand = new Random();
    int contador = rand.nextInt(3000);

    public PanelContenido() {
        setLayout(null);
        background = new ImageIcon(getClass().getResource("/assets/images/fon.jpg")).getImage();

        texto = new JTextField("0");
        texto.setOpaque(false);
        texto.setBorder(null);
        texto.setForeground(Color.BLACK);
        texto.setFont(new Font("Arial", Font.BOLD, 20));
        texto.setHorizontalAlignment(JTextField.CENTER);
        texto.setBounds(0, 50, 400, 80);
        add(texto);

        Timer timer = new Timer(rand.nextInt(3000)+1000, new ActionListener() {
            int contador = 1; // empieza en 1 porque ya mostramos "0"
            @Override
            public void actionPerformed(ActionEvent e) {
                texto.setText(String.valueOf(rand.nextInt(3000)));
                contador++;

                if (contador > 100) { // ejemplo: parar en 5
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.start();
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
