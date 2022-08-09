package com.example.testproject1.dao.basedocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.mapper.document.BaseDocumentMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_CREATE_QUERY;
import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_DELETE_ALL_QUERY;
import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_DELETE_BY_ID_QUERY;
import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_EXIST_BY_REG_NUMBER_QUERY;
import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_GET_ALL_QUERY;
import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link BaseDocumentRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository
public class BaseDocumentRepositoryImpl implements BaseDocumentRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDocumentRepositoryImpl.class);
    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * Маппер для извлечения {@link BaseDocument}
     */
    @Autowired
    private BaseDocumentMapper baseDocumentMapper;
    /**
     * Сервис для работы с {@link Person}
     */
    @Autowired
    private CrudRepository<Person> personCrudRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseDocument create(BaseDocument baseDocument) {
        if (baseDocument != null) {
            if(personCrudRepository.existById(baseDocument.getAuthor().getId())){
                LOGGER.error(MessageFormat.format("Person с id {0} уже существует",baseDocument.getAuthor().getId().toString()));
            } else {
                personCrudRepository.create(baseDocument.getAuthor());
            }
            jdbcTemplate.update(BASE_DOCUMENT_CREATE_QUERY, baseDocument.getId().toString(), baseDocument.getName(), baseDocument.getText(),
                    baseDocument.getRegNumber(), baseDocument.getCreatingDate(), baseDocument.getAuthor().getId().toString());
            return baseDocument;
        } else throw new IllegalArgumentException("BaseDocument не может быть null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BaseDocument> getAll() {
        return jdbcTemplate.query(BASE_DOCUMENT_GET_ALL_QUERY, baseDocumentMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BaseDocument> getById(String id) {
        return jdbcTemplate.query(BASE_DOCUMENT_GET_BY_ID_QUERY, baseDocumentMapper, id).stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(BaseDocument baseDocument) {
        return jdbcTemplate.update(BASE_DOCUMENT_UPDATE_QUERY, baseDocument.getName(), baseDocument.getText(),
                baseDocument.getRegNumber(), baseDocument.getCreatingDate(), baseDocument.getAuthor().getId().toString(),
                baseDocument.getId().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        jdbcTemplate.update(BASE_DOCUMENT_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(String id){
        int deleteCount = jdbcTemplate.update(BASE_DOCUMENT_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new RuntimeException(
                MessageFormat.format("Ошибка удаления BaseDocument с id {0}",id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existByRegNumber(Long regNumber) {
        return jdbcTemplate.query(BASE_DOCUMENT_EXIST_BY_REG_NUMBER_QUERY, baseDocumentMapper, regNumber)
                .stream().findFirst().isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(BASE_DOCUMENT_GET_BY_ID_QUERY, baseDocumentMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }
}
