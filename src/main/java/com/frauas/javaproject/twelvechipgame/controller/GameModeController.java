package com.frauas.javaproject.twelvechipgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class GameModeController {


    // Player 1
    @FXML
    public Label Player1Label;
    @FXML
    public Button Player1Coin1;
    @FXML
    public Button Player1Coin2;
    @FXML
    public Button Player1Coin3;
    @FXML
    public Button Player1Coin4;
    @FXML
    public Label Player1CoinTaken3;
    @FXML
    public Label Player1CoinTaken4;
    @FXML
    public Label Player1CoinTaken1;
    @FXML
    public Label Player1CoinTaken2;

    // Player 2
    @FXML
    public Label Player2Label;
    @FXML
    public Label Player2Coin1;
    @FXML
    public Label Player2Coin2;
    @FXML
    public Label Player2Coin3;
    @FXML
    public Label Player2Coin4;
    @FXML
    public Label Player2CoinTaken2;
    @FXML
    public Label Player2CoinTaken1;
    @FXML
    public Label Player2CoinTaken3;
    @FXML
    public Label Player2CoinTaken4;


    // Player 3
    @FXML
    public Label Player3Label;
    @FXML
    public Label Player3Coin1;
    @FXML
    public Label Player3Coin2;
    @FXML
    public Label Player3Coin3;
    @FXML
    public Label Player3Coin4;
    @FXML
    public Label Player3CoinTaken4;
    @FXML
    public Label Player3CoinTaken1;
    @FXML
    public Label Player3CoinTaken2;
    @FXML
    public Label Player3CoinTaken3;


    // Player 4
    @FXML
    public Label Player4Label;
    @FXML
    public Label Player4Coin1;
    @FXML
    public Label Player4Coin2;
    @FXML
    public Label Player4Coin3;
    @FXML
    public Label Player4Coin4;
    @FXML
    public Label Player4CoinTaken1;
    @FXML
    public Label Player4CoinTaken2;
    @FXML
    public Label Player4CoinTaken3;
    @FXML
    public Label Player4CoinTaken4;

    //Playing field
    @FXML
    public Button FieldButton1;
    @FXML
    public Button FieldButton2;
    @FXML
    public Button FieldButton3;
    @FXML
    public Button FieldButton4;


    //change scene to Game Finished scene
    @FXML
    public Button GameOverButton;

    //window resolution
    int resolutionXValue = 1280;
    int resolutionYValue = 720;

    private Stage stage;
    private Scene scene;
    private Parent root;


    private boolean isThreePlayerMode;
    private boolean isHardMode;
    private int numberOfPlayers;
    private String difficulty;

    // test variablen
    private boolean isHandEmpty = true;



    public void toggleVisibilityForPlayer4() {
        Player4Label.setVisible(!Player4Label.isVisible());
        Player4Coin1.setVisible(!Player4Coin1.isVisible());
        Player4Coin2.setVisible(!Player4Coin2.isVisible());
        Player4Coin3.setVisible(!Player4Coin3.isVisible());
        Player4Coin4.setVisible(!Player4Coin4.isVisible());
        Player4CoinTaken1.setVisible(!Player4CoinTaken1.isVisible());
        Player4CoinTaken2.setVisible(!Player4CoinTaken2.isVisible());
        Player4CoinTaken3.setVisible(!Player4CoinTaken3.isVisible());
        Player4CoinTaken4.setVisible(!Player4CoinTaken4.isVisible());
    }

    public void toggleVisibilityForPlayer1() {
        Player1Label.setVisible(!Player1Label.isVisible());
        Player1Coin1.setVisible(!Player1Coin1.isVisible());
        Player1Coin2.setVisible(!Player1Coin2.isVisible());
        Player1Coin3.setVisible(!Player1Coin3.isVisible());
        Player1Coin4.setVisible(!Player1Coin4.isVisible());
        Player1CoinTaken1.setVisible(!Player1CoinTaken1.isVisible());
        Player1CoinTaken2.setVisible(!Player1CoinTaken2.isVisible());
        Player1CoinTaken3.setVisible(!Player1CoinTaken3.isVisible());
        Player1CoinTaken4.setVisible(!Player1CoinTaken4.isVisible());
    }

    public void toggleVisibilityForPlayer2() {
        Player2Label.setVisible(!Player2Label.isVisible());
        Player2Coin1.setVisible(!Player2Coin1.isVisible());
        Player2Coin2.setVisible(!Player2Coin2.isVisible());
        Player2Coin3.setVisible(!Player2Coin3.isVisible());
        Player2Coin4.setVisible(!Player2Coin4.isVisible());
        Player2CoinTaken1.setVisible(!Player2CoinTaken1.isVisible());
        Player2CoinTaken2.setVisible(!Player2CoinTaken2.isVisible());
        Player2CoinTaken3.setVisible(!Player2CoinTaken3.isVisible());
        Player2CoinTaken4.setVisible(!Player2CoinTaken4.isVisible());
    }

    public void toggleVisibilityForPlayer3() {
        Player3Label.setVisible(!Player3Label.isVisible());
        Player3Coin1.setVisible(!Player3Coin1.isVisible());
        Player3Coin2.setVisible(!Player3Coin2.isVisible());
        Player3Coin3.setVisible(!Player3Coin3.isVisible());
        Player3Coin4.setVisible(!Player3Coin4.isVisible());
        Player3CoinTaken1.setVisible(!Player3CoinTaken1.isVisible());
        Player3CoinTaken2.setVisible(!Player3CoinTaken2.isVisible());
        Player3CoinTaken3.setVisible(!Player3CoinTaken3.isVisible());
        Player3CoinTaken4.setVisible(!Player3CoinTaken4.isVisible());
    }

    public void toggleVisibilityForFieldButtons() {
        FieldButton1.setVisible(!FieldButton1.isVisible());
        FieldButton2.setVisible(!FieldButton2.isVisible());
        FieldButton3.setVisible(!FieldButton3.isVisible());
        FieldButton4.setVisible(!FieldButton4.isVisible());
    }

    public void toggleVisibilityForAllPlayers(int numberOfPlayers) {
        if (numberOfPlayers == 3) {
            toggleVisibilityForPlayer1();
            toggleVisibilityForPlayer2();
            toggleVisibilityForPlayer3();
            toggleVisibilityForFieldButtons();
        } else {
            toggleVisibilityForPlayer1();
            toggleVisibilityForPlayer2();
            toggleVisibilityForPlayer3();
            toggleVisibilityForPlayer4();
            toggleVisibilityForFieldButtons();
        }

    }

    public void onButtonClickedUpdate(ActionEvent actionEvent) {
        int x = 11;


        FieldButton1.setText(Integer.toString(x));
        FieldButton1.setTextFill(Paint.valueOf("white"));

    };

    public void nextTurn(){


    };

    public void initData(int numberOfPlayers, String difficulty, int resolutionXValue, int resolutionYValue) throws IOException {
        this.numberOfPlayers = numberOfPlayers;
        this.difficulty = difficulty;
        GameOverButton.setVisible(!GameOverButton.isVisible());
        this.resolutionXValue = resolutionXValue;
        this.resolutionYValue = resolutionYValue;

        if (numberOfPlayers == 3) {
            toggleVisibilityForPlayer4();
        }


        nextTurn();

        if (isHandEmpty) {
            toggleVisibilityForAllPlayers(numberOfPlayers);
            GameOverButton.setVisible(!GameOverButton.isVisible());


        }

    }





    public boolean isThreePlayerMode() {
        return isThreePlayerMode;
    }

    public void setThreePlayerMode(boolean threePlayerMode) {
        this.isThreePlayerMode = threePlayerMode;
    }

    public boolean isHardMode() {
        return isHardMode;
    }

    public void setHardMode(boolean hardMode) {
        isHardMode = hardMode;
    }




    public void onGameOverSwitchToGameFinished(ActionEvent event) throws IOException {

        //        gameMode.setThreePlayerMode(true);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/game-finished.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();


        GameFinishedController gameFinishedController = loader.getController();
        gameFinishedController.initData(18,23,24,-1, 3, resolutionXValue, resolutionYValue);

        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();

    }

}
