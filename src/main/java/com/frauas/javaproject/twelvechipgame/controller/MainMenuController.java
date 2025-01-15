package com.frauas.javaproject.twelvechipgame.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button threePlayerButton;
    @FXML
    private Button fourPlayerButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextArea debugField;

    String resourcePath = "/com/frauas/javaproject/twelvechipgame/";

    private Stage stage;
    private Scene scene;
    private Parent root;



    // Example: a small "game mode" logic holder
    private final GameModeController gameMode = new GameModeController();

    public static boolean isThreePlayer = false;

    /**
     * No-arg constructor.
     * JavaFX automatically uses this to create the controller
     * when loading the FXML (because of fx:controller).
     */
    public MainMenuController() {
        // Usually empty.
    }

    @FXML
    public void onThreePlayerButtonClicked(ActionEvent event) throws IOException {

//        gameMode.setThreePlayerMode(true);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();


        DifficultyController difficultyController = loader.getController();
        difficultyController.initData(3);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    public void onFourPlayerButtonClicked(ActionEvent event) throws IOException {

//        gameMode.setThreePlayerMode(true);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();


        DifficultyController difficultyController = loader.getController();
        difficultyController.initData(4);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    public void switchToSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/settings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exitProgram(ActionEvent event) throws IOException {
        System.exit(0);
    }


    /**
     * Called automatically after all @FXML fields are injected.
     * Put your event handlers here.
     */
 /*   @FXML
    private void initialize() {
        // Hook up button actions:
        threePlayerButton.setOnAction(event -> {
            gameMode.setThreePlayerMode(true);
            debugField.appendText("Three-player mode: " + gameMode.isThreePlayerMode() + "\n");

        });

        fourPlayerButton.setOnAction(event -> {
            gameMode.setFourPlayerMode(true);
            debugField.appendText("Four-player mode: " + gameMode.isFourPlayerMode() + "\n");
        });

        settingsButton.setOnAction(event -> {
            debugField.appendText("Open Settings...\n");
            // open a settings dialog, etc.
        });

        exitButton.setOnAction(event -> {
            // Typically close the window or exit the application
            System.exit(0);
        });
    }*/
}
