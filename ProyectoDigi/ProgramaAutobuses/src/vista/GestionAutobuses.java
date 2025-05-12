package vista;


import java.util.ArrayList;
import java.util.Random;
import java.util.SplittableRandom;

public class GestionAutobuses {

    private ArrayList<GPSData> buses;
    private Random random;

    public GestionAutobuses() {
        buses = new ArrayList<>();
        random = new Random();

        // Inicializa 10 autobuses con datos iniciales
        for (int i = 1; i <= 2; i++) {
            buses.add(new GPSData(i, 0, 0.0, 0.0, 0));
        }
    }

    public void simularMovimientos(int ciclos) {
        for (int t = 1; t <= ciclos; t++) {
            System.out.println("=== Ciclo " + t + " ===");

            for (GPSData bus : buses) {
                // Actualiza tiempo
                bus.timestamp = t;

                // Simula una velocidad aleatoria entre 0 y 100 km/h
                bus.speed = random.nextInt(10,50);

                // Simula un pequeño movimiento geográfico
                bus.lat += (random.nextDouble(0.1)*bus.speed);
                bus.lon += (random.nextDouble(0.1)*bus.speed);

                System.out.println(bus);
            }

            System.out.println();
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
