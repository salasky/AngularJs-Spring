package com.example.testproject1.service.randomizer;

import com.example.testproject1.model.enums.DocumentDeliveryType;
import com.example.testproject1.model.person.Person;
import com.example.testproject1.storage.PersonHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Класс для рандомной выдачи данных
 *
 * @author smigranov
 */
@Service
public class Randomizer {

    @Autowired
    private PersonHolder personHolder;

    @Value("${doc.documentName}")
    private List<String> newDocNameList;
    /**
     * Лист текстов поручений из application.yaml
     */
    @Value("${doc.documentText}")
    private List<String> newDocTextList;

    /**
     * Метод возврата UUID
     *
     * @return возвращает рандомный UUID
     */
    public UUID getRandUUID() {
        return UUID.randomUUID();
    }

    /**
     * Метод возврата рандомного названия документа
     *
     * @return возвращает рандомное название из application.yaml
     */
    public String getRandDocName() {
        return newDocNameList.get((int) (Math.random() * newDocNameList.size()));
    }

    /**
     * Метод возврата рандомного текста документа
     *
     * @return возвращает рандомный текст из application.yaml
     */
    public String getRandDocText() {
        return newDocTextList.get((int) (Math.random() * newDocTextList.size()));
    }

    /**
     * Метод возврата рандомного регистрационного номера
     *
     * @return возвращает рандомный рег.номер
     */
    public Long getRandDocumentRegNumber() {
        return Long.valueOf((int) (Math.random() * 100));
    }

    /**
     * Метод возврата даты
     *
     * @return возвращает рандомную дату в 2022 году
     */
    public Date getRandDocumentData() {
        Random rnd = new Random();
        Long ms = 1641027402000L + (Math.abs(rnd.nextLong()) % (1L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }

    /**
     * Метод возврата автора
     *
     * @return возвращает рандомного автора из XML файла
     */
    public Person getRandDocumentAuthor() {
        return personHolder.getPersonListList().get((int) (Math.random() * personHolder.getPersonListList().size()));
    }

    /**
     * Метод возварата текущей даты
     *
     * @return
     */
    public Date getRandTaskOutDate() {
        Date date = new Date();
        return date;
    }

    /**
     * Метод возврата рандомного количества дней в промежутке от 1 до 14
     *
     * @return
     */
    public String getRandTaskExecPeriod() {
        return ((int) (Math.random() * 14 + 1) + " дня");
    }

    /**
     * Метод возврата ответственного лица
     *
     * @return возвращает рандомного автора из xml файла
     */
    public Person getRandTaskResponsible() {
        return personHolder.getPersonListList().get((int) (Math.random() * personHolder.getPersonListList().size()));
    }

    /**
     * Метод рандомно выдает true или false.Отслеживается документ или нет.
     *
     * @return
     */
    public Boolean getTaskSignOfControl() {
        return Math.random() < 0.5;
    }

    /**
     * Метод возврата контролирующего человека
     *
     * @return возвращает рандомного контролирующего из XML
     */
    public Person getRandTaskControlPerson() {
        return personHolder.getPersonListList().get((int) (Math.random() * personHolder.getPersonListList().size()));
    }

    /**
     * Метод возвращает ранддомного отправителя из application.yaml
     *
     * @return
     */
    public Person getRandIncomingDocumentSender() {
        return personHolder.getPersonListList().get((int) (Math.random() * personHolder.getPersonListList().size()));
    }

    /**
     * Метод возврата рандомного получателя
     *
     * @return возвращает рандомного получателя из XML
     */
    public Person getIncomingDocumentDestination() {
        return personHolder.getPersonListList().get((int) (Math.random() * personHolder.getPersonListList().size()));
    }
    /**
     * Метод возвращает рандомный номер входящего документа
     *
     * @return
     */
    public Long getIncomingDocumentNumber() {
        return Long.valueOf((int) (Math.random() * 10000));

    }

    /**
     * Метод возвращает рандомную дату в 2022 году
     *
     * @return
     */
    public Date getRandIncomingDocumentDate() {
        var rnd = new Random();
        var ms = 1641027402000L + (Math.abs(rnd.nextLong()) % (1L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }

    /**
     * Метод возвращает рандомного адресата
     *
     * @return
     */
    public Person getRandOutgoingDocumentSender() {
        return personHolder.getPersonListList().get((int) (Math.random() * personHolder.getPersonListList().size()));
    }

    /**
     * Метод возвращает рандомный способ доставки из enum
     *
     * @return
     */
    public DocumentDeliveryType getRandOutgoingDocumentDeliveryType() {
        return DocumentDeliveryType.values()[new Random().nextInt(DocumentDeliveryType.values().length)];
    }
}
