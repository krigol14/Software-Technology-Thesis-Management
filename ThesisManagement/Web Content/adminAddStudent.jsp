<!DOCTYPE html>
<html>

<head>
<title>Add Student</title>
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
			
		<div align="left" class="form-group">
		<form action="<%=request.getContextPath()%>/Register" method="post">
			<table class="table table-borderless" style="width:60%">
				<tr><td><br><h4>Enter the student's data below</h4><br></td></tr>
				<tr>
					<td><label>E-mail:</label></td>
					<td><input type="text" name="email" placeholder="E-mail" class="form-control" /></td>
				</tr>
				<tr>
					<td><label>Password:</label></td>
					<td><input type="password" name="password" id="password" placeholder="Password" class="form-control" /></td>
				</tr>
				<tr>
					<td><label>Confirm Password:</label></td>
					<td><input type="password" name="confirm_password" id="confirm_password" placeholder="Confirm Password" class="form-control" /></td>
				</tr>
				<tr>
					<td><label>First Name:</label></td>
					<td><input type="text" name="first_name" placeholder="First Name" class="form-control" /></td>
				</tr>
				<tr>
					<td><label>Last Name:</label></td>
					<td><input type="text" name="last_name" placeholder="Last Name" class="form-control" /></td>
				</tr>
				<tr>
					<td><label>Phone Number:</label></td>
					<td><input type="text" name="phone_number" placeholder="Phone Number" class="form-control" /></td>
				</tr>
				<tr>
					<td><label>Registration Number:</label></td>
					<td><input type="text" name="registration_number" placeholder="Registration Number" class="form-control" /></td>
				</tr>
			</table>
			<input type="hidden" name="jsp_name" value="adminAddStudent" />
			<input type="submit" value="Register" class="btn btn-primary" />
		</form>
		</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="Javascript/sidebar.js"></script>
	<script type="text/javascript" src="Javascript/confirmPassword.js"></script>
	<!-- NEW ADDED 28.06.2022 -->
	<script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap4.min.js"></script>
	<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function () {
	    $('#example').DataTable();
	});</script>
	<!-- NEW ADDED 28.06.2022 /-->
    
</body>
</html>
