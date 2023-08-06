package org.example.models;

public class Employee {
    private int id;
    private String name;
    private Integer percentage;

    public Employee(String name, Integer percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public Employee(String name, Integer percentage, int id) {
        this.name = name;
        this.percentage = percentage;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
