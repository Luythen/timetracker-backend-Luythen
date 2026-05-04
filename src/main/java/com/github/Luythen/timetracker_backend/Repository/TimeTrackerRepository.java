package com.github.Luythen.timetracker_backend.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import com.github.Luythen.timetracker_backend.Model.TimeTrackerModel;
import com.github.Luythen.timetracker_backend.Model.UserModel;

public interface TimeTrackerRepository extends MongoRepository<TimeTrackerModel, String> {

    List<TimeTrackerModel> findAllByUserModel (UserModel userModel);

    @Update("{'$set': {'stopDate': ?1}}")
    public void findAndSetStopDateById (String id, LocalDateTime stopDate); 

}
