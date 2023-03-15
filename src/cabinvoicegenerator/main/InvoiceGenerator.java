package cabinvoicegenerator.main;

import java.util.ArrayList;

//Program to calculate cab invoice generator
public class InvoiceGenerator {
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5;

    //UC1 - Calculate total fare for a ride
    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;

        //Return either totalFare or minimum fare whoever is greater
        return Math.max(totalFare, MINIMUM_FARE);
    }

    //UC2 - For multiple rides
    public double calculateTotalFare(RideDetail[] rideDetail){
        double totalFare = 0.0;
        for (RideDetail ride : rideDetail) {

            //Storing the aggregate total in an array
            totalFare += calculateFare(ride.getDistance(), ride.getTime());
        }
        return totalFare;
    }

    //UC3 - Enhanced Invoice
    public InvoiceSummary calculateInvoiceSummary(RideDetail[] rideDetail) {
        double totalFare = 0.0;
        for (RideDetail ride : rideDetail) {
            totalFare += calculateFare(ride.getDistance(), ride.getTime());
        }
        return new InvoiceSummary(rideDetail.length, totalFare);
    }

    //UC4 - Given UserID, the invoice service gets list of rides
    public InvoiceSummary givenUserIDReturnInvoice(ArrayList<RideDetail> rides) {
        double totalFare = 0;

        //method takes the list of rides as input and iterates through each ride in the list
        // For each ride, it calls the calculateFare method to calculate the fare for that ride
        // and adds it to a running total, and it returns invoice summary
        for(RideDetail ride : rides) {
            totalFare = totalFare + this.calculateFare(ride.getDistance(), ride.getTime());
        }
        return new InvoiceSummary(rides.size(), totalFare);
    }
}

