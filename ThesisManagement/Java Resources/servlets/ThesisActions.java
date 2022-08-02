package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import basic.Thesis;
import database.Dao;

@WebServlet("/ThesisActions")
public class ThesisActions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// create an object of the Dao class in order to manage the connection with the database
	private Dao dao;
	
	// when this servlet gets initialized, the dao object gets instantiated
	public void init() {
		dao = new Dao();
	}
       
    public ThesisActions() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String registration_number = request.getParameter("registration_number");
		
		try {
			String result = dao.deleteThesis(registration_number);
			
			if (result == "Thesis deleted succesfully!!") {
				// show message
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Succesfully deleted thesis!');");
				out.println("window.location='adminSeeTheses.jsp';");
				out.println("</script>");
			}
			else {
				// show message
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Error, try again!');");
				out.println("window.location='adminAssignThesis.jsp';");
				out.println("</script>");
			}
			

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String registration_number = request.getParameter("registration_number");
		String email = request.getParameter("email");
		String title = request.getParameter("title");
		String coding_language = request.getParameter("coding_language");
		String technologies_used = request.getParameter("technologies_used");
		String start_date = request.getParameter("start_date");
		start_date = start_date.replace("T", " ");
		
		Thesis thesis = new Thesis();
		thesis.setRegistration_number(registration_number);
		thesis.setEmail(email);
		thesis.setTitle(title);
		thesis.setCoding_language(coding_language);
		thesis.setTechnologies_used(technologies_used);
		thesis.setStart_date(start_date);
		
		try {
			String result = dao.assignThesis(thesis);
			
			if (result == "Thesis assigned succesfully!") {
				// show message
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Succesfully assigned thesis!');");
				out.println("window.location='adminAssignThesis.jsp';");
				out.println("</script>");
			}
			else {
				// show message
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Error, try again!');");
				out.println("window.location='adminAssignThesis.jsp';");
				out.println("</script>");
			}
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
