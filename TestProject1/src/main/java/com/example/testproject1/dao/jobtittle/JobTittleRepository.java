package com.example.testproject1.dao.jobtittle;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.mapper.staff.JobTittleMapper;
import com.example.testproject1.model.staff.JobTittle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.JOB_TITTLE_CREATE_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.JOB_TITTLE_DELETE_ALL_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.JOB_TITTLE_DELETE_BY_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.JOB_TITTLE_GET_ALL_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.JOB_TITTLE_GET_BY_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.JOB_TITTLE_UPDATE_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.ORGANIZATION_CREATE_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("JobTittleRepository")
public class JobTittleRepository implements CrudRepository<JobTittle> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTittleRepository.class);
    /**
     * Маппер для извлечения {@link JobTittle}
     */
    @Autowired
    private JobTittleMapper jobTittleMapper;
    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobTittle> getAll() {
        return jdbcTemplate.query(JOB_TITTLE_GET_ALL_QUERY, jobTittleMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<JobTittle> getById(UUID uuid) {
        return jdbcTemplate.query(JOB_TITTLE_GET_BY_ID_QUERY, jobTittleMapper, uuid.toString()).stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobTittle create(JobTittle jobTittle) {
        if (jobTittle == null) {
            throw new DocflowRuntimeApplicationException("JobTittle не может быть null");
        }
        jdbcTemplate.update(JOB_TITTLE_CREATE_QUERY, jobTittle.getUuid().toString(), jobTittle.getName());
        return jobTittle;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public JobTittle update(JobTittle jobTittle) {
        if (jobTittle == null) {
            throw new DocflowRuntimeApplicationException("JobTittle не может быть null");
        }
        jdbcTemplate.update(JOB_TITTLE_UPDATE_ID_QUERY, jobTittle.getName(), jobTittle.getUuid().toString());
        return jobTittle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        jdbcTemplate.update(JOB_TITTLE_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(UUID id) {
        jdbcTemplate.update(JOB_TITTLE_DELETE_BY_ID_QUERY, id.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(JOB_TITTLE_GET_BY_ID_QUERY, jobTittleMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }

    @Override
    public void saveAll(List<JobTittle> entityList) {
        jdbcTemplate.batchUpdate(JOB_TITTLE_CREATE_QUERY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, entityList.get(i).getUuid().toString());
                ps.setString(2, entityList.get(i).getName());
            }
            @Override
            public int getBatchSize() {
                return entityList.size();
            }
        });
    }
}
