<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="database.Dao"%>
<!DOCTYPE html>
<html>

<head>
<title>Assign Thesis</title>
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
		
		<div class="container-fluid ">
			<button type="button" id="sidebarCollapse" class="btn btn-info">
				<i class="fas fa-align-left"></i> 
				<span>Toggle Sidebar</span>
			</button>	
		</div>
			
		<br><br>
			
		<div align="left" class="form-group">
		<form action="<%=request.getContextPath()%>/ThesisActions" method="post">	
		
		<table class="table table-borderless" style="width:60%">
		
			<tr><td><br><h4>Assignee Details</h4><br></td></tr>
			<tr>
				<td><label>Student:</label></td>
				<td><select name="registration_number" class="form-control" style="width: 300px;">
					<option value="-1">Select Student</option>
					<%		
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");
						Statement statement = connection.createStatement();
						
						// select only the students that don't already have a thesis assigned
						String sql = "SELECT * FROM thesismanagement.student t1 LEFT JOIN thesismanagement.theses t2 ON t2.registration_number = t1.registration_number WHERE t2.registration_number IS NULL";
						ResultSet resultSet = statement.executeQuery(sql);
						while (resultSet.next()) {
							%>
							<option value="<%=resultSet.getString("registration_number")%>"><%=resultSet.getString("first_name")%> <%=resultSet.getString("last_name")%> <%=resultSet.getString("registration_number")%></option>
							<%
						}
					connection.close();
					} catch (Exception e) {
					e.printStackTrace();
					}
					%>
				</select></td>
			</tr>	
						
			<tr>	
				<td><label>Supervisor Professor:</label></td>
				<td><select name="email" class="form-control" style="width: 300px; ">
					<option value="-1">Select Professor</option>
					<%		
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");
						Statement statement = connection.createStatement();
						String sql = "select * from thesismanagement.professor";
						ResultSet resultSet = statement.executeQuery(sql);
						while (resultSet.next()) {
							%>
							<option value="<%=resultSet.getString("email")%>"><%=resultSet.getString("first_name")%> <%=resultSet.getString("last_name")%> <%=resultSet.getString("email")%></option>
							<%
						}
					connection.close();
					} catch (Exception e) {
					e.printStackTrace();
					}
					%>
				</select></td>
			</tr>
		
			<tr><td><br><h4>Thesis Details</h4><br></td></tr>
			
			<tr>
				<td><label>Title:</label></td>
				<td><input type="text" name="title" placeholder="Title" class="form-control"/></td>
			</tr>
			<tr>
				<td><label>Coding Language:</label></td>
				<td><input type="text" name="coding_language" placeholder="Coding Language" class="form-control"/></td>
			</tr>
			<tr>
				<td><label>Technologies Used:</label></td>
				<td><input type="text" name="technologies_used" placeholder="Technologies Used" class="form-control"/></td>
			</tr>
			<tr>
				<td><label>Start Date:</label></td>
				<td><input type="datetime-local" name="start_date" placeholder="Start Date" class="form-control"/></td>
			</tr>
		</table>
			
			<input type="submit" value="Assign" class="btn btn-primary"/>
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
