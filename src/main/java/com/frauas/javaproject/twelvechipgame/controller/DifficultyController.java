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

    private Stage stage;
    private Scene scene;
    private Parent root;


    private int numberOfPlayers;

    @FXML
    private TextArea gameModeChosen;
    public String text;



    public DifficultyController() {

    }


    public void initData(int numberOfPlayers) {
        if (numberOfPlayers == 3 || numberOfPlayers == 4) {
            this.numberOfPlayers = numberOfPlayers;

        }

    }

    public void onHardModeClicked(ActionEvent event) throws IOException {

        //        gameMode.setThreePlayerMode(true);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/game-mode.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();


       GameModeController gameModeController = loader.getController();
        gameModeController.initData(numberOfPlayers, "hard");

        scene = new Scene(root);
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
        gameModeController.initData(numberOfPlayers, "easy");

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



//    public void setGameModeChosen() {
//        if (gameModeController.isThreePlayerMode()){
////            gameModeChosen.appendText("\nChoose a three player mode\n");
//        }
//    }


}
