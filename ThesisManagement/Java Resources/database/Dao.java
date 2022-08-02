package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import basic.Admin;
import basic.Appointment;
import basic.Grade;
import basic.Professor;
import basic.Student;
import basic.Thesis;

public class Dao {
	
	// function that inserts an appointment in the database
	public String addAppointment(Appointment app) throws ClassNotFoundException {
				
		String result = "Appointment added succesfully!";

		Class.forName("com.mysql.cj.jdbc.Driver");
				
		try (
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
				PreparedStatement preparedStatement = connection.prepareStatement("insert into thesismanagement.appointments (email, registration_number, datetime, reason) values (?, ?, ?, ?)")
			) {
				preparedStatement.setString(1, app.getEmail());
				preparedStatement.setString(2, app.getRegistration_number());
				preparedStatement.setString(3, app.getDatetime());
				preparedStatement.setString(4, app.getReason());
				preparedStatement.executeUpdate();	
			} 
		catch (SQLException e) {
				e.printStackTrace();
				result = "Data not entered, try again!";
			}
		
		return result;
	}
	
	// function that deletes a thesis
	public String deleteThesis(String registration_number) throws ClassNotFoundException {
				
		String result = "Thesis deleted succesfully!";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
			
			// edit student's fields 
			PreparedStatement preparedStatement = connection.prepareStatement("delete from thesismanagement.theses where registration_number = ?");		
			preparedStatement.setString(1, registration_number);		
			preparedStatement.executeUpdate();	
			
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
				
		return result;
	}
	
	// function that deletes a student
	public String deleteStudent(String email) throws ClassNotFoundException {
				
		String result = "Student deleted succesfully!";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
			
			// edit student's fields 
			PreparedStatement preparedStatement = connection.prepareStatement("delete from thesismanagement.student where email = ?");		
			preparedStatement.setString(1, email);		
			preparedStatement.executeUpdate();	
			
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
				
		return result;
	}
	
	// function that deletes a professor
	public String deleteProfessor(String email) throws ClassNotFoundException {
				
		String result = "Professor deleted succesfully!";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
			
			// edit student's fields 
			PreparedStatement preparedStatement = connection.prepareStatement("delete from thesismanagement.professor where email = ?");		
			preparedStatement.setString(1, email);		
			preparedStatement.executeUpdate();	
			
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
				
		return result;
	}
	
	// function that edits a students data
	public String editStudentData(String new_email, String first_name, String last_name, String phone_number, String registration_number, String email) throws ClassNotFoundException {
				
		String result = "Students data edited succesfully!";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
			
			// edit student's fields 
			PreparedStatement preparedStatement = connection.prepareStatement("update thesismanagement.student set email=?,first_name=?,last_name=?,phone_number=?,registration_number=? where email=?");		
			preparedStatement.setString(1, new_email);		
			preparedStatement.setString(2, first_name);
			preparedStatement.setString(3, last_name);
			preparedStatement.setString(4, phone_number);
			preparedStatement.setString(5, registration_number);
			preparedStatement.setString(6, email);
			preparedStatement.executeUpdate();	
			
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
				
		return result;
	}
	
	// function that edits a professors data
	public String editProfessorData(String new_email, String first_name, String last_name, String phone_number, String office_address, String specialty, String email) throws ClassNotFoundException {
				
		String result = "Professors data edited succesfully!";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
			
			// edit student's fields 
			PreparedStatement preparedStatement = connection.prepareStatement("update thesismanagement.professor set email=?,first_name=?,last_name=?,phone_number=?,office_address=?,specialty=? where email=?");		
			preparedStatement.setString(1, new_email);		
			preparedStatement.setString(2, first_name);
			preparedStatement.setString(3, last_name);
			preparedStatement.setString(4, phone_number);
			preparedStatement.setString(5, office_address);
			preparedStatement.setString(6, specialty);
			preparedStatement.setString(7, email);
			preparedStatement.executeUpdate();	
			
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
				
		return result;
	}

	// function that assigns theses
	public String assignThesis(Thesis thesis) throws ClassNotFoundException {
				
		String result = "Thesis assigned succesfully!";
			
		Class.forName("com.mysql.cj.jdbc.Driver");
				
		try (
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
				PreparedStatement preparedStatement = connection.prepareStatement("insert into theses values (?, ?, ?, ?, ?, ?)")
			) {
				preparedStatement.setString(1, thesis.getRegistration_number());
				preparedStatement.setString(2, thesis.getEmail());
				preparedStatement.setString(3, thesis.getTitle());
				preparedStatement.setString(4, thesis.getCoding_language());
				preparedStatement.setString(5, thesis.getTechnologies_used());
				preparedStatement.setString(6, thesis.getStart_date());
				preparedStatement.executeUpdate();	
			} 
		catch (SQLException e) {
				e.printStackTrace();
				result = "Data not entered, try again!";
			}
				
		return result;
	}
	
