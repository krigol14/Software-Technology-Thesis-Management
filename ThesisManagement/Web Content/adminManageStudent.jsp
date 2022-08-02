<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="database.Dao"%>
<!DOCTYPE html>
<html>

<head>
<title>Manage Students</title>
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

<body onload='loadHtml("side", "adminSidebar.html")'>

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
			
		<div class="container-fluid">
		<table style = "margin-left: auto; margin-right: auto; width:100%;  padding-top: 50px" class="table bordered  table-hover table-responsive">
		<thead  style="background-color:#6d7fcc; color:white; text-align:center;" id= "tableHeader">
			<tr>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Email</td>
				<td>Phone Number</td>
				<td>Registration Number</td>
				<td colspan="4">Actions</td>
			</tr>
		</thead>
		
		<%
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");
			Statement statement = connection.createStatement();
			String sql = "select * from thesismanagement.student";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
		%>
		<tbody style="text-align:center">
			<tr>
				<td><%=resultSet.getString("first_name")%></td>
				<td><%=resultSet.getString("last_name")%></td>
				<td><%=resultSet.getString("email")%></td>
				<td><%=resultSet.getString("phone_number")%></td>
				<td><%=resultSet.getString("registration_number")%></td>
				
				<td><a href="DeleteUser?email=<%=resultSet.getString("email")%>">
					<button type="button" class="btn btn-outline-danger">Delete</button>
					</a></td>
				<td><a href="adminEditStudent.jsp?email=<%=resultSet.getString("email")%>">
					<button type="button" class="btn btn-outline-primary">Edit</button>
				</a></td>
				<td><a href="adminAddGrades.jsp?email=<%=resultSet.getString("email")%>">
						<button type="button" class="btn btn-outline-primary">Add Grade</button>
				</a></td>
				<td><a href="adminSeeGrades.jsp?email=<%=resultSet.getString("email")%>">
						<button type="button" class="btn btn-outline-primary">See Grades</button>
				</a></td>
			</tr>
		</tbody>
		<%
		}
		connection.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		%>

	</table>
	</div>
	
	</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="Javascript/sidebar.js"></script>
	<script type="text/javascript" src="Javascript/confirmPassword.js"></script>
	
    
</body>
</html>
