package com.github.Luythen.timetracker_backend.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import com.github.Luythen.timetracker_backend.Model.CategoryModel;
import com.github.Luythen.timetracker_backend.Model.UserModel;

public interface CategoryRepository extends MongoRepository<CategoryModel, String> {
    List<CategoryModel> findAllByUserModel (UserModel userModel);

    @Update("{'$set': {'name': ?1}}")
    public void findAndSetNameById (String id, String newName);
}
