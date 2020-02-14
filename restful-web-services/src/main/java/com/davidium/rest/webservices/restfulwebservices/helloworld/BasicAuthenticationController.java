package com.davidium.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


//@RestController
//@CrossOrigin(origins="http://localhost:4200")
public class BasicAuthenticationController {

	@GetMapping(path = "/basicauth")
	public AuthenticationBean helloWorld() {
		System.out.println("Authentication Request Received");
		return new AuthenticationBean( "You are Authenticated");
	}

	
	/*
	 * // hello-world-bean // @RequestMapping(path="hello-world-bean", produces=
	 * {"application/xml"})
	 * 
	 * @GetMapping(path = "hello-world-bean") public AuthenticationBean
	 * helloWorldBean() { //throw new RuntimeException("Some Error has occured");
	 * return new AuthenticationBean("Hello World"); }
	 * 
	 * //localhost:8020/hello-world/variable/{name}
	 * 
	 * @RequestMapping(value = "hello-world/variable/{name}")
	 * 
	 * @ResponseBody public AuthenticationBean getVariable(@PathVariable String
	 * name){ return new AuthenticationBean(String.format("Hello %s.", name)); }
	 * 
	 * @RequestMapping(value = "/prod", produces = {"application/JSON"})
	 * 
	 * @ResponseBody public AuthenticationBean getProduces(){ return new
	 * AuthenticationBean("Hello Production attribute"); }
	 * 
	 * @RequestMapping(value = "/cons", produces = {"application/XML",
	 * "application/JSON"}) public AuthenticationBean getConsumes(){ return new
	 * AuthenticationBean("Hello Consumption attribute"); }
	 * 
	 */
	
}
