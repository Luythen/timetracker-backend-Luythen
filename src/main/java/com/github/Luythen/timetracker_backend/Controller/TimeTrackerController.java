package com.github.Luythen.timetracker_backend.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.github.Luythen.timetracker_backend.Model.TimeTrackerModel;
import com.github.Luythen.timetracker_backend.Model.UserModel;
import com.github.Luythen.timetracker_backend.Service.TimeTrackerService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;



@RestController
@RequestMapping("/timetracker")
public class TimeTrackerController {

    private TimeTrackerService timeTrackerService;

    public TimeTrackerController (TimeTrackerService timeTrackerService) {
        this.timeTrackerService = timeTrackerService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TimeTrackerModel timeTrackerModel, @AuthenticationPrincipal UserModel userModel) {
        timeTrackerModel.setStartDate(LocalDateTime.now(ZoneId.of("GMT+2")));
        try {
            return ResponseEntity.ok(timeTrackerService.start(timeTrackerModel, userModel));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/stop/{id}")
    public String stop(@PathVariable String id) {
        timeTrackerService.stop(id, LocalDateTime.now(ZoneId.of("GMT+2")));
        
        return "{'message': 'Timer have been stop for the id: "+ id +" '}";
    }
    
    @GetMapping("/list")
    public List<TimeTrackerModel> getListByUserID (@AuthenticationPrincipal UserModel userModel) {
        return timeTrackerService.getUserList(userModel);
    }

    @PatchMapping("/update")
    public String updateCategory (@RequestBody TimeTrackerModel timeTrackerModel) {
        try {
            timeTrackerService.updateCategory(timeTrackerModel);
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    

}
