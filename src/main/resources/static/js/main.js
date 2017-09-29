/**
 * 
 */
var user;
var contact = [];
var message = [];
var chatuser;
var chatmessage = [];

gethallo();
getcontacts();

function getcontacts() {
	var data = null;

	var xhr = new XMLHttpRequest();
	xhr.withCredentials = true;

	xhr.addEventListener("readystatechange", function() {
		if (this.readyState === 4) {
			console.log(this.responseText);
			contact = JSON.parse(this.responseText);
			console.log(contact);
			create_chat(contact);
		}
	});

	xhr.open("GET", "http://localhost:8080/contactlist");
	xhr.setRequestHeader("cache-control", "no-cache");
	xhr.setRequestHeader("postman-token",
			"0193be6b-73e8-90ea-bc18-d842ef4e4715");

	xhr.send(data);

}
function clickHandlerFor(data) {
	return function() {
		getchat(data);
		chatuser = data;
	};
}
function sendmessage() {
	return function() {
		var mesasgenew = document.getElementById("textarea_id").value;
		// getchat(data);
		console.log(chatuser);
		console.log(mesasgenew);
		document.getElementById("textarea_id").value = "";
		var data = JSON.stringify({
			usermin : chatuser.id,
			message : mesasgenew
		});

		var xhr = new XMLHttpRequest();
		xhr.withCredentials = true;
		xhr.addEventListener("readystatechange", function() {
			if (this.readyState === 4) {
				console.log(this.responseText);
				getchat(chatuser);
			}
		});

		xhr.open("PUT", "http://localhost:8080/newmessage");
		xhr.setRequestHeader("content-type", "application/json");
		xhr.setRequestHeader("cache-control", "no-cache");
		xhr.setRequestHeader("postman-token",
				"fc2c5c0c-8f22-5760-93d7-e456ae0514bd");

		xhr.send(data);

	};
}
function create_chat(array) {
	for (var i = 0; i < array.length; i++) {
		var user = array[i];
		var contactname = document.createTextNode(array[i].name);
		var contactid = document.createTextNode(array[i].id);
		var div = document.createElement("div");
		div.id = "contact" + i;

		var p = document.createElement("p");
		p.id = "contact" + i + "_id";
		p.alt = "";
		p.target = "_blank";
		p.addEventListener("click", clickHandlerFor(user), false);
		var div2 = document.createElement("div");
		div2.id = "contact" + i + "_name";

		document.getElementById("main").appendChild(div);
		document.getElementById(div.id).appendChild(p);
		document.getElementById(p.id).appendChild(div2);
		document.getElementById(div2.id).appendChild(contactname);
	}

}
// sort message array
function datesort(a, b) {
	return new Date(a.date).getTime() - new Date(b.date).getTime();
}
function fillmessage(data) {
	message = JSON.parse(data);
	message.sort(datesort);
	for (var i = 0; i < message.length; i++) {
		message[i].date = unixTime(message[i].date / 1000);
	}
	return message;
}
function getchat(usermin) {
	var data = JSON.stringify(usermin);
	var xhr = new XMLHttpRequest();
	xhr.withCredentials = true;

	xhr.addEventListener("readystatechange", function() {
		if (this.readyState === this.DONE) {
			console.log(this.responseText);
			var message = fillmessage(this.responseText);
			chatmessage = message;//2
			message_display(message);

		}
	});

	xhr.open("PUT", "http://localhost:8080/getchat");
	xhr.setRequestHeader("content-type", "application/json");
	xhr.setRequestHeader("cache-control", "no-cache");
	xhr.setRequestHeader("postman-token",
			"70a6c49d-931b-e4d9-a72e-34be457b63a3");

	xhr.send(data);
}
function gethallo() {

	var data = null;

	var xhr = new XMLHttpRequest();
	xhr.withCredentials = true;

	xhr
			.addEventListener(
					"readystatechange",
					function() {
						if (this.readyState === 4) {
							console.log(this.responseText);
							user = JSON.parse(this.responseText);
							console.log(user);

							document.getElementById("header").innerHTML = ("Hallo " + user.name);
						}
					});

	xhr.open("GET", "http://localhost:8080/loggedUser");
	xhr.setRequestHeader("cache-control", "no-cache");
	xhr.setRequestHeader("postman-token",
			"0193be6b-73e8-90ea-bc18-d842ef4e4715");

	xhr.send(data);
}
function message_display(message) {
	//1.show message Box
	document.getElementById("messagebox").style.display = "block";
	
	
	console.log(message.length);
	var myNode = document.getElementById("message");
	while (myNode.firstChild) {
		myNode.removeChild(myNode.firstChild);
	}

	for (var i = 0; i < message.length; i++) {
		var absender = false;
		var messagetext = document.createTextNode(message[i].message);
		var time = document.createTextNode(message[i].date);

		var divm = document.createElement("div");
		divm.id = "message" + i;
		if (message[i].sender == true) {
			absender = true;
		}

		var p = document.createElement("p");
		p.id = "message" + i + "_id";
		p.alt = "";
		p.target = "_blank";
		// p.addEventListener("click", clickHandlerFor(user), false);
		var div2 = document.createElement("div");
		div2.id = "message" + i + "_name";

		var ptime = document.createElement("p");
		ptime.id = "time" + i + "_id";
		ptime.alt = "";
		ptime.target = "_blank";
		var div3 = document.createElement("div");
		div3.id = "time" + i;

		document.getElementById("message").appendChild(divm);
		document.getElementById(divm.id).appendChild(p);
		document.getElementById(p.id).appendChild(div2);
		document.getElementById(div2.id).appendChild(messagetext);
		document.getElementById(divm.id).appendChild(ptime);
		document.getElementById(ptime.id).appendChild(div3);
		document.getElementById(div3.id).appendChild(time);

		//DESIGN TEXTNACHRICHT
		document.getElementById(p.id).style.fontWeight = "bolder";

		//DESIGN UHRANZEIGE
		document.getElementById(ptime.id).style.fontFamily = "monospace";

		//ABSTAND ZWISCHEN NACHRICHT UND UHRZEIT
		document.getElementById(p.id).style.margin = "0px"
		document.getElementById(p.id).style.padding = "0px"
		document.getElementById(ptime.id).style.margin = "0px"
		document.getElementById(ptime.id).style.padding = "0px"

		//ABSTAND NACHRICHT NACHRICHT
		document.getElementById(divm.id).style.marginBottom = "3%"

		//ABSENDER SENDER SEITE!?
		if (absender == true) {
			document.getElementById(divm.id).style.textAlign = "right";
			document.getElementById(divm.id).style.marginRight = "3%";
		} else {
			document.getElementById(divm.id).style.marginLeft = "3%";
		}
	}

	try {
		var myNode2 = document.getElementById("textbase_id");
		while (myNode2.firstChild) {
			myNode2.removeChild(myNode2.firstChild);
		}
	} catch (err) {

	}

	var textbase = document.createElement("div");
	textbase.className = "input-group";
	textbase.id = "textbase_id";
	var textarea = document.createElement("textarea");
	textarea.className = "form-control custom-control";
	textarea.rows = "3";
	textarea.style = "resize:none;width: 80%;";
	textarea.id = "textarea_id";
	var span = document.createElement("span");
	span.className = "input-group-btn";
	span.id = "span_id";
	var button = document.createElement("button");
	button.className = "btn btn-primary";
	button.id = "button_id";

	var span2 = document.createElement("span");
	span2.id = "span2";

	document.getElementById("messagebox").appendChild(textbase);
	document.getElementById(textbase.id).appendChild(textarea);
	document.getElementById(textbase.id).appendChild(span);
	document.getElementById(span.id).appendChild(button);
	document.getElementById(button.id).appendChild(span2);
	document.getElementById("span2").innerHTML = "Senden";
	button.addEventListener("click", sendmessage(), false);

	//Auto Scroll down
	var objDiv = document.getElementById("message");
	objDiv.scrollTop = objDiv.scrollHeight;

}
function unixTime(unixtime) {

	var u = new Date(unixtime * 1000);
	var date = new Date(unixtime * 1000);
	var formattedDate = ('0' + date.getDate()).slice(-2) + '/'
			+ ('0' + (date.getMonth() + 1)).slice(-2) + '/'
			+ date.getFullYear() + ' ' + ('0' + date.getHours()).slice(-2)
			+ ':' + ('0' + date.getMinutes()).slice(-2);
	return formattedDate;

};
