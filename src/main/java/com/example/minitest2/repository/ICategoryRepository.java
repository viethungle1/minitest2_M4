package com.example.minitest2.repository;

import com.example.minitest2.model.dto.TotalAmountOfCategory;
import com.example.minitest2.model.entity.Category;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ICategoryRepository extends CrudRepository<Category, Long> {
    @Query(nativeQuery = true, value = "select c.name, sum(t.amount) as totalAmountByCategory from category c left join task t on c.id = t.category_id group by c.name;")
    Iterable<TotalAmountOfCategory> sumAmountByCategory();
    @Modifying
    @Query(nativeQuery = true, value = "call deleteCategory(:id);")
    void setNullCategoryIdForTask(@Param("id") Long id);
}
