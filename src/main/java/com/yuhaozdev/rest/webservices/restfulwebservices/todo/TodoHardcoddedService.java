package com.yuhaozdev.rest.webservices.restfulwebservices.todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoHardcoddedService {

    private static List<Todo> todos = new ArrayList<>();
    private static Long idCounter = 0l;

    static {
        todos.add(new Todo(++idCounter, "Andy", "Learn to code", new Date(), false));
        todos.add(new Todo(++idCounter, "Andy", "Learn JAVA", new Date(), false));
        todos.add(new Todo(++idCounter, "Andy", "Learn REACT", new Date(), false));
    }

    public  List<Todo> findAll(){
        return todos;
    }

    public Todo save(Todo todo){
        if(todo.getId() == -1 || todo.getId() == 0){
            todo.setId(++idCounter);
            todos.add(todo);
        }else {
            deleteById(todo.getId());
            todos.add(todo);
        }
        return todo;
    }

    public Todo deleteById(long id){
        Todo todo = findById(id);

        if(todo == null){
            return null;
        }
        todos.remove(todo);

        return todo;
    }

    public Todo findById(long id){
        for(Todo todo : todos){
            if(todo.getId() == id)
                return todo;
        }
        return null;
    }
}
