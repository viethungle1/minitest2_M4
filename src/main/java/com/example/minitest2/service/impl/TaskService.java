package com.example.minitest2.service.impl;

import com.example.minitest2.model.entity.Task;
import com.example.minitest2.repository.ITaskRepository;
import com.example.minitest2.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        taskRepository.deleteById(id);
    }

}
