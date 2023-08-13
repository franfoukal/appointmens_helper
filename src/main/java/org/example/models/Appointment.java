package org.example.models;

import java.util.Date;

public class Appointment {

private int id;
private int employeeId;
private Date date;
private String clientName;
private int serviceId;
private Double payment;


    public Appointment(int id, int employeeId, Date date, String clientName, int serviceIdd, Double payment) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.clientName = clientName;
        this.serviceId = serviceId;
        this.payment = payment;
    }

    public Appointment(int employeeId, Date date, String clientName, int serviceId, Double payment) {
        this.employeeId = employeeId;
        this.date = date;
        this.clientName = clientName;
        this.serviceId = serviceId;
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

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }
}

