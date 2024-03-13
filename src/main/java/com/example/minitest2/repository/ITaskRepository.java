package com.example.minitest2.repository;

import com.example.minitest2.model.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskRepository extends CrudRepository<Task, Long> {
}
