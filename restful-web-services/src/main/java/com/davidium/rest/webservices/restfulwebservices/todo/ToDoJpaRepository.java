package com.davidium.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoJpaRepository extends JpaRepository<Todo, Long> {
	List<Todo> findByUsername(String username);
	
	void deleteById(int id);

}
