package model;

public class ParkingLot{

	private CarParking cars; 
	private BikeParking bikes; 
	private BicycleParking bicycles; 
	
	private int entryGates;
	private int exitGates; 
	
	private String name; 
	
	public CarParking getCars() {
		return cars;
	}


	public void setCars(CarParking cars) {
		this.cars = cars;
	}


	public BikeParking getBikes() {
		return bikes;
	}


	public void setBikes(BikeParking bikes) {
		this.bikes = bikes;
	}


	public BicycleParking getBicycles() {
		return bicycles;
	}


	public ParkingLot() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setBicycles(BicycleParking bicycles) {
		this.bicycles = bicycles;
	}


	public int getEntryGates() {
		return entryGates;
	}


	public void setEntryGates(int entryGates) {
		this.entryGates = entryGates;
	}


	public int getExitGates() {
		return exitGates;
	}


	public void setExitGates(int exitGates) {
		this.exitGates = exitGates;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

	public ParkingLot(String name, CarParking cars, BikeParking bikes, BicycleParking bicycles, int entryGates, int exitGates) {
		this.name = name; 
		this.cars = cars; 
		this.bikes = bikes; 
		this.bicycles = bicycles; 
		this.entryGates = entryGates; 
		this.exitGates = exitGates; 
	}


}
