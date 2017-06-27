package ru.adventurers.pildarok.models;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Дмитрий on 20.04.2016.
 */
public class Error {

    @Expose
    private String level; //

    @Expose
    private String message;

    public Error() {
    }

    public Error(String level, String message) {
        this.level = level;
        this.message = message;

    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    /*public List<FormError> getForm_error() {
        return form_error;
    }*/

    public class FormError{
        @Expose
        String iErrCode;
        @Expose
        String message;
        public FormError() {
        }

        public String getiErrCode() {
            return iErrCode;
        }

        public String getMessage() {
            return message;
        }
    }
}
