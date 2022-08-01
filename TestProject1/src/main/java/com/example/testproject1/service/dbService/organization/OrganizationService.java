package com.example.testproject1.service.dbService.organization;

import com.example.testproject1.model.staff.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    Optional<Organization> create(Organization organization);
    List<Organization> getall();
    Optional<Organization> getById(String id);
    Optional<Organization> update(Organization organization);
    String deleteAll();
    String deleteById(String id);
}
