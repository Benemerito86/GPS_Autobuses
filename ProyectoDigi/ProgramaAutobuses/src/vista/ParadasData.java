package vista;

public class ParadasData {

    public String parada;
    public int longitud;
    public int latitud;

    ParadasData(String parada, int longitud, int latitud){
        this.parada = parada;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String toString(){
        return "Parada " + parada + " @ (" + latitud + ", " + longitud + ")";
    }
}
