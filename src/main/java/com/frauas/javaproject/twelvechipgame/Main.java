package com.frauas.javaproject.twelvechipgame;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));

        // Create the Scene (adjust the size as needed)
        Scene mainMenuScene = new Scene(root);

        // Set up the Stage
        stage.setTitle("Twelve Chip Game");
        stage.setScene(mainMenuScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

