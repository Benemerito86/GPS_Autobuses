package vista;



import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class GestionAutobuses {

    private ArrayList<GPSData> buses;
    private Random random;
    private ArrayList<ParadasData> paradas;

    public GestionAutobuses() {
        buses = new ArrayList<>();
        paradas = new ArrayList<>();
        random = new Random();

        // Inicializa 5 autobuses
        for (int i = 1; i <= 5; i++) {
            buses.add(new GPSData(i, 0, 0.0, 0.0, 0));
        }

        // Inicializa las 14 paradas
        for (int i = 0; i <= 13; i++) {
            paradas.add(new ParadasData(i, 3 * i, 3 * i));
        }
    }

    public void simularMovimientos(int ciclos) { // Simula el movimiento en ciclos, fecha y hora de cada autobus
        for (int t = 1; t <= ciclos; t++) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            String timestamp = LocalDateTime.now().format(formatter);

            System.out.println("=== "+timestamp+ " ===");
            for (GPSData bus : buses) {
                // Actualiza tiempo
                bus.timestamp = t;

                // Simula una velocidad aleatoria entre 10 y 49 km/h
                bus.speed = 10 + random.nextInt(40);
                bus.velocidadTotal += bus.speed;
                bus.velocidadMedia = bus.velocidadTotal/t;

                // Simula un pequeño movimiento geográfico
                bus.lat += random.nextDouble() * 0.1 * bus.speed;
                bus.lon += random.nextDouble() * 0.1 * bus.speed;
                if (bus.paradaActual < paradas.size()) {
                    ParadasData destino = paradas.get(bus.paradaActual);
                    if (bus.lat > destino.latitud) {
                        bus.lat = destino.latitud;
                        bus.lon = destino.longitud;
                        bus.speed = 0;
                        bus.paradaActual++;
                        System.out.println(paradas.get(bus.paradaActual - 1));
                    }
                }

                if (bus.paradaActual >= paradas.size()) {
                    System.out.println("bus " + bus.busId + " ha llegado a su destino");
                    System.out.println("--------------------------------");
                } else {
                    System.out.println(bus);
                    System.out.println("--------------------------------");
                }

                // Exportar JSON de estado actual
                exportarEstadoBus(bus, t);
            }

            System.out.println();
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void exportarEstadoBus(GPSData bus, int t) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String timestamp = LocalDateTime.now().format(formatter);

        String json = "{\n" +
                "  \"busId\": \"" + String.format("BUS%02d", bus.busId) + "\",\n" +
                "  \"velocidadMedia\": " + bus.velocidadMedia + ",\n" +
                "  \"latitud\": " + String.format("%.6f", bus.lat) + ",\n" +
                "  \"longitud\": " + String.format("%.6f", bus.lon) + ",\n" +
                "  \"tiempo\": \"" + timestamp + "\"\n" +
                "}";
        File folder = new File("ProyectoDigi/ProgramaAutobuses/src/historial");

        String filename = String.format(folder+"/bus%02d_"+t+".json", bus.busId);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo " + filename + ": " + e.getMessage());
        }
    }
    public void resetearFolder() {
        File folder = new File("ProyectoDigi/ProgramaAutobuses/src/historial");
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete(); // Elimina cada archivo
                }
            }
        } else {
            folder.mkdirs(); // Si no existe, la crea
        }
    }
}
