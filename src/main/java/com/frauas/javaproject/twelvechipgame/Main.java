package com.frauas.javaproject.twelvechipgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    // Set a default window resolution
    private int resolutionXValue = 1280;
    private int resolutionYValue = 720;

    @Override
    public void start(Stage stage) throws Exception {
        // Loads the main-menu FXML file
        Parent root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));

        // Creates the Scene Main Menu Scene with the above resolution
        Scene mainMenuScene = new Scene(root, resolutionXValue, resolutionYValue);

        // Set up the Stage
        stage.setTitle("Twelve Chip Game");
        stage.setScene(mainMenuScene);
        stage.show();
        

    }

    // Starts the Program
    public static void main(String[] args) {
        launch(args);
    }
}