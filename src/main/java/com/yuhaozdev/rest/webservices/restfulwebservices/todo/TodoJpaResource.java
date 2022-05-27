package com.yuhaozdev.rest.webservices.restfulwebservices.todo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoJpaResource {
    @Autowired
    private TodoHardcoddedService todoService;

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @GetMapping(path = "/jpa/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username){
        return todoJpaRepository.findByUsername(username);
    }

    @GetMapping(path = "/jpa/users/{username}/todos/{id}")
    public Todo getAllTodoById(@PathVariable String username, @PathVariable long id){
        return todoJpaRepository.findById(id).get();
    }

    @DeleteMapping(path = "/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
        todoJpaRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
        todo.setUsername(username);
        Todo updatedTodo = todoJpaRepository.save(todo);
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PostMapping(path = "/jpa/users/{username}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo){
        todo.setUsername(username);
        Todo updatedTodo = todoJpaRepository.save(todo);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedTodo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
