

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TicketGen
 */
@WebServlet("/TicketGen")
public class TicketGen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketGen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int serviceTime = Integer.parseInt(request.getParameter("time"));
		Slot slot = (Slot)request.getAttribute("slot");
		Vehicle v = (Vehicle)request.getAttribute("vehicle");
		ParkingLot pl = (ParkingLot)request.getAttribute("pl");
		Ticket tic = new Ticket(serviceTime, slot);
		tic.generateTicket();
		double cost = tic.generateCost();
		String asd = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkSystem","root","");
			PreparedStatement st = con.prepareStatement("insert into vehicleEntry values(?,?,?,?,?,?,?,?,?,?,?)");
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
			st.setInt(11, slot.getFloor());
			int updated = st.executeUpdate();
			System.out.println(updated + " results inserted");
			PreparedStatement sp = con.prepareStatement("SET GLOBAL event_scheduler = 1;");
			System.out.println(sp.executeUpdate());
			asd = "del"+tic.getTicketNo();
			PreparedStatement st1 = con.prepareStatement("CREATE EVENT "+asd+"s ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL ? MINUTE DO DELETE FROM parkSystem.vehicleEntry WHERE lotloc = ? AND slotNo = ?;");
			//st1.setString(1, asd);
			st1.setInt(1,serviceTime);
			st1.setString(2,pl.getLocation());
			st1.setInt(3, slot.getSlotNo());
			int ans = st1.executeUpdate();
			System.out.println(ans + " row removed");
			con.close();
		}
		catch(Exception e){
			System.out.println("Exception caught");
			e.printStackTrace();
		}
			
		//generating a task to free slot after service time...
		pl.addSlotAfter(tic, slot);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print("Vehicle added");
		response.sendRedirect("index.html");
	}

}
