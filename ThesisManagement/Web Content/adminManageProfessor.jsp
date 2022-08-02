<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="database.Dao"%>
<!DOCTYPE html>
<html>

<head>
<title>Manage Professors</title>
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
			
			<br><br>
		
		<div class="container">
		<table style = "margin-left: auto; margin-right: auto" class="table bordered  table-hover table-responsive">
		<thead>
			<tr style="background-color:#6d7fcc; color:white; text-align:center">
				<td>First Name</td>
				<td>Last Name</td>
				<td>Email</td>
				<td>Phone Number</td>
				<td>Office Address</td>
				<td>Specialty</td>
				<td>Actions</td>
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
			String sql = "select * from thesismanagement.professor";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
		%>
		<tbody style="text-align:center">
			<tr>
				<td><%=resultSet.getString("first_name")%></td>
				<td><%=resultSet.getString("last_name")%></td>
				<td><%=resultSet.getString("email")%></td>
				<td><%=resultSet.getString("phone_number")%></td>
				<td><%=resultSet.getString("office_address")%></td>
				<td><%=resultSet.getString("specialty")%></td>
				<td><a href="DeleteUser?email=<%=resultSet.getString("email")%>&specialty=<%=resultSet.getString("specialty")%>">
						<button type="button" class="btn btn-outline-danger" >Delete</button>
					</a>
					<a href="adminEditProfessor.jsp?email=<%=resultSet.getString("email")%>">
						<button type="button"class="btn btn-outline-primary" style="margin-left:10px">Edit</button>
					</a>
				</td>
				
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
