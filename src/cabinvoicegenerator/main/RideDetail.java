package cabinvoicegenerator.main;

public class RideDetail {
    private final double distance;
    private final int time;

    public RideDetail(double distance, int time){
        this.distance = distance;
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
