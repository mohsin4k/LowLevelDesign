package controller;

import java.util.*;

import model.ParkingLot;
import services.ParkingService;

public class ParkingController {
	
	private Map<String, ParkingLot> allParkingLots;
	private ParkingService service; 

	public ParkingController() {
		this.allParkingLots = new HashMap<>();
		service = new ParkingService(); 
	}


	public void park_vehicle(String data) {
		
		boolean result = service.parkVehicle(data, allParkingLots);
		
		System.out.println(result);
	}

	public void unpark_vehicle(String data) {
		
		boolean result = service.unParkVehicle(data, allParkingLots);
		
	}

	public void is_available(String data) {
		
		boolean result=  service.isSlotsAvailable(data, allParkingLots);
		
		System.out.println(result);
	}

	public void add_parking_lot(String data) {
			
		boolean result = service.addParkingLot(data, allParkingLots);
		
		if(!result) {
			System.out.println("Parking-Lot already present");
		}
	} 
	
	public void allAvailableSlots(String data) {
		
		int cars = service.carAvailableSlots(data, allParkingLots);
		int bikes = service.bikeAvailableSlots(data, allParkingLots);
		int cycles = service.bicycleAvailableSlots(data, allParkingLots);
		
		System.out.println("Total Available: "+ (cars+bikes+cycles));
		System.out.println("Cars: "+ cars + " "+ "Bikes: "+ bikes+ " "+ "Bicycles: "+ cycles);
	}

	public void allVehicleIn(String data) {
		int cars = service.carInGate(data, allParkingLots);
		int bikes = service.bikeInGate(data, allParkingLots); 
		int cycles = service.bicycleInGate(data, allParkingLots); 
		
		System.out.println("Cars: "+ cars + " "+ "Bikes: "+ bikes+ " "+ "Bicycles: "+ cycles);
	}
	
	public void allVehicleOut(String data) {
		int cars = service.carOutGate(data, allParkingLots);
		int bikes = service.bikeOutGate(data, allParkingLots); 
		int cycles = service.bicycleOutGate(data, allParkingLots); 
		
		System.out.println("Cars: "+ cars + " "+ "Bikes: "+ bikes+ " "+ "Bicycles: "+ cycles);
	}
}
