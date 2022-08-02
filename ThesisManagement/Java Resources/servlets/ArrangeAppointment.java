package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basic.Appointment;
import database.Dao;

@WebServlet("/ArrangeAppointment")
public class ArrangeAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// create an object of the Dao class in order to manage the connection with the database
	private Dao dao;
	
	// when this servlet gets initialized, the dao object gets instantiated
	public void init() {
		dao = new Dao();
	}
       
    public ArrangeAppointment() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String registration_number = request.getParameter("registration_number");
		String datetime = request.getParameter("datetime");
		datetime = datetime.replaceAll("T", " ");
		String reason = request.getParameter("reason");
		
		Appointment app = new Appointment();
		app.setEmail(email);
		app.setRegistration_number(registration_number);
		app.setDatetime(datetime);
		app.setReason(reason);
		
		try {
			String result = dao.addAppointment(app);
			
			if (result == "Appointment added succesfully!") {
				// show message
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Appointment arranged succesfully!');");
				out.println("window.location='professorArrangeAppointment.jsp';");
				out.println("</script>");
			}
			else {
				// show message
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Error, try again!');");
				out.println("window.location='professorArrangeAppointment.jsp';");
				out.println("</script>");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
