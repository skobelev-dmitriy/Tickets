package ru.adventurers.pildarok.models;

/**
 * Created by Дмитрий on 09.02.2016.
 */
public class Item {
    private String code;
    private String name;
    private int count;


    public Item(String code, String name, int count) {
        this.code = code;
        this.name = name;
        this.count=count;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
