package com.example.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class SearchController {
    private Socket socket;



    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search-view.fxml"));
    @FXML
    private TableView<Schedule> scheduleTable;
    @FXML
    private TableColumn<Schedule, String> sourceColumn;
    @FXML
    private TableColumn<Schedule, String> destinationColumn;
    @FXML
    private TableColumn<Schedule, String> departureTimeColumn;
    @FXML
    private TableColumn<Schedule, String> idColumn;
    @FXML
    private TableColumn<Schedule, String> busIdColumn;
    @FXML
    private TableColumn<Schedule, String> dateColumn;
    @FXML
    private TextField sourceField;
    @FXML
    private TextField destinationField;
    private MouseEvent event;


    @FXML
    private void handleSearch(ActionEvent event) throws IOException, ClassNotFoundException {
        String source = sourceField.getText();
        String destination = destinationField.getText();

 if (sourceField.getText().isEmpty() || destinationField.getText().isEmpty()){
     Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter the source or destination", ButtonType.OK);
     alert.getDialogPane().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
     alert.getDialogPane().getStyleClass().add("alert");
     alert.showAndWait();
 }
        List<Schedule> schedules = searchSchedules(source, destination);

        if (schedules != null) {
            // Add schedules to the table view
            updateScheduleTable(schedules);
        } else {
            // Show error message
            System.out.println("cannot find data");
        }
    }


    ObjectInputStream in;
    ObjectOutputStream out;
    public void updateScheduleTable(List<Schedule> schedules) {
        ObservableList<Schedule> data = FXCollections.observableArrayList(schedules);
        scheduleTable.setItems(data);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        sourceColumn.setCellValueFactory(new PropertyValueFactory<>("source"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        busIdColumn.setCellValueFactory(new PropertyValueFactory<>("busId"));
    }
    
    
    
    //  Method to send request to the server to search the schedules for a specific source and destination


    public List<Schedule> searchSchedules(String source, String destination) {
        try (
                Socket socket=new Socket("127.0.0.1",5000);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            // Send the search request to the server
            out.writeObject("SEARCH_SCHEDULE");
            out.writeObject(source);
            out.writeObject(destination);
            // Read the response from the server
            List<Schedule> schedules = (List<Schedule>) in.readObject();

            return schedules;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //  Method to send request to the server to search the trips for a specific source and destination


    public List<Trip> searchTrips(Integer scheduleId,Integer busId) {
        try (
                Socket socket=new Socket("127.0.0.1",5000);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            // Send the search request to the server
            out.writeObject("GET_TRIP_DETAILS");
            out.writeObject(scheduleId);
            out.writeObject(busId);

            // Read the response from the server
            List<Trip> trips= (List<Trip>) in.readObject();

            return trips;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }




    @FXML
    private void handleRowClick(MouseEvent event) throws IOException, ClassNotFoundException {
        TableView<Schedule> table = (TableView<Schedule>) event.getSource();
        Schedule selectedSchedule = table.getSelectionModel().getSelectedItem();
        int scheduleId = selectedSchedule.getScheduleId();
        int busId = selectedSchedule.getBusId();
//the response delivered from the server after sending thr request
        List<Trip> trips = searchTrips(scheduleId,busId);

        if (trips != null) {
            // Load the Booking view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("booking-view.fxml"));
            Parent bookingParent = loader.load();

            // Get the BookingController instance and set the trip data
            BookingController bookingController = loader.getController();
            bookingController.setTripData(trips);

            // Set the selected trip
            bookingController.setTrip(trips.get(0));

            bookingController.initialize();

            // Show the Booking view
            Stage bookingStage = new Stage();
            bookingStage.setScene(new Scene(bookingParent));
            bookingStage.show();

        } else {
            // Show error message
            System.out.println("cannot find data");
        }

    }







    @FXML
    private void handleSwitch(ActionEvent event) {
        String temp = sourceField.getText();
        sourceField.setText(destinationField.getText());
        destinationField.setText(temp);
    }

}
