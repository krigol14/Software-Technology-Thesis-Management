package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basic.Grade;
import database.Dao;

@WebServlet("/AddGrade")
public class AddGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// create an object of the Dao class in order to manage the connection with the database
	private Dao dao;
	
	// when this servlet gets initialized, the dao object gets instantiated
	public void init() {
		dao = new Dao();
	}
       
    public AddGrade() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String lesson_name = request.getParameter("lesson_name");
		String lesson_grade = request.getParameter("lesson_grade");
		String comes_from = request.getParameter("comes_from");
		
		// professor adding thesis grade
		if (comes_from != null) {
			
			Grade grade = new Grade();
			grade.setEmail(email);
			grade.setLesson_name(lesson_name);
			grade.setLesson_grade(lesson_grade);
			
			try {
				String result = dao.addGrade(grade);
				
				if (result == "Grade added succesfully!") {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Succesfully added grade!');");
					out.println("window.location='professorManageStudent.jsp';");
					out.println("</script>");
				}
				else {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Error, try again!');");
					out.println("window.location='professorManageStudent.jsp';");
					out.println("</script>");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		// admin adding grade
		else {
			
			Grade grade = new Grade();
			grade.setEmail(email);
			grade.setLesson_name(lesson_name);
			grade.setLesson_grade(lesson_grade);
			
			try {
				String result = dao.addGrade(grade);
				
				if (result == "Grade added succesfully!") {
					// show message
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Succesfully added grade!');");
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
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
