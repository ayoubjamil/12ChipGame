package com.frauas.javaproject.twelvechipgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    private int resolutionXValue = 1280;
    private int resolutionYValue = 720;




    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file



        Parent root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));

        // Create the Scene (adjust the size as needed)
        Scene mainMenuScene = new Scene(root, resolutionXValue, resolutionYValue);

        // Set up the Stage
        stage.setTitle("Twelve Chip Game");
        stage.setScene(mainMenuScene);
        stage.show();
        

    }





    public static void main(String[] args) {
        launch(args);
    }
}