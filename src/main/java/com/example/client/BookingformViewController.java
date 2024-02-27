package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class BookingformViewController {

    private Trip trip;
    private int availableSeats=8;
    @FXML
    public void bookButtonClicked(ActionEvent actionEvent) {
    }

    @FXML
    private ComboBox<String> seatCombo;
    private int selectedSeatNum;

    @FXML
    private Button bookButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    public void initialize() {
        for (int i = 1; i <= availableSeats; i++) {
            String seat = "Seat n°" + i;
            seatCombo.getItems().add(seat);
        }

// Set up an event handler for the combo box to remove a seat from the available seats list when it is selected
        seatCombo.setOnAction(event -> {
            String selectedSeat = seatCombo.getSelectionModel().getSelectedItem();
            if (selectedSeat != null) { // check if the selected item is not null
                System.out.println("ok");
            }
        });
        /*seatCombo.setOnAction(event -> {
     if (seatCombo.getItems().size() > 0) { // check if the ComboBox has any items
         String selectedSeat = seatCombo.getSelectionModel().getSelectedItem();
         if (selectedSeat != null) { // check if the selected item is not null
             int selectedSeatNum = Integer.parseInt(selectedSeat.substring(4)); // extract the seat number from the string
             availableSeats--; // decrement the available seats count
             seatCombo.getItems().remove(selectedSeat);
             seatCombo.getSelectionModel().clearSelection();
         }
     }
 });*/

        // Set up an event handler for the "Book Ticket" button to check if the selected seat is still available and book it if it is

    bookButton.setOnAction(event -> {
        String selectedSeat = seatCombo.getSelectionModel().getSelectedItem();
        if (selectedSeat == null ||nameField.getText().isEmpty()||phoneField.getText().isEmpty()) {
            //missing values , display an error message
            Alert alert = new Alert(Alert.AlertType.ERROR, "Complete the booking form", ButtonType.OK);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("alert");
            alert.showAndWait();
        } else {
            int selectedSeatNum = Integer.parseInt(seatCombo.getValue().substring(7));
            // extract the seat number from the string
            if (availableSeats <= 0 || selectedSeatNum > availableSeats) {
                // No seats available or invalid seat selected, display an error message
                Alert alert = new Alert(Alert.AlertType.ERROR, "Selected seat is no longer available, please select another seat", ButtonType.OK);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("alert");
                alert.showAndWait();
            } else {
                // Seat is available, book it
                availableSeats--;
                seatCombo.getItems().remove(selectedSeat);
                seatCombo.getSelectionModel().clearSelection();
                // Display a success message to the client
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ticket booked successfully", ButtonType.OK);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("alert");
                alert.showAndWait();

                String name = nameField.getText();
                String phone = phoneField.getText();
                try (
                        Socket socket=new Socket("127.0.0.1",5000);
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
                ) {
                    // Send the search request to the server
                    out.writeObject("ADD_RESERVATION");
                    out.writeObject(name);
                    out.writeObject(phone);
                    out.writeObject(selectedSeat);


                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        }
    });

}


    public void setTrip(Trip trip) {
        this.trip=trip;
    }
}
