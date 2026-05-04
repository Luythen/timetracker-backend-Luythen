package com.github.Luythen.timetracker_backend.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.github.Luythen.timetracker_backend.Model.TimeTrackerModel;
import com.github.Luythen.timetracker_backend.Model.UserModel;
import com.github.Luythen.timetracker_backend.Service.TimeTrackerService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/timetracker")
public class TimeTrackerController {

    private TimeTrackerService timeTrackerService;

    public TimeTrackerController (TimeTrackerService timeTrackerService) {
        this.timeTrackerService = timeTrackerService;
    }

    @PostMapping("/create")
    public TimeTrackerModel create(@RequestBody TimeTrackerModel timeTrackerModel) {
        timeTrackerModel.setStartDate(LocalDateTime.now());
        return timeTrackerService.start(timeTrackerModel);
    }

    @PostMapping("/stop/{id}")
    public String stop(@PathVariable String id) {
        timeTrackerService.stop(id, LocalDate.now());
        
        return "{'message': 'Timer have been stop for the id: "+ id +" '}";
    }
    
    @GetMapping("/list")
    public List<TimeTrackerModel> getListByUserID (@AuthenticationPrincipal UserModel userModel) {
        return timeTrackerService.getUserList(userModel);
    }
    

}
