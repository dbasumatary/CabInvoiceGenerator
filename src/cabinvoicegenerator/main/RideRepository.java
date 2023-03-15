package cabinvoicegenerator.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//This will store the ride information of a user based on id
public class RideRepository {

    Map<String, ArrayList<RideDetail>> map;

    public RideRepository() {
        this.map = new HashMap<>();
    }

    //This method takes a user ID and a list of RideDetail objects representing
    // the rides taken by the user, and adds them to the Map object
    public void addRide(String id, ArrayList<RideDetail> rides) {
        if(id != null)
            map.put(id, rides);
    }

    //This method takes a user ID and returns the list of RideDetail
    // objects associated with that user ID in the Map
    public ArrayList<RideDetail> getRides(String id){
        if(map.containsKey(id)) {
            return map.get(id);
        }
        return null;
    }
}

