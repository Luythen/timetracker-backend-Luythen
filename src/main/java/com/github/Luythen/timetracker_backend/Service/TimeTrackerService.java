package com.github.Luythen.timetracker_backend.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.Luythen.timetracker_backend.Model.TimeTrackerModel;
import com.github.Luythen.timetracker_backend.Model.UserModel;
import com.github.Luythen.timetracker_backend.Repository.TimeTrackerRepository;

@Service
public class TimeTrackerService {

    private final TimeTrackerRepository timeTrackerRepository;

    public TimeTrackerService (TimeTrackerRepository timeTrackerRepository) {
        this.timeTrackerRepository = timeTrackerRepository;
    }

    public TimeTrackerModel start (TimeTrackerModel timeTrackerModel) {
        return timeTrackerRepository.insert(timeTrackerModel);
    }

    public void stop (String timeTrackerID, LocalDateTime stopDate) {
        timeTrackerRepository.findAndSetStopDateById(timeTrackerID, stopDate);
    }

    public Optional<TimeTrackerModel> findById (String id) {
        return timeTrackerRepository.findById(id);
    }

    public List<TimeTrackerModel> getUserList (UserModel userModel) {
        return timeTrackerRepository.findAllByUserModel(userModel);
    }

    public List<TimeTrackerModel> getList () {
        return timeTrackerRepository.findAll();
    }
}
