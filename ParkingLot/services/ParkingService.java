package services;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.ParkingLot;

public class ParkingService {

	public ParkingService() {
		
	}
	
	
	public boolean addParkingLot(String data, ParkingLot parkingLot, Map<String, ParkingLot> parkingLots) {
		
		 // Patterns for extraction
        Pattern parkingPattern = Pattern.compile("\\[(\\d+) Car Parkings?, (\\d+) Bike Parking, (\\d+) Bicycle Parking\\]");
        Pattern entryGatesPattern = Pattern.compile("(\\d+) Entry Gates,");
        Pattern exitGatesPattern = Pattern.compile("(\\d+) Exit Gates\\)");

        // Extracting parking data
        Matcher parkingMatcher = parkingPattern.matcher(data);
        int cars = 0, bikes = 0, bicycles = 0;
        if (parkingMatcher.find()) {
            cars = Integer.parseInt(parkingMatcher.group(1));
            bikes = Integer.parseInt(parkingMatcher.group(2));
            bicycles = Integer.parseInt(parkingMatcher.group(3));
        }

        // Extracting entry gates
        Matcher entryGatesMatcher = entryGatesPattern.matcher(data);
        int entryGates = 0;
        if (entryGatesMatcher.find()) {
            entryGates = Integer.parseInt(entryGatesMatcher.group(1));
        }

        // Extracting exit gates
        Matcher exitGatesMatcher = exitGatesPattern.matcher(data);
        int exitGates = 0;
        if (exitGatesMatcher.find()) {
            exitGates = Integer.parseInt(exitGatesMatcher.group(1));
        }

        // Extracting the name
        Pattern namePattern = Pattern.compile("\\(([^,]+),");
        Matcher nameMatcher = namePattern.matcher(data);
        String name = "";
        if (nameMatcher.find()) {
            name = nameMatcher.group(1).trim();
        }
        
        
        if(!parkingLots.containsKey(name)) {
        	
        	return true; 
        }else {
        	return false; 
        }
	        
	}
	
}
