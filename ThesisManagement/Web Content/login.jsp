<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Log In</title>

<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="style_registerAs.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">

</head>
<body>
	<div align="center" class="form-group">
		<h1>Thesis Management</h1>
		<br>
		<form action="<%=request.getContextPath()%>/Login" method="post">
			<table class="table table-borderless" style="width:40%">
				<tr>
					<td><label>Please Enter Your Email:</label></td>
				</tr>
				<tr>
					<td><input type="text" name="email" placeholder="E-mail" class="form-control"/></td>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td><label>Please Enter Your Password:</label></td>
				</tr>
				<tr>
					<td><input type="password" name="password" placeholder="Password" class="form-control"/></td>
				</tr>
			</table>
			<input type="submit" value="Log In" class="btn btn-primary"/>
		</form>
		
		<br>
		<div class="dropdown">
			<button onclick="myFunction()" class="dropbtn">Register now!</button>
			<div id="myDropdown" class="dropdown-content">
				<a href="registerAdmin.jsp">Admin</a> 
				<a href="registerStudent.jsp">Student</a>
				<a href="registerProfessor.jsp">Professor</a>
			</div>
		</div>
	</div>

	<script>
		// when the user clicks on the "register now" button, toggle between hiding and showing the dropdown content with registration options
		function myFunction() {
			document.getElementById("myDropdown").classList.toggle("show");
		}

		// close the dropdown showing registration options if the user clicks outside of it
		window.onclick = function(event) {
			if (!event.target.matches('.dropbtn')) {
				var dropdowns = document.getElementsByClassName("dropdown-content");
				var i;
				for (i = 0; i < dropdowns.length; i++) {
					var openDropdown = dropdowns[i];
					if (openDropdown.classList.contains('show')) {
						openDropdown.classList.remove('show');
					}
				}
			}
		}
	</script>
</body>
</html>
