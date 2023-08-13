package org.example.models;

public class Service {
    private int id;
    private String name;
    private int duration;

    public Service(int id, String name, int duration){
        this.id = id;
        this.name = name;
        this.duration = duration;
    }
    public Service(String name, int duration){
        this.name = name;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
