package com.github.Luythen.timetracker_backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.Luythen.timetracker_backend.Model.CategoryModel;
import com.github.Luythen.timetracker_backend.Model.UserModel;
import com.github.Luythen.timetracker_backend.Repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService (CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<CategoryModel> findCategoryById (String id) {
        return categoryRepository.findById(id);
    }

    public List<CategoryModel> getAllCategorysByUserID (UserModel userModel) {
        return categoryRepository.findAllByUserModel(userModel);
    }

    public void updateCategoryName (String id, String newName) {
        categoryRepository.findAndSetNameById(id, newName);
    }

    public CategoryModel createCategory (CategoryModel categoryModel) {
        return categoryRepository.insert(categoryModel);
    }

}
