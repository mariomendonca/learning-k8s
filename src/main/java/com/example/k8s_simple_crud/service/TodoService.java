package com.example.k8s_simple_crud.service;

import com.example.k8s_simple_crud.dto.TodoCreateDto;
import com.example.k8s_simple_crud.dto.TodoResponseDto;
import com.example.k8s_simple_crud.dto.TodoUpdateDto;
import com.example.k8s_simple_crud.entity.Todo;
import com.example.k8s_simple_crud.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoResponseDto> getAllTodos() {
        log.info("Fetching all todos");
        return todoRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public Optional<TodoResponseDto> getTodoById(Long id) {
        log.info("Fetching todo with id: {}", id);
        return todoRepository.findById(id)
                .map(this::convertToResponseDto);
    }

    public List<TodoResponseDto> getTodosByStatus(Boolean completed) {
        log.info("Fetching todos with completed status: {}", completed);
        return todoRepository.findByCompleted(completed)
                .stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public List<TodoResponseDto> searchTodos(String keyword) {
        log.info("Searching todos with keyword: {}", keyword);
        return todoRepository.findByKeyword(keyword)
                .stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public TodoResponseDto createTodo(TodoCreateDto createDto) {
        log.info("Creating new todo with title: {}", createDto.getTitle());
        Todo todo = new Todo();
        todo.setTitle(createDto.getTitle());
        todo.setDescription(createDto.getDescription());
        todo.setCompleted(false);

        Todo savedTodo = todoRepository.save(todo);
        log.info("Todo created with id: {}", savedTodo.getId());

        return convertToResponseDto(savedTodo);
    }

    public Optional<TodoResponseDto> updateTodo(Long id, TodoUpdateDto updateDto) {
        log.info("Updating todo with id: {}", id);
        return todoRepository.findById(id)
                .map(todo -> {
                    if (updateDto.getTitle() != null) {
                        todo.setTitle(updateDto.getTitle());
                    }
                    if (updateDto.getDescription() != null) {
                        todo.setDescription(updateDto.getDescription());
                    }
                    if (updateDto.getCompleted() != null) {
                        todo.setCompleted(updateDto.getCompleted());
                    }

                    Todo updatedTodo = todoRepository.save(todo);
                    log.info("Todo updated with id: {}", updatedTodo.getId());

                    return convertToResponseDto(updatedTodo);
                });
    }

    public boolean deleteTodo(Long id) {
        log.info("Deleting todo with id: {}", id);
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            log.info("Todo deleted with id: {}", id);
            return true;
        }
        log.warn("Todo not found with id: {}", id);
        return false;
    }

    public Optional<TodoResponseDto> toggleTodoStatus(Long id) {
        log.info("Toggling status for todo with id: {}", id);
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setCompleted(!todo.getCompleted());
                    Todo updatedTodo = todoRepository.save(todo);
                    log.info("Todo status toggled for id: {}", updatedTodo.getId());
                    return convertToResponseDto(updatedTodo);
                });
    }

    private TodoResponseDto convertToResponseDto(Todo todo) {
        return new TodoResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getCompleted(),
                todo.getCreatedAt(),
                todo.getUpdatedAt()
        );
    }
}
