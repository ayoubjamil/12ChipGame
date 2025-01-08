package com.frauas.javaproject.twelvechipgame.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

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

    // Example: a small "game mode" logic holder
    private final GameModeController gameMode = new GameModeController();

    /**
     * No-arg constructor.
     * JavaFX automatically uses this to create the controller
     * when loading the FXML (because of fx:controller).
     */
    public MainMenuController() {
        // Usually empty.
    }

    /**
     * Called automatically after all @FXML fields are injected.
     * Put your event handlers here.
     */
    @FXML
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
    }
}
