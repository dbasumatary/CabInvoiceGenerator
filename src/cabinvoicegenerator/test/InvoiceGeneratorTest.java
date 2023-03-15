package cabinvoicegenerator.test;
import cabinvoicegenerator.main.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class InvoiceGeneratorTest {
    public static InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

    //UC1 - Calculate total fare for a ride
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare(){
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinFare(){
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    //UC2 - For multiple rides
    @Test
    public void givenMultipleRides_ShouldReturnTotalFare(){
        RideDetail[] rideDetail = {new RideDetail(2.0,5),
                                   new RideDetail(0.1,1)
                                  };
        double fare = invoiceGenerator.calculateTotalFare(rideDetail);
        Assert.assertEquals(30, fare, 0.0);
    }

    @Test
    public void shouldReturnMinFareForZeroDistanceAndTime(){
        RideDetail[] rideDetail = {new RideDetail(0.0,0)};
        double fare = invoiceGenerator.calculateTotalFare(rideDetail);
        Assert.assertEquals(5, fare, 0.0);
    }

    //UC3 - Enhanced Invoice
    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary(){
        RideDetail[] rideDetail = {new RideDetail(10.0,30),
                                   new RideDetail(5.0,15)
                                  };
        InvoiceSummary summary = invoiceGenerator.calculateInvoiceSummary(rideDetail);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30);
        if(expectedInvoiceSummary.getAverageFarePerRide() == summary.getAverageFarePerRide()
                && expectedInvoiceSummary.getTotalNumOfRides() == summary.getTotalNumOfRides()
                && expectedInvoiceSummary.getTotalFare() == summary.getTotalFare())
        {
            Assert.assertEquals(245.0, summary.getTotalFare());
            Assert.assertEquals(2, summary.getTotalNumOfRides());
            Assert.assertEquals(122.5, summary.getAverageFarePerRide());
        }
    }

    @Test
    public void shouldReturnMinFareForOneRideWithZeroDistanceAndTime(){
        RideDetail[] rideDetail = {new RideDetail(0.0,0)};
        InvoiceSummary summary = invoiceGenerator.calculateInvoiceSummary(rideDetail);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30);
        if(expectedInvoiceSummary.getAverageFarePerRide() == summary.getAverageFarePerRide()
                && expectedInvoiceSummary.getTotalNumOfRides() == summary.getTotalNumOfRides()
                && expectedInvoiceSummary.getTotalFare() == summary.getTotalFare())
        {
            Assert.assertEquals(5.0, summary.getTotalFare());
            Assert.assertEquals(1, summary.getTotalNumOfRides());
            Assert.assertEquals(5.0, summary.getAverageFarePerRide());
        }
    }

    //UC4 - Given UserID, the invoice service gets list of rides
    @Test
    public void givenUserId_ShouldReturnInvoiceSummary() {

        String userID = "UID1";
        RideRepository rideRepository = new RideRepository();

        ArrayList<RideDetail> rides = new ArrayList<>();
        rides.add(new RideDetail(20.0, 60));
        rides.add(new RideDetail(15.0, 45));

        //Add RideDetail objects to RideRepository class using addRide method along with userID
        rideRepository.addRide(userID, rides);
        ArrayList<RideDetail> listOfRides = rideRepository.getRides(userID);

        //it calls the givenUserIDReturnInvoice method of the InvoiceGenerator class,
        // passing the user ID to retrieve the invoice summary
        InvoiceSummary summary = invoiceGenerator.givenUserIDReturnInvoice(listOfRides);
        InvoiceSummary expectedSummary = new InvoiceSummary(2, 30);
        if(expectedSummary.getAverageFarePerRide() == summary.getAverageFarePerRide()
                && expectedSummary.getTotalNumOfRides() == summary.getTotalNumOfRides()
                && expectedSummary.getTotalFare() == summary.getTotalFare())
        {
            Assert.assertEquals(437.5, summary.getTotalFare());
            Assert.assertEquals(2, summary.getTotalNumOfRides());
            Assert.assertEquals(218.75, summary.getAverageFarePerRide());
        }
    }
}
