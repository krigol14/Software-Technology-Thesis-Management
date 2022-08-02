package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.Dao;

@WebServlet("/EditData")
public class EditData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// create an object of the Dao class in order to manage the connection with the database
	private Dao dao;
	
	// when this servlet gets initialized, the dao object gets instantiated
	public void init() {
		dao = new Dao();
	}
       
    public EditData() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// common fields for professors/ students
		String email = request.getParameter("email");
		String new_email = request.getParameter("new_email");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String phone_number = request.getParameter("phone_number");
		
		// unique fields for professors/ students
		String registration_number = request.getParameter("registration_number");
		String office_address = request.getParameter("office_address");
		String specialty = request.getParameter("specialty");
		
		// professor
		if (specialty != null) {
			try {
				String result = dao.editProfessorData(new_email, first_name, last_name, phone_number, office_address, specialty, email);
				
				if (result == "Professors data edited succesfully!") {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Succesfully edited professors data!');");
					out.println("window.location='adminManageProfessor.jsp';");
					out.println("</script>");
				}
				else {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Error, try again!');");
					out.println("window.location='adminManageProfessor.jsp';");
					out.println("</script>");
				}
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		// student
		else {
			try {
				String result = dao.editStudentData(new_email, first_name, last_name, phone_number, registration_number, email);
				
				if (result == "Students data edited succesfully!") {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Succesfully edited students data!');");
					out.println("window.location='adminManageStudent.jsp';");
					out.println("</script>");
				}
				else {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Error, try again!');");
					out.println("window.location='adminManageStudent.jsp';");
					out.println("</script>");
				}
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
