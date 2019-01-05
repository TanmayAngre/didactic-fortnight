import java.util.*;
import java.io.*;

public class Main {
	
	static ParkingLot an, da, gkp;
	
	static{
		ArrayList<Slot> slotA = new ArrayList<>();
		for(int i = 0; i<200; i++){
			if(i<50)
				slotA.add(new Slot(1,1));
			else if(i<100)
				slotA.add(new Slot(1,2));
			else if(i<150)
				slotA.add(new Slot(2,3));
			else
				slotA.add(new Slot(3,2));
		}
		an = new ParkingLot("Andheri", 3, 200, slotA);
		da = new ParkingLot("Dadar", 3, 200, slotA);
		gkp = new ParkingLot("Ghatkoper", 3, 200, slotA);
		System.out.println("static block initiated");
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		Properties p = new Properties();
		InputStream input = null;
		input = Main.class.getResourceAsStream("slotconfig.properties");
		try{
			p.load(input);
		}
		catch(IOException e){
			System.out.println("Exception caught" + e);
			e.printStackTrace();
		}
		finally{
			if(input!=null){
				try{
					input.close();
				}
				catch(IOException e){
					System.out.println("Exception caught while closing stream");
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("\nEnter the details of your vehicle... ");
		System.out.println("\nEnter the model name : ");
		String m = sc.next();
		System.out.println("\nEnter the type of vehicle : (twowheeler/minifour/maxfour)");
		String t = sc.next();
		System.out.println("\nEnter the location of your desired parking lot : ");
		String loc = sc.next();
		
		//check conditions for invalid inputs...
		
		int w = 0;
		if(t == "twowheeler")
			w = 2;
		if(t == "minifour" || t == "maxfour")
			w = 4;
		Vehicle v = new TwoWheeler( m, loc, t, w);
		//Have to implement 2 more classes of vehicle...
		ParkingLot pl;
		if(loc == gkp.getLocation())
			pl = gkp;
		else if(loc == da.getLocation())
			pl = da;
		else// if(loc == an.getLocation())
			pl = an;
		Slot slot = pl.emptySlot(v, p);
		//if slots full, can check for other lot for empty slots
		if(slot == null){
			System.out.println("\nParking Slot not available!!!");
			sc.close();
			return;
		}
		System.out.println("Enter the service time (in hours) : ");
		int serviceTime = sc.nextInt();
		Ticket tic = new Ticket(serviceTime, slot);
		tic.generateTicket();
		//generating a task to free slot after service time...
		pl.addSlotAfter(tic, slot);
		//double total = tic.generateCost();
		v.display(p);
		tic.displayTicket();
		sc.close();
	}

}
