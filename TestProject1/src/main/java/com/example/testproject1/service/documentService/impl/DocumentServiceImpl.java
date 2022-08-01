package com.example.testproject1.service.documentService.impl;

import com.example.testproject1.exception.DocumentExistsException;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.service.documentService.DocumentService;
import com.example.testproject1.service.documentService.DocumentStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Класс реализующий интерфейс {@link DocumentService}. Сохраняет переданные документы,
 * перед сохранением проверяет уникаль6ность рег.номера
 *
 * @author smigranov
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    /**
     * Объект класса для хранения документов
     */
    @Autowired
    private DocumentStorageService documentStorageService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(BaseDocument baseDocument) throws DocumentExistsException {
        if(documentStorageService.existByRegNumber(baseDocument)){
            throw new DocumentExistsException(baseDocument.getRegNumber(), MessageFormat.format("Document number {0} exist", baseDocument.getRegNumber()));
        }
        else {
            documentStorageService.addAll(baseDocument);
        }
    }
}