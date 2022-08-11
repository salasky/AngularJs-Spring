package com.example.testproject1.service.dbservice.organization;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link  Organization} к базе данных .
 *
 * @author smigranov
 */
@Transactional
@Service("OrganizationService")
@Order(value = 1)
public class OrganizationService implements CrudService<Organization> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationService.class);
    /**
     * Бин {@link CrudRepository}
     */
    @Autowired
    private CrudRepository<Organization> organizationRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Organization create(Organization organization) throws DocflowRuntimeApplicationException {
        Organization organizationDB = organizationRepository.create(organization);
        if (organizationDB != null) {
            LOGGER.info("Organization успешно сохранен");
            return organizationDB;
        }
        throw new DocflowRuntimeApplicationException("Неудачная попытка сохранения Organization");
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<Organization> getall() {
        LOGGER.info("Попытка выдачи всех Organization");
        return organizationRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Organization> getById(String id) {
        LOGGER.info("Попытка получить Organization по id");
        return organizationRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization update(Organization organization) throws DocflowRuntimeApplicationException {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Organization с id {0}", organization.getId().toString()));
        int updateCount = organizationRepository.update(organization);
        if (updateCount == 1) {
            LOGGER.info("Organization успешно обновлен");
            return organization;
        }
        throw new DocflowRuntimeApplicationException("Неудачная попытка обновления Organization");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info("Попытка удаления записей из таблицы Organization");
        organizationRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) throws DocflowRuntimeApplicationException {
        try {
            organizationRepository.deleteById(id);
        } catch (DeleteByIdException e) {
            throw new DocflowRuntimeApplicationException("Запись из таблицы Organization не удалена");
        }
    }
}
