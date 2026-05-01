package com.github.Luythen.timetracker_backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.Luythen.timetracker_backend.Dto.CategoryDto;
import com.github.Luythen.timetracker_backend.Model.CategoryModel;
import com.github.Luythen.timetracker_backend.Model.UserModel;
import com.github.Luythen.timetracker_backend.Service.CategoryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public CategoryModel create(@RequestBody CategoryDto categoryDto, @AuthenticationPrincipal UserModel userModel) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(categoryDto.getName());
        categoryModel.setUserModel(userModel);

        return categoryService.createCategory(categoryModel);
    }

    @GetMapping("/{id}")
    public CategoryModel findCategoryById(@PathVariable String id) {
        return categoryService.findCategoryById(id).get();
    }

    @GetMapping("/all")
    public List<CategoryModel> getAllCategoryByUserID(@AuthenticationPrincipal UserModel userModel) {
        return categoryService.getAllCategorysByUserID(userModel);
    }
    
    @PutMapping("update/{id}")
    public String update(@PathVariable String id, @RequestBody String entity) {
        categoryService.updateCategoryName(id, entity);
        return "{'message': 'Category ("+ id +") name have been update to "+ entity +"'}";
    }
    

}
