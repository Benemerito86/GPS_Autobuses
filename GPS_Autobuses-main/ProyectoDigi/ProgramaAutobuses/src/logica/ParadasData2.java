package logica;

public class ParadasData2 {
    private static final String[] nombresParadas = {
            "Instituto Pueblo Paleta (I)",
            "Instituto Pueblo Paleta (V)",
            "Ciudad Carmín",
            "Urb Ciudad Endrino",
            "C Ciudad Porcelana",
            "Pueblo Lavanda Bajo",
            "Metro Pueblo Lavanda Bajo (U)",
            "Ciudad Luminalia (Centro Pokémon)",
            "Pueblo Lavanda Alto",
            "Ciudad Puntaneva (Avenida Helada)",
            "Hospital Pokémon (Frente Centro Pokémon)",
            "Torneo (Entrada de Ciudad Malvalona)",
            "Av. Ciudad Caolín (Ruta Dragón)",
            "Ciudad Caolín (Alcaldía de Iris)"

    };

    public int parada;       // índice de parada
    public double longitud;
    public double latitud;
    public int estado = 1;

    public ParadasData2(int parada, double longitud, double latitud){
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
