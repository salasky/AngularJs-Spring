package com.example.testproject1.dao.department.mapper;

import com.example.testproject1.dao.organization.mapper.OrganizationMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
/**
 * Маппер для класса {@link Department}
 *
 * @author smigranov
 */
@Component
public class DepartmentMapper implements RowMapper<Department> {
    /**
     * Бин маппер {@link Organization}
     */
    @Autowired
    private OrganizationMapper organizationMapper;
    /**
     * Название столбца для мапинаг
     */
    private final String DEPARTMENT_ID="department_id";
    /**
     * Название столбца для мапинага в поле id
     */
    private final String DEPARTMENT_FULL_NAME="department_full_name";
    /**
     * Название столбца для мапинага в поле short_name
     */
    private final String DEPARTMENT_SHORT_NAME="department_short_name";
    /**
     * Название столбца для мапинага в поле supervisor
     */
    private final String DEPARTMENT_SUPERVISOR="department_supervisor";
    /**
     * Название столбца для мапинага в поле contact_number
     */
    private final String DEPARTMENT_CONTACT_NUMBER="department_contact_number";
    /**
     * Название столбца для мапинага в поле id
     */
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setId(UUID.fromString(rs.getString(DEPARTMENT_ID)));
        department.setFullName(rs.getString(DEPARTMENT_FULL_NAME));
        department.setShortName(rs.getString(DEPARTMENT_SHORT_NAME));
        department.setSupervisor(rs.getString(DEPARTMENT_SUPERVISOR));
        department.setContactNumber(rs.getString(DEPARTMENT_CONTACT_NUMBER));
        Organization organization = organizationMapper.mapRow(rs, rowNum);
        department.setOrganization(organization);
        return department;
    }
}