	// function that adds grades for a specific student
	public String addGrade(Grade grade) throws ClassNotFoundException {
				
		String result = "Grade added succesfully!";
			
		Class.forName("com.mysql.cj.jdbc.Driver");
				
		try (
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
				PreparedStatement preparedStatement = connection.prepareStatement("insert into grades values (?, ?, ?)")
			) {
				preparedStatement.setString(1, grade.getEmail());
				preparedStatement.setString(2, grade.getLesson_name());
				preparedStatement.setString(3, grade.getLesson_grade());
				preparedStatement.executeUpdate();	
			} 
		catch (SQLException e) {
				e.printStackTrace();
				result = "Data not entered, try again!";
			}
				
		return result;
	}
	
	// function that inserts an administrator in the database
	public String insert(Admin admin) throws ClassNotFoundException {
				
		String result = "User registered succesfully!";
			
		Class.forName("com.mysql.cj.jdbc.Driver");
				
		try (
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
				PreparedStatement preparedStatement = connection.prepareStatement("insert into admin values (?, ?)")
			) {
				preparedStatement.setString(1, admin.getEmail());
				preparedStatement.setString(2, admin.getPassword());
				preparedStatement.executeUpdate();	
			} 
		catch (SQLException e) {
				e.printStackTrace();
				result = "Data not entered, try again!";
			}
				
		return result;
	}
	
	// function that inserts a professor in the database
	public String insert(Professor professor) throws ClassNotFoundException {
				
		String result = "User registered succesfully!";
			
		Class.forName("com.mysql.cj.jdbc.Driver");
				
		try (
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
				PreparedStatement preparedStatement = connection.prepareStatement("insert into professor values (?, ?, ?, ?, ?, ?, ?)")
			) {
				preparedStatement.setString(1, professor.getEmail());
				preparedStatement.setString(2, professor.getPassword());
				preparedStatement.setString(3, professor.getFirst_name());
				preparedStatement.setString(4, professor.getLast_name());
				preparedStatement.setString(5, professor.getPhone_number());
				preparedStatement.setString(6, professor.getOffice_address());
				preparedStatement.setString(7, professor.getSpecialty());
				preparedStatement.executeUpdate();	
			} 
		catch (SQLException e) {
				e.printStackTrace();
				result = "Data not entered, try again!";
			}
				
		return result;
	}
	
	// function that inserts a student in the database
	public String insert(Student student) throws ClassNotFoundException {
		
		String result = "User registered succesfully!";
			
		Class.forName("com.mysql.cj.jdbc.Driver");
				
		try (
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
				PreparedStatement preparedStatement = connection.prepareStatement("insert into student values (?, ?, ?, ?, ?, ?)")
			) {
				preparedStatement.setString(1, student.getEmail());
				preparedStatement.setString(2, student.getPassword());
				preparedStatement.setString(3, student.getFirst_name());
				preparedStatement.setString(4, student.getLast_name());
				preparedStatement.setString(5, student.getPhone_number());
				preparedStatement.setString(6, student.getRegistration_number());
				preparedStatement.executeUpdate();	
			} 
		catch (SQLException e) {
				e.printStackTrace();
				result = "Data not entered, try again!";
			}
				
		return result;
	}
	
	// function that validates whether the data given in the login form are correct 
	public boolean validate(Admin admin) throws ClassNotFoundException {
		
		boolean status = false;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try (
			// establish the connection with the database javabasics giving the url and the database's username and password
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
			PreparedStatement preparedStatement = connection.prepareStatement("select * from admin where email = ? and password = ?")
			)
		{
			// the prepared statement gets its values from the patient object given as a parameter to the validate function
			preparedStatement.setString(1, admin.getEmail());
			preparedStatement.setString(2, admin.getPassword());
			
			// execute the prepared statement 
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		// if the prepared statement gets executed, the boolean status is set to true and thus the validation is successful
		return status;		
	}	
	
	// function that validates whether the data given in the login form are correct 
	public boolean validate(Student student) throws ClassNotFoundException {
		
		boolean status = false;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try (
			// establish the connection with the database javabasics giving the url and the database's username and password
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
			PreparedStatement preparedStatement = connection.prepareStatement("select * from student where email = ? and password = ?")
			)
		{
			// the prepared statement gets its values from the patient object given as a parameter to the validate function
			preparedStatement.setString(1, student.getEmail());
			preparedStatement.setString(2, student.getPassword());
			
			// execute the prepared statement 
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		// if the prepared statement gets executed, the boolean status is set to true and thus the validation is successful
		return status;		
	}	
	
	// function that validates whether the data given in the login form are correct 
	public boolean validate(Professor professor) throws ClassNotFoundException {
		
		boolean status = false;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try (
			// establish the connection with the database javabasics giving the url and the database's username and password
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
			PreparedStatement preparedStatement = connection.prepareStatement("select * from professor where email = ? and password = ?")
			)
		{
			// the prepared statement gets its values from the patient object given as a parameter to the validate function
			preparedStatement.setString(1, professor.getEmail());
			preparedStatement.setString(2, professor.getPassword());
			
			// execute the prepared statement 
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		// if the prepared statement gets executed, the boolean status is set to true and thus the validation is successful
		return status;		
	}	
}
