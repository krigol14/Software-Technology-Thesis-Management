package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basic.Admin;
import basic.Encryption;
import basic.Professor;
import basic.Student;
import database.Dao;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// create an object of the Dao class in order to manage the connection with the database
	private Dao dao;
	
	// when this servlet gets initialized, the dao object gets instantiated
	public void init() {
		dao = new Dao();
	}
       
    public Register() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String role;
		
		// determine the role of the user registering based on his data
		// if the user registering has a specialty, he is a professor
		if (request.getParameter("specialty") != null) {
			role = "professor";
		}
		// if the user registering has a registration_number, he is a student
		else if (request.getParameter("registration_number") != null) { 
			role = "student";
		}
		// if the user registering doesn't have a specialty or registration_number, he is an admin
		else {
			role = "admin";
		}
		
		// add user to the database based on his role
		// professor registration
		if (role == "professor") {
			
			// request and store the user input from the registerProfessor.jsp form
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String first_name = request.getParameter("first_name");
			String last_name = request.getParameter("last_name");
			String phone_number = request.getParameter("phone_number");
			String office_address = request.getParameter("office_address");
			String specialty = request.getParameter("specialty");
			String jsp_name = request.getParameter("jsp_name");
			
			// hash the password
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);		
			String hashed_password = Encryption.getHashMD5(password, random.toString());
					
			// create a new professor object using the user's input
			Professor professor = new Professor();
			professor.setEmail(email);
			professor.setPassword(hashed_password);
			professor.setFirst_name(first_name);
			professor.setLast_name(last_name);
			professor.setPhone_number(phone_number);
			professor.setOffice_address(office_address);
			professor.setSpecialty(specialty);
			
			try {
				dao.insert(professor);
				
				/*
				 * check from which .jsp file is the request coming from 
				 * if it's coming from adminAddProfessor.jsp, the jsp_name won't be null 
				 * so once the admin adds a new professor, don't redirect him to the login page
				 * but show him the registered professors
				 * 
				 * if the jsp_name is null, the request is coming from registerProfessor,
				 * so after the professor registers by himself, redirect him to the login page
				 * so that he can log in with his new credentials
				 */
				if (jsp_name != null) {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Succesfully added professor!');");
					out.println("window.location='adminManageProfessor.jsp';");
					out.println("</script>");
				}
				else {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('You have been succesfully registered!');");
					out.println("window.location='login.jsp';");
					out.println("</script>");
				}
					
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}	
			
		}
		// student registration
		else if (role == "student") {
			
			// request and store the user input from the registerStudent.jsp form
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String first_name = request.getParameter("first_name");
			String last_name = request.getParameter("last_name");
			String phone_number = request.getParameter("phone_number");
			String registration_number = request.getParameter("registration_number");
			String jsp_name = request.getParameter("jsp_name");
			
			// hash the password
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);		
			String hashed_password = Encryption.getHashMD5(password, random.toString());
					
			// create a new student object using the user's input
			Student student = new Student();
			student.setEmail(email);
			student.setPassword(hashed_password);
			student.setFirst_name(first_name);
			student.setLast_name(last_name);
			student.setPhone_number(phone_number);
			student.setRegistration_number(registration_number);
			
			try {
				dao.insert(student);
				
				/*
				 * check from which .jsp file is the request coming from 
				 * if it's coming from adminAddStudent.jsp, the jsp_name won't be null 
				 * so once the admin adds a new student, don't redirect him to the login page
				 * but show him the registered students
				 * 
				 * if the jsp_name is null, the request is coming from registerStudent,
				 * so after the student registers by himself, redirect him to the login page
				 * so that he can log in with his new credentials
				 */
				if (jsp_name != null) {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Succesfully added student!');");
					out.println("window.location='adminManageStudent.jsp';");
					out.println("</script>");
				}
				else {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('You have been succesfully registered!');");
					out.println("window.location='login.jsp';");
					out.println("</script>");
				}
					
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}	
			
		}
		// admin registration
		else {
			
			// request and store the user input from the registerAdmin.jsp form
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			// hash the password
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);		
			String hashed_password = Encryption.getHashMD5(password, random.toString());
					
			// create a new admin object using the user's input
			Admin admin = new Admin();
			admin.setEmail(email);
			admin.setPassword(hashed_password);
			
			try {
				dao.insert(admin);
							
				// show message
				out.println("<script type=\"text/javascript\">");
				out.println("alert('You have been succesfully registered!');");
				out.println("window.location='login.jsp';");
				out.println("</script>");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
