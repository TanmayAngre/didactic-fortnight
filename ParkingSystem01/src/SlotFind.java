

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TicketGen
 */
@WebServlet("/SlotFind")
public class SlotFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SlotFind() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ParkingLot pl;
		
		Vehicle v = (Vehicle)request.getAttribute("vehicle");
		ServletContext ctx = getServletContext();
		if(ctx.getAttribute("Ghatkoper").equals(v.getLoc()))
			pl = (ParkingLot)ctx.getAttribute("Ghatkoper");
		else if(ctx.getAttribute("Dadar").equals(v.getLoc()))
			pl = (ParkingLot)ctx.getAttribute("Dadar");
		else// if(loc == an.getLocation())
			pl = (ParkingLot)ctx.getAttribute("Andheri");;
		System.out.println("Debug:"+pl.getLocation());
		//System.out.println(v.getType());
		Slot slot = pl.emptySlot(v, (Properties)ctx.getAttribute("properties"));
		//if slots full, can check for other lot for empty slots(optional)
		if(slot == null){
			System.out.println("\nParking Slot not available!!!");
			return;
		}
		System.out.println("Slot allocated:"+slot);
		request.setAttribute("pl",pl);
		request.setAttribute("slot", slot);
		request.getRequestDispatcher("service.html").forward(request, response);
		//System.out.println("Enter the service time (in hours) : ");
		//int serviceTime = sc.nextInt();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
ParkingLot pl;
		
		Vehicle v = (Vehicle)request.getAttribute("vehicle");
		ServletContext ctx = getServletContext();
		if(ctx.getAttribute("Ghatkoper").equals(v.getLoc()))
			pl = (ParkingLot)ctx.getAttribute("Ghatkoper");
		else if(ctx.getAttribute("Dadar").equals(v.getLoc()))
			pl = (ParkingLot)ctx.getAttribute("Dadar");
		else// if(loc == an.getLocation())
			pl = (ParkingLot)ctx.getAttribute("Andheri");;
		System.out.println("Debug:"+pl.getLocation());
		//System.out.println(v.getType());
		Slot slot = pl.emptySlot(v, (Properties)ctx.getAttribute("properties"));
		//if slots full, can check for other lot for empty slots(optional)
		if(slot == null){
			System.out.println("\nParking Slot not available!!!");
			return;
		}
		System.out.println("Slot allocated:"+slot);
		request.setAttribute("pl",pl);
		request.setAttribute("slot", slot);
		request.getRequestDispatcher("service.html").forward(request, response);
		//System.out.println("Enter the service time (in hours) : ");
		//int serviceTime = sc.nextInt();
	}

}
