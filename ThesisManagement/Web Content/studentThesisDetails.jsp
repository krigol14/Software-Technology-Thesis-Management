<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="database.Dao"%>

<!DOCTYPE html>
<html>

<head>
<title>Thesis Details</title>
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
			
		<div class="container">
		<h3>Thesis Details</h3>
		
	
		<table style = "margin-left: auto; margin-right: auto;" class="table bordered  table-hover table-responsive">
		<thead>
			<tr style="background-color:#6d7fcc; color:white; text-align:center">
				<td>Thesis Title</td>
				<td>Coding Language(s)</td>
				<td>Technologies Used</td>
				<td>Start Date</td>
			</tr>
		</thead>
		
		<%
		HttpSession ses = request.getSession();
		String email = ses.getAttribute("email").toString();
		String registration_number = "";
		String professor_email = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
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
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");
			Statement statement = connection.createStatement();
			String sql = "select * from thesismanagement.theses where registration_number = '" + registration_number + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			
			Statement statement2 = connection.createStatement();
			String sql2 = "select * from thesismanagement.professor where email = '" + professor_email + "'";
			ResultSet resultSet2 = statement2.executeQuery(sql2);

			while (resultSet.next() && resultSet2.next()) {
		%>
		<tbody>
			<tr>
				<td><%=resultSet.getString("title")%></td>
				<td><%=resultSet.getString("coding_language")%></td>
				<td><%=resultSet.getString("technologies_used")%></td>
				<td><%=resultSet.getString("start_date")%></td>
			</tr>
		</table>
		
		<br><br>
		
		<h3>Supervisor Professor Details</h3>
		<table style = "margin-left: auto; margin-right: auto;" class="table bordered  table-hover table-responsive">
		<thead>
			<tr style="background-color:#6d7fcc; color:white; text-align:center">
				<td>Name</td>
				<td>Email</td>
				<td>Phone Number</td>
				<td>Office Address</td>
				<td>Specialty</td>
			</tr>
		</thead>
		
		<tbody style="text-align:center">
			<tr>
				<td><%=resultSet2.getString("first_name")%> <%=resultSet2.getString("last_name")%></td>
				<td><%=resultSet2.getString("email")%></td>
				<td><%=resultSet2.getString("phone_number")%></td>
				<td><%=resultSet2.getString("office_address")%></td>
				<td><%=resultSet2.getString("specialty")%></td>
			</tr>
			
		</table>
		<%
		}
		connection.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		%>
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
