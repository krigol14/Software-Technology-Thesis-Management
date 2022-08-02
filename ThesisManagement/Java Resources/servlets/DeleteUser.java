package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.Dao;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// create an object of the Dao class in order to manage the connection with the database
	private Dao dao;
	
	// when this servlet gets initialized, the dao object gets instantiated
	public void init() {
		dao = new Dao();
	}
       
    public DeleteUser() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// common fields for professors/ students
		String email = request.getParameter("email");
		String specialty = request.getParameter("specialty");
		
		// professor
		if (specialty != null) {
			try {
				String result = dao.deleteProfessor(email);
				
				if (result == "Professor deleted succesfully!") {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Professor deleted succesfully!');");
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
				String result = dao.deleteStudent(email);
				
				if (result == "Student deleted succesfully!") {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Student deleted succesfully!');");
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
