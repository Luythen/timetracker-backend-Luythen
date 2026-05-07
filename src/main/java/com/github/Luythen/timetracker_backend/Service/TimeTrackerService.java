package com.github.Luythen.timetracker_backend.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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

    public TimeTrackerModel start (TimeTrackerModel timeTrackerModel, UserModel userModel) throws Exception {
        Optional<TimeTrackerModel> tModel = timeTrackerRepository.findOneByUserModelAndStopDate(userModel, null);
        if (tModel.isEmpty()) {
            return timeTrackerRepository.save(timeTrackerModel);
        }

        throw new Exception("Cant not have more then one timer active");
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

    public void updateCategory (TimeTrackerModel timeTrackerModel) throws Exception {
        try {
            timeTrackerRepository.findAndUpdateCategoryById(timeTrackerModel.getId(), new ObjectId(timeTrackerModel.getCategory().getId()));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
