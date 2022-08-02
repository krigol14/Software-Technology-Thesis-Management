<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="database.Dao"%>
<!DOCTYPE html>
<html>

<head>
<title>Upload File</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">

<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>
<script src="Javascript/loadSidebar.js"></script>

</head>

<body onload='loadHtml("side", "studentSidebar.html")'>
	
	<div class="wrapper">
	
		<div id='side' class='side'></div>

		<!-- Page Content  -->
		<div id="content">
		
			<div class="container-fluid">
				<button type="button" id="sidebarCollapse" class="btn btn-info">
					<i class="fas fa-align-left"></i> 
					<span>Toggle Sidebar</span>
				</button>	
			</div>
			
			<br><br>

	<%
	HttpSession ses = request.getSession();
	String email = ses.getAttribute("email").toString();
	String registration_number = "";
	String professor_email = "";
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");
		
		Statement statement = connection.createStatement();
		String sql = "select * from thesismanagement.student where email = '" + email + "'";
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			registration_number = resultSet.getString("registration_number");
		}
		
		Statement statement2 = connection.createStatement();
		String sql2 = "select * from thesismanagement.theses where registration_number = '" + registration_number + "'";
		ResultSet resultSet2 = statement.executeQuery(sql2);
		
		while (resultSet2.next()) {
			professor_email = resultSet2.getString("email");
		}
		
		connection.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	%>
	
	<div align="left" class="form-group">
		<form action="<%=request.getContextPath()%>/UploadFile" method="post" enctype="multipart/form-data">
			<table class="table table-borderless" style="width:60%">
				<tr><td colspan="6"><br><h4>Fill up the form below to upload a file</h4><br></td></tr>
				<tr>
					<td><label>File Description:</label></td>
					<td>
					<input type="hidden" name="uploaded_by" value="student" />
					<input type="hidden" name="registration_number" value="<%= registration_number %>" />
					<input type="hidden" name="email" value="<%= professor_email %>" />
					
					<input type="text" name="file_message" placeholder="Message about file for supervisor" class="form-control"/>
					</td>
				</tr>
				<tr>
					<td><label>File:</label></td>
					<td><input type="file" name="file_data" class="form-control"/></td>
				</tr>
			</table>
			<input type="submit" value="Upload" class="btn btn-primary"/>
		</form>
	</div>
	</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="Javascript/sidebar.js"></script>
    
</body>
</html>
