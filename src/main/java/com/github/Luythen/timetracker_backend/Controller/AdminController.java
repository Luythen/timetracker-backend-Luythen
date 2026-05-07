package com.github.Luythen.timetracker_backend.Controller;

import com.github.Luythen.timetracker_backend.Repository.CategoryRepository;
import com.github.Luythen.timetracker_backend.Repository.TimeTrackerRepository;
import com.github.Luythen.timetracker_backend.Repository.UserRepository;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.Luythen.timetracker_backend.Model.CategoryModel;
import com.github.Luythen.timetracker_backend.Model.TimeTrackerModel;
import com.github.Luythen.timetracker_backend.Model.UserModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/admin")
@PreAuthorize("TM_Admin")
public class AdminController {
    

    private final CategoryRepository categoryRepository;
    private final TimeTrackerRepository timeTrackerRepository;
    private final UserRepository userRepository;

    AdminController(UserRepository userRepository, TimeTrackerRepository timeTrackerRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.timeTrackerRepository = timeTrackerRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/users")
    public List<UserModel> getAllUsers () {
        return userRepository.findAll();
    }

    @GetMapping("/timetracker/{id}")
    public List<TimeTrackerModel> getAllUserTimers (@PathVariable String id) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        return timeTrackerRepository.findAllByUserModel(userModel);
    }

    @GetMapping("/categorys/{id}")
    public List<CategoryModel> getAllUserCategorys (@PathVariable String id) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        return categoryRepository.findAllByUserModel(userModel);
    }
    

}
