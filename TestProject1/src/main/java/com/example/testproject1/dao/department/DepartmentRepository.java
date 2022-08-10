package com.example.testproject1.dao.department;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.EntityExistInDataBaseException;
import com.example.testproject1.mapper.staff.DepartmentMapper;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_CREATE_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_DELETE_ALL_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_DELETE_BY_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_GET_ALL_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_UPDATE_QUERY;

/**
 * Класс репозиторий для {@link Department}. Реализует интерфейс {@link CrudRepository}
 *
 * @author smigranov
 */
@Repository("DepartmentRepository")
public class DepartmentRepository implements CrudRepository<Department> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRepository.class);
    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * Маппер для извлечения {@link Department}
     */
    @Autowired
    private DepartmentMapper departmentMapper;
    /**
     * Сервис для работы с {@link Organization}
     */
    @Autowired
    private CrudService<Organization> organizationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Department> getAll() {
        return jdbcTemplate.query(DEPARTMENT_GET_ALL_QUERY, departmentMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Department> getById(String id) {
        return jdbcTemplate.query(DEPARTMENT_GET_BY_ID_QUERY, departmentMapper, id).stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Department create(Department department) {
        if (department != null) {
                jdbcTemplate.update(DEPARTMENT_CREATE_QUERY, department.getId().toString()
                        , department.getFullName(), department.getShortName(), department.getSupervisor()
                        , department.getContactNumber(), department.getOrganization().getId().toString());
                return department;
        } else {
            throw new IllegalArgumentException("Department не может быть null");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Department department) {
        return jdbcTemplate.update(DEPARTMENT_UPDATE_QUERY, department.getFullName(), department.getShortName(),
                department.getSupervisor(), department.getContactNumber(), department.getOrganization().getId().toString(),
                department.getId().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        jdbcTemplate.update(DEPARTMENT_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(String id) throws DeleteByIdException {
        int deleteCount = jdbcTemplate.update(DEPARTMENT_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new DeleteByIdException("Department");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(DEPARTMENT_GET_BY_ID_QUERY, departmentMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }
}
