package model;

import java.util.HashMap;
import java.util.Map;

public class BikeParking {
	
	int slotNum; 
	int maxSlots;
	Map<Integer, Integer> entryGateCounter; 
	Map<Integer, Integer> exitGateCounter; 
	
	public BikeParking(int length) {
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
	
	public int getMaxSlots() {
		return maxSlots;
	}

	public void setMaxSlots(int maxSlots) {
		this.maxSlots = maxSlots;
	}

	public int getSlotNum() {
		return slotNum;
	}

	public void setSlotNum(int slotNum) {
		this.slotNum = slotNum;
	}


	int[] slots; 
}
