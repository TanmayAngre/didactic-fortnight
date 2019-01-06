
public class Slot {

	private int floor;
	private int slotType;
	private int slotNo;
	//1 : Bike
	//2 : Car
	//3 : Truck / Bus
	private boolean isAvailable = true;

	Slot(int f, int s, int no){
		floor = f;
		slotType = s;
		slotNo = no;
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

	public int getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}
	
}
