package com.example.minitest2.repository;

import com.example.minitest2.model.entity.Tasks;
import org.springframework.data.repository.CrudRepository;

public interface ITaskRepository extends CrudRepository<Tasks, Long> {
}
