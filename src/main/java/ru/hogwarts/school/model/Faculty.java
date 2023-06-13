package ru.hogwarts.school.model;

public class Faculty {

    public Faculty(String name, String color) {
        this.name = name;
        this.color = color;
    }

    private long id = 0;
    private String name;
    private String color;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
