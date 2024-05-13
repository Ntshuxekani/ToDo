package com.todo.service;
import com.todo.entity.Todo;
import com.todo.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //Annotation used to specify a service
public class TodoServiceImpl implements TodoService {//implementing the method

    @Autowired // Because we want to use methods from DB we need to connect the
    private TodoRepo todoRepo;

    @Override //Overriding the methods we have in the service and reusing the
    public List<Todo> getAllTodo(){//Return type ia a list of todo
        return todoRepo.findAll();

    }


    @Override
    public void saveTodo(Todo todo){//Does not expect a return value
        this.todoRepo.save(todo);
    }


    @Override
    public Todo getTodoById(Long id){//Return type of todo is expected
        Optional<Todo> optional =  todoRepo.findById(id);
        Todo todo;
        if(optional.isPresent()){
            todo = optional.get();
        }else{
            throw new RuntimeException("Todo for the "+id+ "is not found ");
        }
        return todo;
    }
    @Override
    public void updateTodo(Long id, Todo todo) {
        Todo todoFromDb = todoRepo.findById(id).get();
        todoFromDb.setTaskName(todo.getTaskName());
        todoFromDb.setDesc(todo.getDesc());
        todoRepo.save(todoFromDb);
    }


    @Override
    public void deleteTodo(Long id) {
this.todoRepo.deleteById(id);
    }


}
