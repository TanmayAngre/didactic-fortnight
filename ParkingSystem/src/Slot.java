
public class Slot {

	private int floor;
	private int slotType;
	//1 : Bike
	//2 : Car
	//3 : Truck / Bus
	private boolean isAvailable = true;

	Slot(int f, int s){
		floor = f;
		slotType = s;
		//isAvailable = i;
	}
	
	public int getFloor(){
		return floor;
	}
	
	public int getSlotType(){
		return slotType;
	}
	
	public boolean getAvailability(){
		return isAvailable;
	}
	
}
