package com.example.testproject1.service.facadeservice.taskdocument;

import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.dto.document.TaskDocumentDTO;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingutils.TaskDocumentMapperAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс реализующий интерфейс {@link CrudFacadeService}. Для выполнения CRUD операций к базе данных.
 *
 * @author smigranov
 */
@Service
public class TaskDocumentFacadeService implements CrudFacadeService<TaskDocumentDTO> {
    /**
     * Department сервис
     */
    @Autowired
    private CrudService<TaskDocument> taskDocumentCrudService;
    /**
     * Mapper DTO to Entity, Entity to DTO
     */
    @Autowired
    private TaskDocumentMapperAbstract mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocumentDTO create(TaskDocumentDTO entity) {
        TaskDocument taskDocument = mapper.dtoToTask(entity);
        return mapper.taskToDTO(taskDocumentCrudService.create(taskDocument));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskDocumentDTO> getAll() {
        List<TaskDocument> taskDocumentList = taskDocumentCrudService.getAll();
        return mapper.listToDtoList(taskDocumentList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TaskDocumentDTO> getById(UUID id) {
        Optional<TaskDocument> optionalTaskDocument = taskDocumentCrudService.getById(id);
        if (optionalTaskDocument.isPresent()) {
            return Optional.ofNullable(mapper.taskToDTO(optionalTaskDocument.get()));
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocumentDTO update(TaskDocumentDTO entity) {
        TaskDocument taskDocument = mapper.dtoToTask(entity);
        return mapper.taskToDTO(taskDocumentCrudService.update(taskDocument));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<TaskDocumentDTO> entityList) {
        taskDocumentCrudService.saveAll(mapper.dtoListToList(entityList));
    }
}
