package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.service.randomizer.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс фабрики для {@link com.example.testproject1.model.OutgoingDocument}
 *
 * @author smigranov
 */
@Service
public class OutgoingDocumentFactory extends DocumentFactory {



    /**
     * {@inheritDoc}
     *
     * @return Возвращает объект наследник класса BaseDocument
     */
    @Override
    public BaseDocument createDocument() {
        return OutgoingDocument.newBuilder()
                .setDocId(randomizer.getRandUUID())
                .setDocName(randomizer.getRandDocName())
                .setDocText(randomizer.getRandDocText())
                .setDocRegNumber(randomizer.getRandDocumentRegNumber())
                .setDocDate(randomizer.getRandDocumentData())
                .setDocAuthor(randomizer.getRandDocumentAuthor())
                .setDocSender(randomizer.getRandOutgoingDocumentSender())
                .setDocDeliveryType(randomizer.getRandOutgoingDocumentDeliveryType())
                .build();
    }
}
