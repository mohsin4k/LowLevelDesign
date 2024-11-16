package model;

import java.util.HashMap;
import java.util.Map;

public class BicycleParking {
	
	int maxSlots;
	int slotNum;
	Map<Integer, Integer> entryGateCounter; 
	Map<Integer, Integer> exitGateCounter; 
	
	public BicycleParking(int length) {
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
