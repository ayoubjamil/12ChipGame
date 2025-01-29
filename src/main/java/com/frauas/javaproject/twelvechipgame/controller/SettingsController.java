package com.frauas.javaproject.twelvechipgame.controller;

import com.frauas.javaproject.twelvechipgame.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    // Default window resolution
    private int resolutionXValue = 1280;
    private int resolutionYValue = 720;

    public ToggleGroup resolution;
    public RadioButton hd;
    public RadioButton fullHD;
    public RadioButton quadHD;
    private Stage stage;
    private Scene scene;


    // Switch back to Main Menu
    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        // Sets the Stage
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

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

    // Sets the window resolution depending on which radio button is clicked, these are then forwarded to the Main Menu Controller through its initData method
    @FXML
    public void setResolution() {
        if(hd.isSelected()) {
            resolutionXValue = 1280;
            resolutionYValue = 720;
            hd.selectedProperty().set(true);
        }

        if(fullHD.isSelected()) {
            resolutionXValue = 1920;
            resolutionYValue = 1080;
            fullHD.selectedProperty().set(true);
        }

        if(quadHD.isSelected()) {
            resolutionXValue = 2560;
            resolutionYValue = 1440;
            quadHD.selectedProperty().set(true);
        }



    }

    // init Data method of this controller class
    public void initData(int resolution) {
       // depending on which resolution it receives, it selects the corresponding radio button and sets the resolution
        if (resolution == 1280){
            this.resolutionXValue = 1280;
            this.resolutionYValue = 720;
            hd.setSelected(true);
            fullHD.setSelected(false);
            quadHD.setSelected(false);
        } else if (resolution == 1920){
            this.resolutionXValue = 1920;
            this.resolutionYValue = 1080;
            fullHD.setSelected(true);
            hd.setSelected(false);
            quadHD.setSelected(false);
        } else if (resolution == 2560){
            this.resolutionXValue = 2560;
            this.resolutionYValue = 1440;
            quadHD.setSelected(true);
            fullHD.setSelected(false);
            hd.setSelected(false);
        }
    }
}
