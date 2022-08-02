function loadHtml(id, filename) {
	
	let xhttp;
	let element = document.getElementById(id);
	let file = filename;
	
	if (file) {
		
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4) {
				if (this.status == 200) {
					element.innerHTML = this.responseText;
				}
				if (this.status == 404) {
					element.innerHTML = this.responseText;
				}
			}
		}
		
		xhttp.open("GET", 'SidebarTemplates/' + file, true);
		xhttp.send();
		return;
	}
}
