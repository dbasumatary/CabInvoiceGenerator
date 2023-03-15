package cabinvoicegenerator.test;
import cabinvoicegenerator.main.InvoiceGenerator;
import cabinvoicegenerator.main.RideDetail;
import org.junit.Assert;
import org.junit.Test;

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
}
