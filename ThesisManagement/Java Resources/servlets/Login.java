package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import basic.Admin;
import basic.Professor;
import basic.Encryption;
import basic.Student;
import database.Dao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// create an object of the Dao class in order to manage the connection with the database
	private Dao dao;
	
	// when this servlet gets initialized, the dao object gets instantiated
	public void init() {
		dao = new Dao();
	}
       
    public Login() {
        super();
    }
    
    // helper function for when a user logs in
    public void redirect(HttpServletRequest request, HttpServletResponse response, String email, String page) throws IOException {
		
    	HttpSession session = request.getSession();
		session.setAttribute("email", email);
		
		response.sendRedirect(page);	
    }
	
	// doPost method used in order to manage the login data of the user from the login.jsp form
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// request and store the user input from the login.jsp form
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// hash the password
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);		
		String hashed_password = Encryption.getHashMD5(password, random.toString());
		
		Admin admin = new Admin();
		admin.setEmail(email);
		admin.setPassword(hashed_password);
		
		Student student = new Student();
		student.setEmail(email);
		student.setPassword(hashed_password);
		
		Professor professor = new Professor();
		professor.setEmail(email);
		professor.setPassword(hashed_password);
		
		// check if the user who is logging in is a student, professor or admin and redirect him to the corresponding page
		try {

			if (dao.validate(student)) {
				redirect(request, response, email, "student.jsp");
			}
			else if (dao.validate(professor)) {
				redirect(request, response, email, "professor.jsp");
			}
			else if (dao.validate(admin)) {
				redirect(request, response, email, "admin.jsp");		
			}	
			else {
				// show message about wrong credentials and redirect to initial login form
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Email or password incorrect, try again!');");
				out.println("location='index.jsp';");
				out.println("</script>");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
}
