package com.davidium.rest.webservices.restfulwebservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ToDoHardcodedService {
	private static List<Todo> todos = new ArrayList();
	private static int idCounter = 0;

	static {

		todos.add(new Todo(++idCounter, "davidiumprime", "Master Angular and SpringMVC", new Date(), false));
		todos.add(new Todo(++idCounter, "davidiumprime", "Create a dating podcast", new Date(), false));
		todos.add(new Todo(++idCounter, "davidiumprime", "Take over the world", new Date(), false));
	}

	
	public Todo save(Todo todo) {
		//if the id isn't present...
		if(todo.getId()==-1 || todo.getId()==0) {
			todo.setId(++idCounter);
			todos.add(todo);
		}
		//else, if the id is present, delete the old and replace with new.
		else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	
	public List<Todo> findAll() {
		return todos;
	}

	public Todo deleteById(long id) {
		Todo todo = findById(id);
		if (todo == null)
			return null;
		if (todos.remove(todo)) {
			return todo;
		}
		return null;

	}

	public Todo findById(long id) {
		for (Todo todo : todos) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}

}
