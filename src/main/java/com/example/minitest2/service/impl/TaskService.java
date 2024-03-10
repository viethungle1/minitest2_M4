package com.example.minitest2.service.impl;

import com.example.minitest2.model.entity.Tasks;
import com.example.minitest2.repository.ITaskRepository;
import com.example.minitest2.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public Iterable<Tasks> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Tasks task) {
        taskRepository.save(task);
    }

    @Override
    public Optional<Tasks> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        taskRepository.deleteById(id);
    }

}
