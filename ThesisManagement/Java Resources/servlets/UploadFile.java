package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/UploadFile")
@MultipartConfig
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadFile() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");	
			
            String file_message = request.getParameter("file_message");
            String registration_number = request.getParameter("registration_number");
            String email = request.getParameter("email");
            String uploaded_by = request.getParameter("uploaded_by");
            
            // student 
            if (uploaded_by != null) {
            	
            	uploadFile(request, connection, "student", registration_number, email, file_message);
            	
				// show message
				out.println("<script type=\"text/javascript\">");
				out.println("alert('File uploaded succesfully!');");
				out.println("window.location='studentUploadFile.jsp';");
				out.println("</script>");

            // professor
            } else {
            	
            	uploadFile(request, connection, "professor", registration_number, email, file_message);
            	
				// show message
				out.println("<script type=\"text/javascript\">");
				out.println("alert('File uploaded succesfully!');");
				out.println("window.location='professorUploadFile.jsp';");
				out.println("</script>");
            }

            connection.close();
            
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error: " + e.getMessage());
		}
	}
    
    private String extractFileName(Part part) {

        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
            	
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');

                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
    
    private void uploadFile(HttpServletRequest request, Connection connection, String uploaded_by, String registration_number, String email, String file_message) throws IOException, ServletException, SQLException {
    	
    	for (Part part : request.getParts()) {
        	
            String file_name = extractFileName(part);
            
            if (file_name != null && file_name.length() > 0) {
                InputStream inputStream = part.getInputStream();
                
                String sql = "insert into thesismanagement.files (uploaded_by, registration_number, email, file_name, file_data, file_message) values (?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
                preparedStatement.setString(1, uploaded_by);
                preparedStatement.setString(2, registration_number);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, file_name);
                preparedStatement.setBlob(5, inputStream);
                preparedStatement.setString(6, file_message);
                preparedStatement.executeUpdate();
            }
        }
    }
}
