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

//JPA version of the control will enable us to add CRUD requirements more easily. -Feb 14th

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoJpaResource {
	Log log = LogFactory.getLog(ToDoJpaResource.class.getName());
	// @Autowired
	private ToDoHardcodedService todoService;
	@Autowired
	private ToDoJpaRepository todoRepo;

	// @GetMapping(value = "/users/{username}/todos", produces = {
	// "application/JSON" })
	@GetMapping(value = "/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		log.info("Retrieving all todos in JPA Mode...");
		return todoRepo.findByUsername(username);
	}

	@DeleteMapping(value = "/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		todoRepo.deleteById(id);

		return ResponseEntity.noContent().build();

	}

	@PutMapping(value = "/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id,
			@RequestBody Todo todo) {

		Todo todoUpdate = todoRepo.save(todo);
		todoUpdate.setUsername(username);
		log.info("Todo #" + id + " Updated");
		return new ResponseEntity<Todo>(todoUpdate, HttpStatus.OK);
	}

	// when creating, we are most concerned with saving the URL
	// we'll pull this from the created todo using the ServletComponentBuilder
	@PostMapping(value = "/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
		todo.setUsername(username);
		Todo createdTodo = todoRepo.save(todo);
		log.info("~~~Todo #" + createdTodo.getId() + " Created with "+createdTodo.getUsername());
//createdTodo.setUsername(username);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId())
				.toUri();
		log.info("URI Path Created for New Todo: " + uri.toString());
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/jpa/users/{username}/todos/{id}")
	// @GetMapping(value = "/users/{username}/todos", produces = {
	// "application/JSON" })
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
		log.info("Retrieving all todos in JPA Mode for id: " + id + ".");
		return todoRepo.findById(id).get();
	}

//	@PutMapping(value = "/jpa/users/{username}/todos/{id}")
//	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id,
//			@RequestBody Todo todo) {
//		Todo todoUpdate = todoRepo.save(todo);
//		log.info("Todo #" +id + " Updated");
//		return new ResponseEntity<Todo>(todoUpdate, HttpStatus.OK);
//	}
//
//	

//
//	
//	@DeleteMapping(value = "/jpa/users/{username}/todos/{id}")
//	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
//		Todo todo = todoService.deleteById(id);
//		if (todo != null) {
//			//if found, ResponseEntity builds noContent in the place of what we found
//			return ResponseEntity.noContent().build();
//		}
//		//or maybe we just plain didn't find it.
//		return ResponseEntity.notFound().build();
//	}
}
