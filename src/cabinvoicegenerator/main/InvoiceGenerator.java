package cabinvoicegenerator.main;

//Program to calculate cab invoice generator
public class InvoiceGenerator {
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5;

    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;

        //Return either totalFare or minimum fare whoever is greater
        return Math.max(totalFare, MINIMUM_FARE);
    }
}
