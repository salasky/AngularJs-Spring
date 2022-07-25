package com.example.testproject1.service.documents;

/**
 * Интерфейс генерации документов
 *
 * @author smigranov
 */
public interface GenerateDocumentService {
    /**
     * Метод генерации документов трех типов
     *
     * @param count Количество генерируемых документов
     */
    void generateDocument(Integer count);
}
