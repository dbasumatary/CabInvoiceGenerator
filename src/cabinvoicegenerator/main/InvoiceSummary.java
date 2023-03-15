package cabinvoicegenerator.main;

public class InvoiceSummary {

    private int totalNumOfRides;
    private double totalFare;
    private double averageFarePerRide;

    public InvoiceSummary(int totalNumOfRides, double totalFare) {
        this.totalNumOfRides = totalNumOfRides;
        this.totalFare = totalFare;
        this.averageFarePerRide = this.totalFare/this.totalNumOfRides;
    }

    public int getTotalNumOfRides() {
        return totalNumOfRides;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public double getAverageFarePerRide() {
        return averageFarePerRide;
    }
}
