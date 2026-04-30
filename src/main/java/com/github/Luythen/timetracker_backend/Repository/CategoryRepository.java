package com.github.Luythen.timetracker_backend.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import com.github.Luythen.timetracker_backend.Model.CategoryModel;

public interface CategoryRepository extends MongoRepository<CategoryModel, String> {
    List<CategoryModel> findAllByUserID (String userID);

    @Update("{'set': {'name': ?1}}")
    public void findAndSetNameById (String id, String newName);
}
