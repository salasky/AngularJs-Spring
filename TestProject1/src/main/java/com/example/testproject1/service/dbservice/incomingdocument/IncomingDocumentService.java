package com.example.testproject1.service.dbservice.incomingdocument;

import com.example.testproject1.model.document.IncomingDocument;

import java.util.List;
import java.util.Optional;

public interface IncomingDocumentService {
    Optional<IncomingDocument> create(IncomingDocument incomingDocument);

    List<IncomingDocument> getall();

    Optional<IncomingDocument> getById(String id);

    Optional<IncomingDocument> update(IncomingDocument incomingDocument);

    void deleteAll();

    void deleteById(String id);
}
