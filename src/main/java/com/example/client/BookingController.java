package com.example.client;
import com.example.client.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BookingController {
    private List<Trip> trips;
    private Trip trip;

    @FXML
    private Label sourceLabel;
    @FXML
    private Label destinationLabel;
    @FXML
    private Label timingLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label availableSeatsLabel;

    @FXML
    private Label dateLabel;
    public void initialize() {
        // Check that the trip instance variable is not null
        if (this.trip != null) {
            // Set the values of the JavaFX controls to the corresponding properties of the selected trip
            sourceLabel.setText(this.trip.getSource());
            destinationLabel.setText(this.trip.getDestination());
            timingLabel.setText(this.trip.getTiming());
            priceLabel.setText(String.valueOf(this.trip.getPrice()));
            availableSeatsLabel.setText(String.valueOf(this.trip.getAvailableSeats()));
            dateLabel.setText(this.trip.getDate());
        } else {
            System.out.println("Trip object is null.");
        }
    }

    public void setTripData(List<Trip> trips) {
        this.trips = trips;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    @FXML
    Button book;
    @FXML
    void loadForm(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bookingform-view.fxml"));

        // Load the FXML file and get the root node
        Parent root = loader.load();

        // Create a new stage for the booking form
        Stage bookingFormStage = new Stage();
        bookingFormStage.setTitle("Booking Form");
        bookingFormStage.setScene(new Scene(root, 900, 700));
        bookingFormStage.show();

        // Hide the current window
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.hide();
}}