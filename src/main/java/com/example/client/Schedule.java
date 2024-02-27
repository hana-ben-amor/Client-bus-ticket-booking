package com.example.client;

import java.io.Serializable;

public class Schedule implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String source;
    private String destination;
    private String departureTime;
    private String date;
    private int busId;

    public Schedule(int id, String source, String destination, String departureTime, int busId) {
        this.id = id;
        this.source = source;
        this.destination = destination;

        this.departureTime = departureTime;
        this.busId = busId;
    }



    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getBusId() {
        return busId;
    }
    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", busId=" + busId +
                '}';
    }

    public int getScheduleId() {
        return id;
    }
}
