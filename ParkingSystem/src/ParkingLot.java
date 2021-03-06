import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ParkingLot {

	private String location;
	private int floors;
	private int totalSlots;
	private ArrayList<Slot> slotAvailableList;
	private ArrayList<Slot> slotFilledList;

	public ParkingLot(String location, int floors, int totalSlots,
			ArrayList<Slot> slotAvailable, ArrayList<Slot> slotFilled) {
		super();
		this.location = location;
		this.floors = floors;
		this.totalSlots = totalSlots;
		this.slotAvailableList = slotAvailable;
		this.slotFilledList = slotFilled;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	public int getTotalSlots() {
		return totalSlots;
	}

	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}

	public ArrayList<Slot> getSlotAvailableList() {
		return slotAvailableList;
	}

	public void setSlotAvailableList(ArrayList<Slot> slotAvailable) {
		this.slotAvailableList = slotAvailable;
	}
	
	public ArrayList<Slot> getSlotFilledList() {
		return slotFilledList;
	}

	public void setSlotFilledList(ArrayList<Slot> slotFilled) {
		this.slotFilledList = slotFilled;
	}

	/*ParkingLot(String l, int f, int t){
		location = l;
		floors = f;
		totalSlots = t;
		slotAvailable = new ArrayList<Slot>(totalSlots);
		slotAvailable.add(new Slot(1, 1));
		slotAvailable.add(new Slot(1, 2));
		slotAvailable.add(new Slot(1, 3));
	}*/
	
}

