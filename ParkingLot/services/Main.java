package services;
import java.util.*;

import controller.ParkingController;

public class Main {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Parking Lot Management System");
		
		System.out.println("You can give your input As per instruction to exit enter 00");
		
		ParkingController controller = new ParkingController(); 
		
		while(true) {
			String input = sc.nextLine(); 
			if(input.equals("00")) {
				System.out.println("Exiting.....");
			    break; 
			}
			int openParenIndex = input.indexOf('(');
		        
		    String functionName = input.substring(0, openParenIndex);
		        
		    String data = input.substring(openParenIndex);
		    
		   if(functionName.equals("add_parking_lot")) {
		    	controller.add_parking_lot(data);
		    }else if(functionName.equals("is_available")) {
		    	controller.is_available(data);
		    }else if(functionName.equals("park_vehicle")) {
		    	controller.park_vehicle(data);
		    }else if(functionName.equals("print_all_available_slots")) {
		    	controller.allAvailableSlots(data);
		    }else if(functionName.equals("print_total_in")) {
		    	controller.allVehicleIn(data);
		    }else if(functionName.equals("print_total_out") ){
		    	controller.allVehicleOut(data);
		    }
		}

	}

}
