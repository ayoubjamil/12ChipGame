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

        //        gameMode.setThreePlayerMode(true);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/game-mode.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();


       GameModeController gameModeController = loader.getController();
        gameModeController.initData(numberOfPlayers, "hard", resolutionXValue, resolutionYValue);

        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();


    }

    public void onEasyModeClicked(ActionEvent event) throws IOException {

        //        gameMode.setThreePlayerMode(true);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/game-mode.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();


        GameModeController gameModeController = loader.getController();
        gameModeController.initData(numberOfPlayers, "easy", resolutionXValue, resolutionYValue);

        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();
    }



//    public void setGameModeChosen() {
//        if (gameModeController.isThreePlayerMode()){
////            gameModeChosen.appendText("\nChoose a three player mode\n");
//        }
//    }


}
