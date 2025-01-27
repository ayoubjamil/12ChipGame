package com.frauas.javaproject.twelvechipgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class DifficultyController {

    int resolutionXValue = 1280;
    int resolutionYValue = 720;

    private Stage stage;
    private Scene scene;
    private Parent root;


    private int numberOfPlayers;

    @FXML
    private TextArea gameModeChosen;
    public String text;



    public DifficultyController() {

    }


    public void initData(int numberOfPlayers, int resolutionXValue, int resolutionYValue ) {
        if (numberOfPlayers == 3 || numberOfPlayers == 4) {
            this.numberOfPlayers = numberOfPlayers;
        }

        this.resolutionXValue = resolutionXValue;
        this.resolutionYValue = resolutionYValue;

    }

    public void onHardModeClicked(ActionEvent event) throws IOException {

        // Sets the Stage
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Loads the game-mode FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/game-mode.fxml"));
        Parent root = loader.load();

        // Gets the GameModeController
        GameModeController gameModeController = loader.getController();

        // Passes the numberOfPlayers the hard difficulty and window resolution to GameModeController
        gameModeController.initData(numberOfPlayers, true, resolutionXValue, resolutionYValue);

        // Creates the GameMode window
        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();

    }

    public void onEasyModeClicked(ActionEvent event) throws IOException {

        // Sets the Stage
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Loads the game-mode FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/game-mode.fxml"));
        Parent root = loader.load();

        // Gets the GameModeController
        GameModeController gameModeController = loader.getController();

        // Passes the numberOfPlayers the easy difficulty and window resolution to GameModeController
        gameModeController.initData(numberOfPlayers, false, resolutionXValue, resolutionYValue);

        // Creates the GameMode window
        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();
    }


}
