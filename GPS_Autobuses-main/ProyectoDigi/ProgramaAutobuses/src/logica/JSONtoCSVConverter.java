package logica;

import org.json.JSONObject;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class JSONtoCSVConverter {

    private static final int LIMITE_ARCHIVOS = 7;
    private static final String PREFIJO = "historial_";
    private static final String SUFIJO = ".csv";

    public static void convertirCarpetaJsonACsvConRotacion(String carpetaJson, String carpetaSalida) {
        File carpeta = new File(carpetaJson);
        File[] archivosJson = carpeta.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));

        if (archivosJson == null || archivosJson.length == 0) {
            System.out.println("No se encontraron archivos JSON en la carpeta: " + carpetaJson);
            return;
        }

        // Crear carpeta de salida si no existe
        File carpetaOut = new File(carpetaSalida);
        if (!carpetaOut.exists()) {
            carpetaOut.mkdirs();
        }

        // Obtener el próximo número de CSV
        int siguienteNumero = obtenerSiguienteNumeroCSV(carpetaOut);

        String nombreArchivo = String.format("%s%02d%s", PREFIJO, siguienteNumero, SUFIJO);
        File archivoCsv = new File(carpetaOut, nombreArchivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCsv))) {
            writer.write("busId,velocidadMedia,latitud,longitud,tiempo\n");

            for (File archivoJson : archivosJson) {
                try (BufferedReader reader = new BufferedReader(new FileReader(archivoJson))) {
                    StringBuilder sb = new StringBuilder();
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        sb.append(linea);
                    }

                    JSONObject json = new JSONObject(sb.toString());

                    String busId = json.optString("busId", "DESCONOCIDO");
                    double velocidadMedia = json.optDouble("velocidadMedia", 0.0);
                    double latitud = json.optDouble("latitud", 0.0);
                    double longitud = json.optDouble("longitud", 0.0);
                    String tiempo = json.optString("tiempo", "");

                    String fila = String.format(Locale.US, "%s,%.2f,%.6f,%.6f,%s\n",
                            busId, velocidadMedia, latitud, longitud, tiempo);
                    writer.write(fila);

                } catch (Exception e) {
                    System.err.println("❌ Error procesando archivo " + archivoJson.getName() + ": " + e.getMessage());
                }
            }

            System.out.println("✅ CSV generado: " + archivoCsv.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("❌ Error al escribir CSV: " + e.getMessage());
        }
    }

    private static int obtenerSiguienteNumeroCSV(File carpetaSalida) {
        File[] archivos = carpetaSalida.listFiles((dir, name) -> name.matches(PREFIJO + "\\d{2}" + SUFIJO));
        if (archivos == null) return 1;

        Arrays.sort(archivos, Comparator.comparing(File::getName));

        if (archivos.length < LIMITE_ARCHIVOS) {
            return archivos.length + 1;
        } else {
            // Eliminar el más antiguo (primer en orden alfabético)
            File archivoMasAntiguo = archivos[0];
            if (archivoMasAntiguo.delete()) {
                System.out.println("🗑️ Eliminado: " + archivoMasAntiguo.getName());
            }
            // Renombrar los archivos restantes para mantener la secuencia
            for (int i = 1; i < archivos.length; i++) {
                File viejo = archivos[i];
                File nuevo = new File(carpetaSalida, String.format("%s%02d%s", PREFIJO, i, SUFIJO));
                viejo.renameTo(nuevo);
            }
            return LIMITE_ARCHIVOS;
        }
    }

    public static void main(String[] args) {
        String carpetaJson = "ProyectoDigi/ProgramaAutobuses/src/historial";
        String carpetaSalida = "ProyectoDigi/ProgramaAutobuses/historial_csv";

        convertirCarpetaJsonACsvConRotacion(carpetaJson, carpetaSalida);
    }
}
