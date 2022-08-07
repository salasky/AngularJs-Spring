package com.example.testproject1.service.dbservice.jobtittleservice;

import com.example.testproject1.model.staff.JobTittle;

import java.util.List;
import java.util.Optional;

public interface JobTittleService {
    Optional<JobTittle> create(JobTittle jobTittle);

    List<JobTittle> getall();

    Optional<JobTittle> getById(String id);

    Optional<JobTittle> update(JobTittle jobTittle);

    void deleteAll();

    void deleteById(String id);
}
