package services;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.*;

public class ParkingService {

	public ParkingService() {
		
	}
	
	
	public boolean addParkingLot(String data, Map<String, ParkingLot> allParkingLots) {
		
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
        
        
        if(!allParkingLots.containsKey(name)) {
        	
        	CarParking carP = new CarParking(cars); 
        	BikeParking bikeP = new BikeParking(bikes);
        	BicycleParking cycleP = new BicycleParking(bicycles); 
        	
        	ParkingLot newParkinLot = new ParkingLot(name, carP, bikeP, cycleP, entryGates, exitGates);
        	
        	allParkingLots.put(name, newParkinLot);
        	
        	return true; 
        }else {
        	return false; 
        }
	        
	}
	
	
	public boolean parkVehicle(String data, Map<String, ParkingLot> allParkingLots) {
		
		 String cleanedInput = data.replace("(", "").replace(")", "").trim();
	     String[] parts = cleanedInput.split(", ");

	     // Extracting values
	     String name = parts[0].trim(); 
	     String vehicle = parts[1].trim(); 
	     String entryGateString = parts[2].trim(); 

	     int entryGate = Integer.parseInt(entryGateString.replaceAll("[^0-9]", "").trim());
	        
	     
	     ParkingLot parkingLot = allParkingLots.get(name); 
	     
	     if(vehicle.equals("Car")) {
	        	 if(parkingLot.getCars().getSlotNum() != parkingLot.getCars().getMaxSlots()) {
	        		 parkingLot.getCars().setSlotNum(parkingLot.getCars().getSlotNum() +1);
	        		 parkingLot.getCars().getEntryGateCounter().put(entryGate,  parkingLot.getCars().getEntryGateCounter().getOrDefault(entryGate, 0)+1);            
	        		 return true; 
	        	 }else {
	        		 return false; 
	        	 }
	        }else if(vehicle.equals("Bike")) {
	        	if( parkingLot.getBikes().getSlotNum() != parkingLot.getBikes().getMaxSlots()) {
	        		parkingLot.getBikes().setSlotNum(parkingLot.getBikes().getSlotNum() +1);
	        		parkingLot.getBikes().getEntryGateCounter().put(entryGate,  parkingLot.getBikes().getEntryGateCounter().getOrDefault(entryGate, 0)+1);    
	        		return true; 
	        	}else {
	        		return false; 
	        	}
	        }else {
	        	 if(parkingLot.getBicycles().getSlotNum() != parkingLot.getBicycles().getMaxSlots()) {
	        		 parkingLot.getBicycles().setSlotNum(parkingLot.getBicycles().getSlotNum() +1);
	        		 parkingLot.getBicycles().getEntryGateCounter().put(entryGate,  parkingLot.getBicycles().getEntryGateCounter().getOrDefault(entryGate, 0)+1);    
	        		 return true; 
	        	 }else {
	        		 return false; 
	        	 }
	        }
	     
	}
	
	
	public boolean isSlotsAvailable(String data, Map<String, ParkingLot> allParkingLots) {

		
        String cleanedInput = data.replace("(", "").replace(")", "").trim();
        String[] parts = cleanedInput.split(", ");

        // Extracting values
        String name = parts[0].trim(); // Name
        String vehicle = parts[1].trim(); // Vehicle
        
        ParkingLot parkingLot = allParkingLots.get(name); 
        
        if(vehicle.equals("Car")) {
        	return parkingLot.getCars().getSlotNum() != parkingLot.getCars().getMaxSlots();
        }else if(vehicle.equals("Bike")) {
        	boolean x = parkingLot.getBikes().getSlotNum() != parkingLot.getBikes().getMaxSlots();
        	return x; 
        }else {
        	return parkingLot.getBicycles().getSlotNum() != parkingLot.getBicycles().getMaxSlots();
        }
	}

	
	public boolean unParkVehicle(String data, Map<String, ParkingLot> allParkingLots) {
		
		 // Separate function name from parameters
        String params = data.split("\\(")[1].replace(")", "").trim(); // Get everything inside the parentheses

        String[] parts = params.split(", ");

        String name = parts[0].trim(); // Name
        String vehicle = parts[1].trim(); // Vehicle
        String exitGateString = parts[2].trim(); // Exit gate string

        int exitGate = Integer.parseInt(exitGateString.replaceAll("[^0-9]", "").trim());
		
        ParkingLot parkingLot = allParkingLots.get(name); 
	     
	     if(vehicle.equals("Car")) {
	        	 if(parkingLot.getCars().getSlotNum() != 0) {
	        		 parkingLot.getCars().setSlotNum(parkingLot.getCars().getSlotNum() -1);
	        		 parkingLot.getCars().getEntryGateCounter().put(exitGate,  parkingLot.getCars().getExitGateCounter().getOrDefault(exitGate, 0)+1); 
	        		 return true; 
	        	 }else {
	        		 return false; 
	        	 }
	        }else if(vehicle.equals("Bike")) {
	        	if( parkingLot.getBikes().getSlotNum() != 0) {
	        		parkingLot.getBikes().setSlotNum(parkingLot.getBikes().getSlotNum() -1);
	        		 parkingLot.getBikes().getEntryGateCounter().put(exitGate,  parkingLot.getBikes().getExitGateCounter().getOrDefault(exitGate, 0)+1); 
	        		return true; 
	        	}else {
	        		return false; 
	        	}
	        }else {
	        	 if(parkingLot.getBicycles().getSlotNum() != 0) {
	        		 parkingLot.getBicycles().setSlotNum(parkingLot.getBicycles().getSlotNum() -1);
	        		 parkingLot.getBicycles().getEntryGateCounter().put(exitGate,  parkingLot.getBicycles().getExitGateCounter().getOrDefault(exitGate, 0)+1); 
	        		 return true; 
	        	 }else {
	        		 return false; 
	        	 }
	        }
        
        
	}


