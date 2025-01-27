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

    public Button gameRulesButton;
    @FXML
    private Button threePlayerButton;
    @FXML
    private Button fourPlayerButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button exitButton;

    int resolutionXValue = 1280;
    int resolutionYValue = 720;


    private Stage stage;
    private Scene scene;


    public MainMenuController() {

    }

    // Switch to difficulty window
    @FXML
    public void onThreePlayerButtonClicked(ActionEvent event) throws IOException {

        // Sets the Stage
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Loads the choose-difficulty FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();

        // Gets the DifficultyController
        DifficultyController difficultyController = loader.getController();
        // Passes the number of players (3 in this case) as well as the window resolution the Difficulty Window
        difficultyController.initData(3, resolutionXValue, resolutionYValue);

        // Creates the Difficulty Window with the above set resolution
        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void onFourPlayerButtonClicked(ActionEvent event) throws IOException {

        // Sets the Stage
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Loads the choose-difficulty FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();

        // Gets the DifficultyController
        DifficultyController difficultyController = loader.getController();

        // Passes the number of players (4 in this case) as well as the window resolution the Difficulty Window
        difficultyController.initData(4, resolutionXValue, resolutionYValue);

        // Creates the Difficulty Window with the above set resolution
        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();



    }

    // Switches to the Settings Window
    public void switchToSettings(ActionEvent event) throws IOException {

        // Sets the Stage
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Loads the settings FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/settings.fxml"));
        Parent root = loader.load();

        // Gets the SettingsController
        SettingsController settingsController = loader.getController();
        // Passes the X Value for the resolution to the Setting Window
        settingsController.initData(resolutionXValue);

        // Creates the Settings Window with the above set resolution
        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();
    }

    // Switches to the Game Rules Window
    public void switchToGameRules(ActionEvent event) throws IOException {

        // Sets the Stage
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Loads the game-rules FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/game-rules.fxml"));
        Parent root = loader.load();

        // Gets the GameRulesController
        GameRulesController gameRulesController = loader.getController();
        gameRulesController.initData(resolutionXValue, resolutionYValue);

        // Creates the GameRules Window with the above set resolution
        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();
    }

    // Initializes the Main Menu with the received window resolution
    public void initData(int resolutionXValue, int resolutionYValue) {
            this.resolutionXValue = resolutionXValue;
            this.resolutionYValue = resolutionYValue;
    }

    // exits the program to desktop
    public void exitProgram(ActionEvent event) throws IOException {
        System.exit(0);
    }

}
