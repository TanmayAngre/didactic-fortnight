

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
@WebListener
public class InitListener implements ServletContextListener {
	
    /**
     * Default constructor. 
     */
    public InitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent eve)  { 
         // TODO Auto-generated method stub
    	System.out.println("context destroyed");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent eve)  { 
         // TODO Auto-generated method stub
    	ResultSet rs = null;
    	ParkingLot an, da, gkp;
    	ServletContext ctx = eve.getServletContext();
		try{
			System.out.println(rs);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkSystem","root","");
			ctx.setAttribute("con", con);
			Statement st = con.createStatement();
			rs = st.executeQuery("SELECT lotloc,slotNo,slotType,floor FROM vehicleEntry");
			
		}
		catch(Exception e){
			System.out.println("Exception caught in static stuff" + e);
		}
		System.out.println("60");
		//Slot ArrayList for 3 parking lots storing available slots (floor,slottype,slotno)
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
		System.out.println("87");
		try{
			while(rs.next()){
				System.out.println("90");
				Slot s = new Slot(rs.getInt(4),rs.getInt(3),rs.getInt(2));
				if(rs.getString(1).equals("Andheri")){
					if(slotA.contains(s))
						slotA.remove(s);
				}
				else if(rs.getString(1).equals("Dadar")){
					if(slotB.contains(s))
						slotB.remove(s);
				}
				else if(rs.getString(1).equals("Ghatkoper")){
					if(slotC.contains(s))
						slotC.remove(s);
				}
				System.out.println("Slot "+s.getSlotNo()+" removed");
			}
		}
		catch(SQLException e){
			System.out.println("ResultSet exception caught");
		}
		an = new ParkingLot("Andheri", 3, 200, slotA);
		da = new ParkingLot("Dadar", 3, 200, slotB);
		gkp = new ParkingLot("Ghatkoper", 3, 200, slotC);
		System.out.println("static block initiated");
		
		Properties p = new Properties();
		InputStream input = null;
		input = InitListener.class.getClassLoader().getResourceAsStream("slotconfig.properties");
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
		
		ctx.setAttribute("properties", p);
		ctx.setAttribute("Andheri", an);
		ctx.setAttribute("Dadar", da);
		ctx.setAttribute("Ghatkoper", gkp);
    }
	
}
