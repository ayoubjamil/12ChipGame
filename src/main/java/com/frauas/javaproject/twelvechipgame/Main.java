package com.frauas.javaproject.twelvechipgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-menu.fxml"));

        // Create the Scene (adjust the size as needed)
        Scene mainMenuScene = new Scene(fxmlLoader.load(), 1280, 720);

        // Set up the Stage
        stage.setTitle("Twelve Chip Game");
        stage.setScene(mainMenuScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}