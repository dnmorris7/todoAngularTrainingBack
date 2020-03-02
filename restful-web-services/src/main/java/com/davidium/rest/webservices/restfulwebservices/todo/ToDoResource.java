package com.davidium.rest.webservices.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class ToDoResource {
	Log log = LogFactory.getLog(ToDoResource.class.getName());	
	@Autowired
	private ToDoHardcodedService todoService;

	//@GetMapping("/users/{username}/todos")
	 @GetMapping(value = "/users/{username}/todos", produces = {
	 "application/JSON" })
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoService.findAll();
	}

	
	
	@GetMapping("/users/{username}/todos/{id}")
	// @GetMapping(value = "/users/{username}/todos", produces = {
	// "application/JSON" })
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
		return todoService.findById(id);
	}

	@PutMapping(value = "/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id,
			@RequestBody Todo todo) {
		Todo todoUpdate = todoService.save(todo);
		log.info("Todo #" +id + " Updated");
		return new ResponseEntity<Todo>(todoUpdate, HttpStatus.OK);
	}

	
	//when creating, we are most concerned with saving the URL
	//we'll pull this from the created todo using the ServletComponentBuilder
	@PostMapping(value = "/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username,
			@RequestBody Todo todo) {
			Todo createdTodo = todoService.save(todo);
		
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		log.info("URI Path Created for New Todo: " + uri.toString());
		return ResponseEntity.created(uri).build();
	}

	
	@DeleteMapping(value = "/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		Todo todo = todoService.deleteById(id);
		if (todo != null) {
			//if found, ResponseEntity builds noContent in the place of what we found
			return ResponseEntity.noContent().build();
		}
		//or maybe we just plain didn't find it.
		return ResponseEntity.notFound().build();
	}
}
