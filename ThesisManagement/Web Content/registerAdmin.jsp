<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Registration</title>

<meta charset="UTF-8">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
</head>

<body>
	<div align="center">
		<h3>Enter your data below</h3>
		<br>
		<form action="<%=request.getContextPath()%>/Register" method="post">
			<table class="table table-borderless" style="width:50%">
				<tr>
					<td><label>E-mail:</label></td>
					<td><input type="text" name="email" placeholder="E-mail" class="form-control"/></td>
				</tr>
				<tr>
					<td><label>Password:</label></td>
					<td><input type="password" name="password" id="password" placeholder="Password" class="form-control"/></td>
				</tr>
				<tr>
					<td><label>Confirm Password:</label></td>
					<td><input type="password" name="confirm_password" id="confirm_password" placeholder="Confirm Password" class="form-control"/></td>
				</tr>
			</table>
			<input type="submit" value="Register" class="btn btn-primary"/>
		</form>
	</div>
<script type="text/javascript" src="Javascript/confirmPassword.js"></script>
</body>
</html>
