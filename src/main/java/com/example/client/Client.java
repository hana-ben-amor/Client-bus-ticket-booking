
package com.example.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Client {
    private String host;
    private int port;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Client(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        socket = new Socket(host, port);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    public List<Schedule> searchSchedule(String source, String destination) throws IOException, ClassNotFoundException {

        // Send the SEARCH_SCHEDULE request to the server
        output.writeObject("SEARCH_SCHEDULE");

        // Send the source and destination strings to the server
        output.writeObject(source);
        output.writeObject(destination);

        // Receive the schedules from the server
        List<Schedule> schedules = (List<Schedule>) input.readObject();
        return schedules;
    }

    public List<Trip> searchTrip(int scheduleId,int busId) throws IOException, ClassNotFoundException {

        output.writeObject("GET_TRIP_DETAILS");

        // Send the source and destination strings to the server
        output.writeObject(scheduleId);
        output.writeObject(busId);

        // Receive the schedules from the server
        List<Trip> trip= (List<Trip>) input.readObject();
        return trip;

    }

    public void close() throws IOException {
        output.close();
        input.close();
        socket.close();
    }
}
