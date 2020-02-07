package com.davidium.rest.webservices.restfulwebservices.helloworld;

public class AuthenticationBean {
String message;

public AuthenticationBean(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}


}
