function createXHR() {
	var request = false;
	try {
		request = new ActiveXObject('Msxml2.XMLHTTP');
	}
	catch (err2) {
		try {
			request = new ActiveXObject('Microsoft.XMLHTTP');
		}
		catch (err3) {
			try {
				request = new XMLHttpRequest();
			}
			catch (err1) {
				request = false;
			}
		}
	}
	return request;
}

function initialize() {
	var xhr = createXHR();
	xhr.open("HEAD", "http://localhost:8080/hornetq-rest/topics/jms.topic.broadcast", true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				// getting the links from the rest resource
				sender = xhr.getResponseHeader("msg-create");
				subscriptions = xhr.getResponseHeader("msg-pull-subscriptions");

				// just adding the report
				document.getElementById("log-div").innerHTML = "Subscriptions URL: " + subscriptions;
			}
		}
	}

	// this will send the request from javascript
	xhr.send(null);
}

function receiveMessage() {
	var xhr = createXHR();
	if (reconnect)
	{
		document.getElementById("connection").innerHTML = "Trying to reconnect: " + subscriptions + " retries: " + count++;
		xhr.open("POST", subscriptions, true);
		xhr.onreadystatechange = function()
		{
			if (xhr.readyState == 4)
			{
				var status = xhr.status;
				if (status == 201)
				{
					nextMessage = xhr.getResponseHeader("msg-consume-next");
					document.getElementById("connection").innerHTML = "Connected to: " + nextMessage;
					count = 1;
					reconnect = false;
				}
				setTimeout("receiveMessage()", 800);
			}
		}
		xhr.send(null);
	}
	else
	{
		xhr.open("POST", nextMessage, true);
		xhr.setRequestHeader("Accept-Wait", "10")
		xhr.onreadystatechange = function()
		{
			if (xhr.readyState == 4)
			{
				var status = xhr.status;
				if (status == 200)
				{
					document.getElementById("next").innerHTML = xhr.responseText + "\n" + document.getElementById("next").innerHTML;
					nextMessage = xhr.getResponseHeader("msg-consume-next");
				}
				else
				{
					reconnect = true;
				}
				setTimeout("receiveMessage()", 800);
			}
		}
		xhr.send(null);
	}
}