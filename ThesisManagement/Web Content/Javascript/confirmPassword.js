/*
check if password and confirm_password fields are the same
if not, show alert message
*/
var password = document.getElementById("password");
var confirm_password = document.getElementById("confirm_password");

function validatePassword() {
	if (password.value != confirm_password.value) {
		confirm_password.setCustomValidity("Passwords don't match, try again!");
		} 
	else {
		confirm_password.setCustomValidity('');
		}
	}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
