package vista;

public class GPSData {
    public int busId;
    public int timestamp;
    public double lat;
    public double lon;
    public int speed;

    public GPSData(int busId, int timestamp, double lat, double lon, int speed) {
        this.busId = busId;
        this.timestamp = timestamp;
        this.lat = lat;
        this.lon = lon;
        this.speed = speed;
    }
}
