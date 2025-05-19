package logica;

public class ParadasData {

    private static final String[] nombresParadas = {
            "Instituto Mairena Aljarafe (I)",
            "Instituto Mairena Aljarafe (V)",
            "La Prusiana",
            "Urb La Estacada Del Marques",
            "C Zurraque",
            "San Juan Bajo",
            "Metro San Juan Bajo (U)",
            "Eduardo Dato (San Juan de Dios)",
            "San Juan Alto",
            "San Juan de La Cruz (Avenida Juan XXIII)",
            "Hospital San Juan De Dios ( Frente Urgencias )",
            "Torneo (Puerta de San Juan)",
            "Av. San Juan de La Salle (Av. Llanes)",
            "San Juan de La Salle (Alc. M. del Valle)"
    };


    public int parada;       // índice de parada
    public double longitud;
    public double latitud;
    public int estado = 1;

    public ParadasData(int parada, double longitud, double latitud){
        this.parada = parada;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String getNombreParada() {
            if (parada > nombresParadas.length) {
                parada = parada - nombresParadas.length;
            }
            if (parada >= 0 && parada < nombresParadas.length) {
                return nombresParadas[parada];
            } else {
                return "Parada desconocida";
            }



    }
    public String toString(){
        return "Parada: " + getNombreParada() + " - (" + latitud + ", " + longitud + ")";
    }
}
