package cabinvoicegenerator.main;

import java.util.ArrayList;

//Program to calculate cab invoice generator
public class InvoiceGenerator {
    private static final double MINIMUM_COST_PER_KILOMETER_FOR_NORMAL = 10;
    private static final int COST_PER_TIME_FOR_NORMAL = 1;
    private static final double MINIMUM_FARE_FOR_NORMAL = 5;
    private static final double MINIMUM_COST_PER_KILOMETER_FOR_PREMIUM = 15;
    private static final int COST_PER_TIME_FOR_PREMIUM = 2;
    private static final double MINIMUM_FARE_FOR_PREMIUM = 20;

    //UC1 & UC5 - Calculate total fare for normal and premium rides
    public double calculateFare(double distance, int time, String rideType) {
        if (rideType.equalsIgnoreCase("Normal")) {
            double totalFare = distance * MINIMUM_COST_PER_KILOMETER_FOR_NORMAL + time * COST_PER_TIME_FOR_NORMAL;
            return Math.max(totalFare, MINIMUM_FARE_FOR_NORMAL);
        } else if (rideType.equalsIgnoreCase("Premium")) {
            double totalFare = distance * MINIMUM_COST_PER_KILOMETER_FOR_PREMIUM + time * COST_PER_TIME_FOR_PREMIUM;
            return Math.max(totalFare, MINIMUM_FARE_FOR_PREMIUM);
        }
        return 0.0;
    }

    //UC2 - For multiple rides
    public double calculateTotalFare(RideDetail[] rideDetail, String rideType){
        double totalFare = 0.0;
        for (RideDetail ride : rideDetail) {

            //Storing the aggregate total in an array
            totalFare += calculateFare(ride.getDistance(), ride.getTime(), rideType);
        }
        return totalFare;
    }

    //UC3 - Enhanced Invoice
    public InvoiceSummary calculateInvoiceSummary(RideDetail[] rideDetail, String rideType) {
        double totalFare = 0.0;
        for (RideDetail ride : rideDetail) {
            totalFare += calculateFare(ride.getDistance(), ride.getTime(), rideType);
        }
        return new InvoiceSummary(rideDetail.length, totalFare);
    }

    //UC4 - Given UserID, the invoice service gets list of rides
    public InvoiceSummary givenUserIDReturnInvoice(ArrayList<RideDetail> rides, String rideType) {
        double totalFare = 0;

        //method takes the list of rides as input and iterates through each ride in the list
        // For each ride, it calls the calculateFare method to calculate the fare for that ride
        // and adds it to a running total, and it returns invoice summary
        for(RideDetail ride : rides) {
            totalFare = totalFare + this.calculateFare(ride.getDistance(), ride.getTime(), rideType);
        }
        return new InvoiceSummary(rides.size(), totalFare);
    }
}

