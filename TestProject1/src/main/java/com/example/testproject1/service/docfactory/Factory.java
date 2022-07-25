package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.BaseDocument;

/**
 * Интерфейс фабрик
 * @param <T>
 */
public interface Factory<T> {
    /**
     * Метод создания {@link com.example.testproject1.model.BaseDocument}
     *
     * @return объект {@link com.example.testproject1.model.BaseDocument}
     */
    T create();

}
