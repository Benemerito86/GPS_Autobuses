package logica;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GPSData {
    public int busId;
    public int timestamp;
    public double lat;
    public double lon;
    public int speed;
    public int velocidadTotal= 0;
    public int velocidadMedia = 0;
    public int paradaActual = 0;

    public GPSData(int busId, int timestamp, double lat, double lon, int speed) {
        this.busId = busId;
        this.timestamp = timestamp;
        this.lat = lat;
        this.lon = lon;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Bus " + busId + " | t=" + timestamp +
                " | Pos=(" + String.format("%.4f", lat) + ", " + String.format("%.4f", lon) + ")" +
                " | Vel=" + speed + " km/h";
    }

    private void exportarEstadoBus(GPSData bus) {
        // Formatea la hora actual en formato ISO 8601
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String timestamp = LocalDateTime.now().format(formatter);

        // Crea manualmente el contenido JSON
        String json = "{\n" +
                "  \"busId\": \"" + String.format("BUS%02d", bus.busId) + "\",\n" +
                "  \"VelocidadMedia\": " + bus.velocidadMedia + ",\n" +
                "  \"latitude\": " + bus.lat + ",\n" +
                "  \"longitude\": " + bus.lon + ",\n" +
                "  \"timestamp\": \"" + timestamp + "\"\n" +
                "}";

        // Nombre del archivo
        String filename = String.format("bus%02d_status.json", bus.busId);

        // Escribe el JSON al archivo
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo " + filename + ": " + e.getMessage());
        }


    }
}
