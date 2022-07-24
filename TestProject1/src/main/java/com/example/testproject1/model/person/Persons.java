package com.example.testproject1.model.person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательный класс для маршалинга класса {@link Person}
 *
 * @author smigranov
 */
@XmlRootElement
public class Persons {
    /**
     * Хранит список {@link Person}
     */
    @XmlElement(name = "person")
    private List<Person> list=new ArrayList<>();

    public Persons() {
    }

    public void setList(List<Person> list) {
        this.list = list;
    }

    /**
     * Метод получения списка Person
     */
    public List<Person> getPersonList() {
        return list;
    }
    /**
     * Метод добавления в список Person
     */
    public boolean add(Person person){
        return list.add(person);
    }
}
