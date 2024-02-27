package com.example.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class HelloController {
    private Socket socket;

    @FXML
    private Label welcomeText;

    @FXML
    private Button showSearchView;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

@FXML
    void loadSearchView(ActionEvent event) throws IOException{
        Parent root6 = FXMLLoader.load(getClass().getResource("search-view.fxml"));
        Stage window = (Stage) showSearchView.getScene().getWindow();
        window.setScene(new Scene(root6,
                900,
                700));
    }

}
