package org.example.models;

import java.util.Date;

public class Appointment {

private int id;
private int employeeId;
private Date date;
private String clientName;
private String service;
private Double payment;


    public Appointment(int id, int employeeId, Date date, String clientName, String service, Double payment) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.clientName = clientName;
        this.service = service;
        this.payment = payment;
    }

    public Appointment(int employeeId, Date date, String clientName, String service, Double payment) {
        this.employeeId = employeeId;
        this.date = date;
        this.clientName = clientName;
        this.service = service;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }
}

