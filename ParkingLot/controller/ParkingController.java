package controller;

import java.util.Map;

import model.ParkingLot;
import services.ParkingService;

public class ParkingController {
	
	private Map<String, ParkingLot> parkingLots;
	private ParkingService service; 

	public ParkingController(Map<String, ParkingLot> parkingLots) {
		this.parkingLots = parkingLots;
		service = new ParkingService(); 
	}


	public boolean park_vehicle(String parkingLot, String vehicleType, String entryGate) {
		// TODO Auto-generated method stub
		return false;
	}

	public void unpark_vehicle(String parkingLot, String vehicleType, String entryGate) {
		// TODO Auto-generated method stub
		
	}

	public boolean is_available(String parkingLot, String vehicleType) {
		// TODO Auto-generated method stub
		return false;
	}

	public void add_parking_lot(String data) {
			
		ParkingLot newParkingLot = new ParkingLot(); 
		boolean result = service.addParkingLot(data, newParkingLot, parkingLots);
		
		if(!result) {
			System.out.println("Parking-Lot already present");
		}
	} 

}
