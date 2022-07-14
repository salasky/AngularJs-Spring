package com.example.testproject1.exeption;

/**
 * @author smigranov
 * @version 1.0
 * При создании документа с существующим рег.номером выбрасывается исключение DocumentExistsException
 */
public class DocumentExistsException  extends Exception{
    /**
     * Рег.номер созданного документа,который уже существует у другого документа
     */
    private Long detail;
    public DocumentExistsException(Long detail,String message) {
        super(message);
        this.detail=detail;
    }

    @Override
    public String toString() {
        return "DocumentExistsException{" +
                "RegNumber= " + detail +" exist"+
                '}';
    }
}
