package com.example.client;

public class Reservation {
    private int id;
    private String name;
    private String phone;
    private String source;
    private String destination;
    private String date;
    private String timing;
    private int seat;

    public Reservation(int id, String name, String phone, String source, String destination, String date, String timing, int seat) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.timing = timing;
        this.seat = seat;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
