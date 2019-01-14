import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ParkingLot {

	class timerTask extends TimerTask{
		Slot s;
		ArrayList<Slot> as;
		timerTask(Slot s, ArrayList<Slot> as){
			this.s = s;
			this.as = as;
		}
		public void run(){
			if(!as.contains(s)){
				getSlotAvailable().add(s.getSlotNo(),s);
				s.setAvailable(true);
				System.out.println("Slot:" + s + " " + s.getSlotType() + "\nAdded");
				System.out.println(s.getAvailability());
			}
			else{
				System.out.println("Already removed");
				System.out.println(s.getAvailability());
			}
		}
	}
	
	private String location;
	private int floors;
	private int totalSlots;
	private ArrayList<Slot> slotAvailable;

	public ParkingLot(String location, int floors, int totalSlots,
			ArrayList<Slot> slotAvailable) {
		super();
		this.location = location;
		this.floors = floors;
		this.totalSlots = totalSlots;
		this.slotAvailable = slotAvailable;
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

	public ArrayList<Slot> getSlotAvailable() {
		return slotAvailable;
	}

	public void setSlotAvailable(ArrayList<Slot> slotAvailable) {
		this.slotAvailable = slotAvailable;
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
	
	public Slot emptySlot(Vehicle v, Properties p){
		//code for isAvailableSlot() for vehicle v...
		System.out.println((v.getType()));
		for(Slot slot:slotAvailable){
				
				if(slot.getSlotType() == Integer.parseInt(p.getProperty(v.getType())) && slot.getAvailability()){
					slotAvailable.remove(slot);
					slot.setAvailable(false);
					return slot;
				}
		}
		return null;
	}
	
	public boolean addSlotAfter(Ticket t, Slot s){
		Timer timer = new Timer();
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = dateFormatter .parse(t.getExpiryDate() + " " + t.getExpiryTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timer.schedule(new timerTask(s,slotAvailable), date);
		return true;
	}
	
	
}
