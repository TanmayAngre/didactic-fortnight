import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Ticket {

	private long ticketNo = new Date().getTime();
	private Slot slot;
	private int totalParkTime;
	private LocalDate entryDate;
	private LocalTime entryTime;
	private LocalDate expiryDate;
	private LocalTime expiryTime;
	//private double serviceCost;
	
	Ticket(int t, Slot s){
		totalParkTime = t;
		slot = s;
	}
	
	public long getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(long ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public int getTotalParkTime() {
		return totalParkTime;
	}

	public void setTotalParkTime(int totalParkTime) {
		this.totalParkTime = totalParkTime;
	}

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	public LocalTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalTime entryTime) {
		this.entryTime = entryTime;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public LocalTime getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(LocalTime expiryTime) {
		this.expiryTime = expiryTime;
	}

	public boolean generateTicket(){
		setEntryDate(LocalDate.now());
		setEntryTime(LocalTime.now());
		int entryH = getEntryTime().getHour();
		//System.out.println("Enter the service time (in hours):");
		//totalParkTime = getparkTime();
		int serviceDays = getTotalParkTime()/24;
		int plusServiceTime = getTotalParkTime()%24;
		setExpiryDate(getEntryDate().plusDays(serviceDays));
		setExpiryTime(getEntryTime().plusMinutes(plusServiceTime));
		int expiryH = getExpiryTime().getHour();
		if(expiryH < entryH)
			setExpiryDate(getExpiryDate().plusDays(1));
		return true;
	}
	
	public double generateCost(){
		long days = ChronoUnit.DAYS.between(getEntryDate(), getExpiryDate());
		long hours = ChronoUnit.HOURS.between(getEntryTime(), getExpiryTime());
		if(hours < 0){
			days -= 1;
			hours += 24;
		}
		//System.out.println(days + "   " + hours);
		int typeSlot = getSlot().getSlotType();
		double slotTypeCost = 50 * typeSlot;
		//System.out.println(slotTypeCost);
		//double cost = 0;
		//if(hours < 0  && days >= 1)
			//cost = (days - 1) * slotTypeCost;
		hours = (days * 24) + hours;
		//System.out.println(hours);
		slotTypeCost = slotTypeCost * hours / 24.0;
		//System.out.println(slotTypeCost);
		return slotTypeCost;   
	}
	
	public void displayTicket(double cost){
		System.out.println("\n");
		System.out.println("---------------------- Ticket Generation ------------------------\n");
		System.out.println("Ticket Serial No. : " + getTicketNo());
		System.out.println("Entry Time : " + getEntryDate() + "  " + getEntryTime());
		System.out.println("Expiry Time : " + getExpiryDate() + "  " + getExpiryTime());
		System.out.println("Total Cost : " + cost);
	}
	
	/*public Ticket returnRef(long ticNo){
		return this;
	}*/

}
