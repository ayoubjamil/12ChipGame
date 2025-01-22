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

    private int resolutionXValue = 1280;
    private int resolutionYValue = 720;

    public ToggleGroup resolution;
    public RadioButton hd;
    public RadioButton fullHD;
    public RadioButton quadHD;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/main-menu.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();
        MainMenuController controller = loader.getController();
        controller.initData(resolutionXValue, resolutionYValue);

        scene = new Scene(root, resolutionXValue, resolutionYValue);

        stage.setScene(scene);
        stage.show();
    }

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

    public void initData(int resolution) {
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