	public int carAvailableSlots(String data, Map<String, ParkingLot> allParkingLots) {
		
		 String params = data.split("\\(")[1].replace(")", "").trim(); 

	     String name = params.trim();
	     
		 ParkingLot parkingLot = allParkingLots.get(name); 
		 
		 int carSlot = (parkingLot.getCars().getMaxSlots()-parkingLot.getCars().getSlotNum());
		 
		 return carSlot; 
	}
	
	public int bikeAvailableSlots(String data, Map<String, ParkingLot> allParkingLots) {
		
		 String params = data.split("\\(")[1].replace(")", "").trim(); 

	     String name = params.trim();
	     
		 ParkingLot parkingLot = allParkingLots.get(name); 
		 
		 int bikeSlot = (parkingLot.getBikes().getMaxSlots()-parkingLot.getBikes().getSlotNum());
		 
		 return bikeSlot; 
	}
	
	public int bicycleAvailableSlots(String data, Map<String, ParkingLot> allParkingLots) {
		
		 String params = data.split("\\(")[1].replace(")", "").trim(); 

	     String name = params.trim();
	     
		 ParkingLot parkingLot = allParkingLots.get(name); 
		 
		 int bicycleSlot = (parkingLot.getBicycles().getMaxSlots()-parkingLot.getBicycles().getSlotNum());
		 
		 return bicycleSlot; 
	}

	
	public int carOutGate(String data, Map<String, ParkingLot> allParkingLots) {
		
		String params = data.split("\\(")[1].replace(")", "").trim(); // Get everything inside the parentheses

        // Split parameters by commas
        String[] parts = params.split(", ");

        // Extracting values
        String name = parts[0].trim(); // Name
        String exitGateString = parts[1].trim(); // Exit gate string

        // Extracting the number from the exit gate string
        int exitGate = Integer.parseInt(exitGateString.replaceAll("[^0-9]", "").trim());
        
        ParkingLot parkingLot = allParkingLots.get(name); 
        
        return parkingLot.getCars().getExitGateCounter().getOrDefault(exitGate, 0);
	}
	

	public int carInGate(String data, Map<String, ParkingLot> allParkingLots) {
		String params = data.split("\\(")[1].replace(")", "").trim(); // Get everything inside the parentheses

        // Split parameters by commas
        String[] parts = params.split(", ");

        // Extracting values
        String name = parts[0].trim(); // Name
        String entryGateString = parts[1].trim(); // Entry gate string

        // Extracting the number from the entry gate string
        int entryGate = Integer.parseInt(entryGateString.replaceAll("[^0-9]", "").trim());
        
        ParkingLot parkingLot = allParkingLots.get(name); 
        
        return parkingLot.getCars().getEntryGateCounter().getOrDefault(entryGate, 0);
	}
	
	public int bikeOutGate(String data, Map<String, ParkingLot> allParkingLots) {
		String params = data.split("\\(")[1].replace(")", "").trim(); // Get everything inside the parentheses

        // Split parameters by commas
        String[] parts = params.split(", ");

        // Extracting values
        String name = parts[0].trim(); // Name
        String exitGateString = parts[1].trim(); // Exit gate string

        // Extracting the number from the exit gate string
        int exitGate = Integer.parseInt(exitGateString.replaceAll("[^0-9]", "").trim());
        
        ParkingLot parkingLot = allParkingLots.get(name); 
        
        return parkingLot.getBikes().getExitGateCounter().getOrDefault(exitGate, 0);
	}
	

	public int bikeInGate(String data, Map<String, ParkingLot> allParkingLots) {
		String params = data.split("\\(")[1].replace(")", "").trim(); // Get everything inside the parentheses

        // Split parameters by commas
        String[] parts = params.split(", ");

        // Extracting values
        String name = parts[0].trim(); // Name
        String entryGateString = parts[1].trim(); // Entry gate string

        // Extracting the number from the entry gate string
        int entryGate = Integer.parseInt(entryGateString.replaceAll("[^0-9]", "").trim());
        
        ParkingLot parkingLot = allParkingLots.get(name); 
        
        return parkingLot.getBikes().getEntryGateCounter().getOrDefault(entryGate, 0);
	}
	
	public int bicycleOutGate(String data, Map<String, ParkingLot> allParkingLots) {
		String params = data.split("\\(")[1].replace(")", "").trim(); // Get everything inside the parentheses

        // Split parameters by commas
        String[] parts = params.split(", ");

        // Extracting values
        String name = parts[0].trim(); // Name
        String exitGateString = parts[1].trim(); // Exit gate string

        // Extracting the number from the exit gate string
        int exitGate = Integer.parseInt(exitGateString.replaceAll("[^0-9]", "").trim());
        
        ParkingLot parkingLot = allParkingLots.get(name); 
        
        return parkingLot.getBicycles().getExitGateCounter().getOrDefault(exitGate, 0);
	}
	

	public int bicycleInGate(String data, Map<String, ParkingLot> allParkingLots) {
		String params = data.split("\\(")[1].replace(")", "").trim(); // Get everything inside the parentheses

        // Split parameters by commas
        String[] parts = params.split(", ");

        // Extracting values
        String name = parts[0].trim(); // Name
        String entryGateString = parts[1].trim(); // Entry gate string

        // Extracting the number from the entry gate string
        int entryGate = Integer.parseInt(entryGateString.replaceAll("[^0-9]", "").trim());
        
        ParkingLot parkingLot = allParkingLots.get(name); 
        
        return parkingLot.getBicycles().getEntryGateCounter().getOrDefault(entryGate, 0);
	}
	
	

}
