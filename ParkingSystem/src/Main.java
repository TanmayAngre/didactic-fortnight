import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.Time;
import java.util.*;
import java.io.*;

public class Main {
	
	static ParkingLot an, da, gkp;
	
	static{
		ArrayList<Slot> slotA = new ArrayList<>();
		ArrayList<Slot> slotB = new ArrayList<>();
		ArrayList<Slot> slotC = new ArrayList<>();
		for(int i = 0; i<200; i++){
			if(i<50){
				slotA.add(new Slot(1,1,i));
				slotB.add(new Slot(1,1,i));
				slotC.add(new Slot(1,1,i));
			}
			else if(i<100){
				slotA.add(new Slot(1,2,i));
				slotB.add(new Slot(1,2,i));
				slotC.add(new Slot(1,2,i));
			}
			else if(i<150){
				slotA.add(new Slot(2,3,i));
				slotB.add(new Slot(2,3,i));
				slotC.add(new Slot(2,3,i));
			}
			else{
				slotA.add(new Slot(3,2,i));
				slotB.add(new Slot(3,2,i));
				slotC.add(new Slot(3,2,i));
			}
		}
		an = new ParkingLot("Andheri", 3, 200, slotA);
		da = new ParkingLot("Dadar", 3, 200, slotB);
		gkp = new ParkingLot("Ghatkoper", 3, 200, slotC);
		System.out.println("static block initiated");
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		Properties p = new Properties();
		InputStream input = null;
		input = Main.class.getClassLoader().getResourceAsStream("slotconfig.properties");
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
		String ch = null;
		do{
		System.out.println("\nEnter the details of your vehicle... ");
		System.out.println("\nEnter the model name : ");
		String m = sc.next();
		String t = null;
		do{
			System.out.println("\nEnter the type of vehicle : (twowheeler/minifour/maxfour)");
			t = sc.next();
		}while(!(t.equals("twowheeler") || t.equals("minifour") || t.equals("maxfour")));
		String loc = null;
		do{
			System.out.println("\nEnter the location of your desired parking lot : ");
			loc = sc.next();
		}while(!(loc.equals("Ghatkoper") || loc.equals("Andheri") || loc.equals("Dadar")));
		
		
		//check conditions for invalid inputs...(done)
		
		int w = 0;
		Vehicle v = null;
		if(t.equals("twowheeler")){
			w = 2;
			v = new TwoWheeler( m, loc, t, w);
		}
		if(t.equals("minifour")){
			w = 4;
			v = new FourMini( m, loc, t, w);
		}
		if(t.equals("maxfour")){
			w = 4;
			v = new MaxFour( m, loc, t, w);
		}
		
		//Have to implement 2 more classes of vehicle...(Done)
		ParkingLot pl;
		if(loc.equals(gkp.getLocation()))
			pl = gkp;
		else if(loc.equals(da.getLocation()))
			pl = da;
		else// if(loc == an.getLocation())
			pl = an;
		System.out.println(pl.getLocation());
		//System.out.println(v.getType());
		Slot slot = pl.emptySlot(v, p);
		//if slots full, can check for other lot for empty slots(optional)
		if(slot == null){
			System.out.println("\nParking Slot not available!!!");
			sc.close();
			return;
		}
		System.out.println(slot);
		System.out.println("Enter the service time (in hours) : ");
		int serviceTime = sc.nextInt();
		Ticket tic = new Ticket(serviceTime, slot);
		tic.generateTicket();
		double cost = tic.generateCost();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkSystem","root","");
			PreparedStatement st = con.prepareStatement("insert into vehicleEntry values(?,?,?,?,?,?,?,?,?,?)");
			st.setString(1,v.getModel());
			st.setString(2,pl.getLocation());
			st.setInt(3,slot.getSlotNo());
			st.setDate(4,Date.valueOf(tic.getEntryDate()));
			st.setTime(5,Time.valueOf(tic.getEntryTime()));
			st.setDate(6,Date.valueOf(tic.getExpiryDate()));
			st.setTime(7,Time.valueOf(tic.getExpiryTime()));
			st.setLong(8,tic.getTicketNo());
			st.setDouble(9,cost);
			st.setInt(10,slot.getSlotType());
			int updated = st.executeUpdate();
			System.out.println(updated + " results inserted");
			con.close();
		}
		catch(Exception e){
			System.out.println("Exception caught");
			e.printStackTrace();
		}
			
		//generating a task to free slot after service time...
		pl.addSlotAfter(tic, slot);
		//double total = tic.generateCost();
		v.display(p);
		tic.displayTicket(cost);
		System.out.println("Enter 1 to continue...");
		ch = sc.next();
		}while(ch.equals("1"));
		sc.close();
	}

}
