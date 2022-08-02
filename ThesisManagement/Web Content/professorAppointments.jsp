<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="database.Dao"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>

<head>
<title>Manage Supervising Student</title>
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

<body onload='loadHtml("side", "professorMainSidebar.html")'>
	
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
	<h3>Future Appointments</h3>
	<table style = "margin-left: auto; margin-right: auto" class="table bordered  table-hover table-responsive">
		<thead>	
			<tr style="background-color:#6d7fcc; color:white; text-align:center">
				<td>Students Registration Number</td>
				<td>Appointment Date-Time</td>
				<td>Reason</td>
			</tr>
		</thead>
		<%
		HttpSession ses = request.getSession();
		String professor_email = ses.getAttribute("email").toString();
		
		// get today's date
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);
		String now_date = formattedDate.substring(0, 10);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesismanagement?useSSL = false", "root", "An534090");
			Statement statement = connection.createStatement();
						
			String sql = "select * from thesismanagement.appointments where email = '" + professor_email + "'";
			ResultSet res = statement.executeQuery(sql);
			
			while (res.next()) {
				String datetime = res.getString("datetime");
				String datetime_date = datetime.substring(0, 10);
				// condition to get only the future appointments
				if (now_date.compareTo(datetime_date) < 0){	
		%>
			<tbody>
				<tr class = "active-row">
					<td><%=res.getString("registration_number")%></td>
					<td><%=res.getString("datetime")%></td>
					<td><%=res.getString("reason")%></td>
				</tr>
			</tbody>
		<%
		}
		}
		connection.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		%>

	</table>
	
	<br><br>
	
	<h3>Past Appointments</h3>
	<table style = "margin-left: auto; margin-right: auto" class="table bordered  table-hover table-responsive">
		<thead>	
			<tr style="background-color:#6d7fcc; color:white; text-align:center">
				<td>Students Registration Number</td>
				<td>Appointment Date-Time</td>
				<td>Reason</td>
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
						
			String sql = "select * from thesismanagement.appointments where email = '" + professor_email + "'";
			ResultSet res = statement.executeQuery(sql);
			
			while (res.next()) {
				String datetime = res.getString("datetime");
				String datetime_date = datetime.substring(0, 10);
				// condition to get only the past appointments
				if (now_date.compareTo(datetime_date) > 0){	
		%>
			<tbody>
				<tr class = "active-row">
					<td><%=res.getString("registration_number")%></td>
					<td><%=res.getString("datetime")%></td>
					<td><%=res.getString("reason")%></td>
				</tr>
			</tbody>
		<%
		}
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
    
</body>
</html>
