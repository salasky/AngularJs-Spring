package com.example.testproject1.model;

import com.example.testproject1.service.visitorPatternRelase.DocumentInspector;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Класc входящих документов. Наследник {@link BaseDocument}
 *
 * @author smigranov
 */
public class IncomingDocument extends BaseDocument {
    /**
     * отправитель
     */
    private String sender;
    /**
     * адресат
     */
    private String destination;
    /**
     * исходящий номер
     */
    private Long documentNumber;
    /**
     * исходящая дата регистрации
     */
    private Date date;

    public IncomingDocument() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Long getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Long documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String toString() {
        Object[] taskArgs = {super.toString(), sender, destination, documentNumber, date};
        MessageFormat form = new MessageFormat(
                "Входящий документ {0}, incomingDocumentSender= {1}, incomingDocumentDestination= {2}, incomingDocumentNumber= {3}, incomingDocumentDate= {4}");
        return form.format(taskArgs);
    }

    /**
     * {@inheritDoc}
     *
     * @param o Объект для сравнивания
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IncomingDocument that = (IncomingDocument) o;
        return Objects.equals(sender, that.sender) && Objects.equals(destination, that.destination) && Objects.equals(documentNumber, that.documentNumber) && Objects.equals(date, that.date);
    }

    @Override
    public String accept(DocumentInspector documentInspector) {
        return documentInspector.visit(this);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sender, destination, documentNumber, date);
    }

    /**
     * @return возвращает объект builder
     */
    public static IncomingDocumentBuilder newBuilder() {
        return new IncomingDocument().new IncomingDocumentBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class IncomingDocumentBuilder extends BaseDocumentBuilder {
        private IncomingDocumentBuilder() {
            // private constructor
        }

        public IncomingDocumentBuilder setIncomingSender(String sender) {
            IncomingDocument.this.sender = sender;
            return this;
        }

        public IncomingDocumentBuilder setIncomingDestination(String destination) {
            IncomingDocument.this.destination = destination;
            return this;
        }

        public IncomingDocumentBuilder setIncomingDocumentNumber(Long documentNumber) {
            IncomingDocument.this.documentNumber = documentNumber;
            return this;
        }

        public IncomingDocumentBuilder setIncomingDocumentDate(Date date) {
            IncomingDocument.this.date = date;
            return this;
        }

        public IncomingDocument build() {
            return IncomingDocument.this;
        }
    }
}
