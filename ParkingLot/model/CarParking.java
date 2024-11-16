package model;
import java.util.*;

public class CarParking {
	int maxSlots;
	int slotNum; 
	
	Map<Integer, Integer> entryGateCounter; 

	Map<Integer, Integer> exitGateCounter; 

	public CarParking(int length) {
		maxSlots = length;
		slotNum = 0; 
		entryGateCounter = new HashMap<>(); 
		exitGateCounter = new HashMap<>(); 

	}
	
	public Map<Integer, Integer> getEntryGateCounter() {
		return entryGateCounter;
	}

	public Map<Integer, Integer> getExitGateCounter() {
		return exitGateCounter;
	}

	
	public int getSlotNum() {
		return slotNum;
	}

	public void setSlotNum(int slotNum) {
		this.slotNum = slotNum;
	}

	public int getMaxSlots() {
		return maxSlots;
	}

	public void setMaxSlots(int maxSlots) {
		this.maxSlots = maxSlots;
	}


	
}
