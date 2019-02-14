

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveVehicle
 */
@WebServlet("/RemoveVehicle")
public class RemoveVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveVehicle() {
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
		//tic.setExpiryDate(LocalDate.now());
		//tic.setExpiryTime(LocalTime.now());
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkSystem","root","");
			PreparedStatement sp = con.prepareStatement("SET GLOBAL event_scheduler = 1;");
			System.out.println(sp.executeUpdate());
			//asd = "del"+tic.getTicketNo();
			PreparedStatement st1 = con.prepareStatement("DELETE FROM vehicleEntry WHERE ticketID = ?;");
			long number = Long.parseLong(request.getParameter("ticket"));
			st1.setLong(1, number);
			System.out.println(st1.executeUpdate());
			System.out.println("Deleted at current timestamp");
			con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print("Vehicle removed");
		response.sendRedirect("index.html");
		//pl.addSlotAfter(tic,slot);
	}

}
