package com.frauas.javaproject.twelvechipgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class GameRulesController {

    @FXML
    public Button returnToMainMenuFromGameRules;

    // Default window resolution
    private int resolutionXValue = 1280;
    private int resolutionYValue = 720;

    private Scene scene;

    // Switch back to Main Menu
    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        // Sets the Stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Loads the main-menu FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/main-menu.fxml"));
        Parent root = loader.load();

        // Gets the MainMenuController
        MainMenuController controller = loader.getController();

        // Passes the window resolution back to the MainMenuController
        controller.initData(resolutionXValue, resolutionYValue);

        // Creates the Main Menu
        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();
    }

    // Initializes the window with the received window resolution
    public void initData(int resolutionXValue, int resolutionYValue) {
        this.resolutionXValue = resolutionXValue;
        this.resolutionYValue = resolutionYValue;
    }
    
    
}
