package com.example.testproject1.service.DocBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.shell.TaskDocumentShell;
import org.springframework.stereotype.Component;

@Component
public class OutgoingBuilderImpl implements OutgoingBuilder{
    private String documentName;
    private String documentText;
    private Long documentRegNumber;
    private String documentData;
    private String documentAuthor;
    private String outgoingDocumentSender;
    private String outgoingDocumentDeliveryType;


    @Override
    public OutgoingBuilder fixDocumentName() {
        this.documentName=TaskBuilderImpl.docNameOutgoingList.get((int) ( Math.random() * 5 )) ;
        return this;
    }

    @Override
    public OutgoingBuilder fixDocumentText() {
        this.documentText=TaskBuilderImpl.docTextList.get((int) ( Math.random() * 5 )) ;
        return this;
    }

    @Override
    public OutgoingBuilder fixDocumentRegNumber() throws DocumentExistsException {
        var regNumber= Long.valueOf((int) (Math.random()*100)+200);//первые 100 номера для поручений, 100-200 для входящих,200-300 для исходящих

        for (BaseDocument t: TaskDocumentShell.documentList)
        {
            if (t.getDocumentRegNumber() == regNumber) {
                throw new DocumentExistsException(regNumber,"Document number "+regNumber+" exist");
            }
        }
        this.documentRegNumber=regNumber;
        return this;
    }

    @Override
    public OutgoingBuilder fixDocumentData() {
        this.documentData="2022-"+(int)(Math.random()*12+1)+"-"+(int)(Math.random()*29+1);
        return this;
    }

    @Override
    public OutgoingBuilder fixDocumentAuthor() {
        this.documentAuthor=TaskBuilderImpl.docAuthorList.get((int) ( Math.random() * 5 )) ;
        return this;
    }

    @Override
    public OutgoingBuilder fixOutgoingDocumentSender() {
        this.outgoingDocumentSender=TaskBuilderImpl.distinPersonList.get((int) ( Math.random() * 5 )) ;
        return this;
    }

    @Override
    public OutgoingBuilder fixOutgoingDocumentDeliveryType() {
        this.outgoingDocumentDeliveryType=TaskBuilderImpl.deliveryTypeList.get((int) ( Math.random() * 5 )) ;
        return this;
    }

    @Override
    public OutgoingDocument build() {
        var outgoingDock=new OutgoingDocument(documentName,documentText,documentRegNumber,documentData
                ,documentAuthor,outgoingDocumentSender,outgoingDocumentDeliveryType);
        TaskDocumentShell.documentList.add(outgoingDock);
        return outgoingDock;
    }
}
