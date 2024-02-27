package com.example.client;

import java.io.Serializable;

public class Trip implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int scheduleId;
    private int busId;
    private String source;
    private String destination;
    private String timing;
    private double price;
    private int availableSeats;
    private String date;

    public Trip(int scheduleId, int busId, String source, String destination, String timing, double price, int availableSeats, String date) {
        this.scheduleId = scheduleId;
        this.busId = busId;
        this.source = source;
        this.destination = destination;
        this.timing = timing;
        this.price = price;
        this.availableSeats = availableSeats;
        this.date=date;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
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
    public String getDate() {
        return date;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
