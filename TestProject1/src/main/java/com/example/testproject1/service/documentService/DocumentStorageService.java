package com.example.testproject1.service.documentService;

import com.example.testproject1.exception.DocumentExistsException;
import com.example.testproject1.model.document.BaseDocument;

import java.util.List;

/**
 * Интерфейс сохранения объектов BaseDocument созданных с помощью Builder-ов
 *
 * @author smigranov
 */
public interface DocumentStorageService {
    /**
     * Метод получения сохраненных документов
     * @return объект {@link List} содержащий объекты класса {@link BaseDocument}
     */
    List<BaseDocument> getAll();

    /**
     * Метод добавления документов в базу
     * @param baseDocumentList объект класса {@link BaseDocument}
     */
    void addAll(BaseDocument baseDocumentList);

    /**
     * Метод проверки существования документа с указанным рег.номером
     * @param baseDocument объект добовляемого класса {@link BaseDocument}
     * @return возращает true если есть документ с данным рег.номером, иначе false
     * @throws DocumentExistsException
     */
    boolean existByRegNumber(BaseDocument baseDocument);
}
