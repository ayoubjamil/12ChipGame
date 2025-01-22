package com.frauas.javaproject.twelvechipgame.controller;

import com.frauas.javaproject.twelvechipgame.GameLogic.TestLogic;
import com.frauas.javaproject.twelvechipgame.gamecomponets.Coin;
import com.frauas.javaproject.twelvechipgame.gamecomponets.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

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

    //Playing field.
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

    // window creation
    private Stage stage;
    private Scene scene;
    private Parent root;


    private boolean isThreePlayerMode;
    private boolean isHardMode;
    private int numberOfPlayers;
    private boolean difficulty;

    // test variablen
    private boolean isHandEmpty = false;

    TestLogic logic;

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


    // Methods for updating corresponding UI
    public void updateHand(Player player, Coin coin) {
        String color = coin.getColor().toString();
//        System.out.printf("player number: " + player.getPlayerNumber() + "\n");
        if (player.getPlayerNumber() == 1) {

            if (Player1Coin1.getText().isEmpty()){
                Player1Coin1.setText(Integer.toString(coin.getNumber()));
                Player1Coin1.setStyle(String.format("-fx-text-fill: %s;", color));

            } else if (Player1Coin2.getText().isEmpty()){
                Player1Coin2.setText(Integer.toString(coin.getNumber()));
                Player1Coin2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player1Coin3.getText().isEmpty()){
                Player1Coin3.setText(Integer.toString(coin.getNumber()));
                Player1Coin3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player1Coin4.getText().isEmpty()){
                Player1Coin4.setText(Integer.toString(coin.getNumber()));
                Player1Coin4.setStyle(String.format("-fx-text-fill: %s;", color));
            }


        } else if (player.getPlayerNumber() == 2) {
//            System.out.printf("player number inside: " + player.getPlayerNumber() + "\n");
            if (Player2Coin1.getText().isEmpty()){
                Player2Coin1.setText(Integer.toString(coin.getNumber()));
                Player2Coin1.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));

            } else if (Player2Coin2.getText().isEmpty()){
               Player2Coin2.setText(Integer.toString(coin.getNumber()));
               Player2Coin2.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));

            } else if (Player2Coin3.getText().isEmpty()){
                Player2Coin3.setText(Integer.toString(coin.getNumber()));
                Player2Coin3.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));

            } else if (Player2Coin4.getText().isEmpty()){
                Player2Coin4.setText(Integer.toString(coin.getNumber()));
                Player2Coin4.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));

            }

        } else if (player.getPlayerNumber() == 3) {

            if (Player3Coin1.getText().isEmpty()){
               Player3Coin1.setText(Integer.toString(coin.getNumber()));
                Player3Coin1.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player3Coin2.getText().isEmpty()){
                Player3Coin2.setText(Integer.toString(coin.getNumber()));
                Player3Coin2.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player3Coin3.getText().isEmpty()){
                Player3Coin3.setText(Integer.toString(coin.getNumber()));
                Player3Coin3.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player3Coin4.getText().isEmpty()){
                Player3Coin4.setText(Integer.toString(coin.getNumber()));
                Player3Coin4.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            }


        } else if (player.getPlayerNumber() == 4) {

            if (Player4Coin1.getText().isEmpty()){
                Player4Coin1.setText(Integer.toString(coin.getNumber()));
                Player4Coin1.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player4Coin2.getText().isEmpty()){
                Player4Coin2.setText(Integer.toString(coin.getNumber()));
                Player4Coin2.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player4Coin3.getText().isEmpty()){
                Player4Coin3.setText(Integer.toString(coin.getNumber()));
                Player4Coin3.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player4Coin4.getText().isEmpty()){
                Player4Coin4.setText(Integer.toString(coin.getNumber()));
                Player4Coin4.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            }


        }


    }

    public void updateCoinsTaken(Player player, Coin coin) {
        String color = coin.getColor().toString();
        if (player.getPlayerNumber() == 1) {
            if (Integer.parseInt(Player1CoinTaken1.getText()) == 0 ){
                Player1CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken2.getText()) == 0 ){
                Player1CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken3.getText()) == 0 ){
                Player1CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken4.getText()) == 0 ){
                Player1CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }

        } else if (player.getPlayerNumber() == 2) {

            if (Integer.parseInt(Player2CoinTaken1.getText()) == 0 ){
                Player2CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken2.getText()) == 0 ){
                Player2CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken3.getText()) == 0 ){
                Player2CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken4.getText()) == 0 ){
                Player2CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }


        } else if (player.getPlayerNumber() == 3) {

            if (Integer.parseInt(Player3CoinTaken1.getText()) == 0 ){
                Player3CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken2.getText()) == 0 ){
                Player3CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken3.getText()) == 0 ){
                Player3CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken4.getText()) == 0 ){
                Player3CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }


        } else if (player.getPlayerNumber() == 4) {

            if (Integer.parseInt(Player4CoinTaken1.getText()) == 0 ){
                Player4CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken2.getText()) == 0 ){
                Player4CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken3.getText()) == 0 ){
                Player4CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Integer.parseInt(Player1CoinTaken4.getText()) == 0 ){
                Player4CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }


        }


    }

    public void updateCoinsOnPlayingField (List<Coin> coinsOnField) {

        if (!coinsOnField.isEmpty()){
            if (FieldButton1.getText().isEmpty()) {
                System.out.printf("Field is empty\n");
                FieldButton1.setText(Integer.toString(coinsOnField.get(0).getNumber()));
                FieldButton1.setStyle(String.format("-fx-text-fill: %s;", coinsOnField.get(0).getColor()));
                FieldButton2.setText(Integer.toString(coinsOnField.get(1).getNumber()));
                FieldButton2.setStyle(String.format("-fx-text-fill: %s;", coinsOnField.get(1).getColor()));
                FieldButton3.setText(Integer.toString(coinsOnField.get(2).getNumber()));
                FieldButton3.setStyle(String.format("-fx-text-fill: %s;", coinsOnField.get(2).getColor()));
                FieldButton4.setText(Integer.toString(coinsOnField.get(3).getNumber()));
                FieldButton4.setStyle(String.format("-fx-text-fill: %s;", coinsOnField.get(3).getColor()));

            }
        }   else {
                FieldButton1.setText("");
                FieldButton2.setText("");
                FieldButton3.setText("");
                FieldButton4.setText("");
        }


    }


    // Methods for returning chosen coin numbers
    public int getCoinNumber(ActionEvent event) {
        System.out.printf("button text: " + ((Button) event.getSource()).getText() + "\n");
        return !((Button) event.getSource()).getText().isEmpty() ? Integer.parseInt(Player1Coin1.getText()) : 0;

    }


    // Methods to initialize the corresponding UI elements with coins
    public void initializeFieldWithZeros(Player player, Coin coin) {
        String color = coin.getColor().toString();
        if (player.getPlayerNumber() == 1) {
            if (Player1CoinTaken1.getText().isEmpty()) {
                Player1CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player1CoinTaken2.getText().isEmpty()){
                Player1CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player1CoinTaken3.getText().isEmpty() ){
                Player1CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player1CoinTaken4.getText().isEmpty()){
                Player1CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }

        } else if (player.getPlayerNumber() == 2) {

            if (Player2CoinTaken1.getText().isEmpty()){
                Player2CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player2CoinTaken2.getText().isEmpty()){
                Player2CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player2CoinTaken3.getText().isEmpty() ){
                Player2CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player2CoinTaken4.getText().isEmpty()){
                Player2CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }


        } else if (player.getPlayerNumber() == 3) {

            if (Player3CoinTaken1.getText().isEmpty() ){
                Player3CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player3CoinTaken2.getText().isEmpty() ){
                Player3CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player3CoinTaken3.getText().isEmpty()){
                Player3CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player3CoinTaken4.getText().isEmpty() ){
                Player3CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }


        } else if (player.getPlayerNumber() == 4) {

            if (Player4CoinTaken1.getText().isEmpty()){
                Player4CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player4CoinTaken2.getText().isEmpty()){
                Player4CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player4CoinTaken3.getText().isEmpty() ){
                Player4CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player4CoinTaken4.getText().isEmpty() ){
                Player4CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }


        }


    }

    // update the UI with the coins the players have at the beginning of the game
    public void initializeStartingHand() {

        for (int i = 0 ; i < numberOfPlayers; i++){
            for (int j = 0 ; j < 4; j++){
                updateHand(logic.getPlayers().get(i),logic.getPlayers().get(i).getCoinsOnHand().get(j));
                System.out.printf("Player: %d; Coin :%d\n", logic.getPlayers().get(i).getPlayerNumber(), logic.getPlayers().get(i).getCoinsOnHand().get(j).getNumber());
            }

        }





    }

    public void initializeField() {

        for (int i = 0 ; i < numberOfPlayers; i++){
            for (int j = 0 ; j < 4; j++){
                initializeFieldWithZeros(logic.getPlayers().get(i),logic.getPlayers().get(i).getFieldOfWonCoins().get(j));
                System.out.printf("Player: %d; Coin :%d\n", logic.getPlayers().get(i).getPlayerNumber(), logic.getPlayers().get(i).getFieldOfWonCoins().get(j).getNumber());
            }
        }







    }


    // Game Logic
    public void nextTurn(){
        // call game logic from logic class here

        System.out.printf("next turn \n");


        System.out.printf("next turn before updateHand\n");
//       updateHand(logic.getPlayers().get(0), logic.getPlayers().getFirst().getCoinsOnHand().get(0));
        System.out.printf("next turn before updateCoinsTaken\n");

        updateCoinsTaken(logic.getPlayers().get(1), logic.getPlayers().getFirst().getFieldOfWonCoins().getFirst());

        System.out.printf("next turn before updateCoinsOnPLlayingField\n");
        updateCoinsOnPlayingField(logic.getCoinsOnField());



        //check for game over condition to end game and not create an infinite game loop

        /*
        if (logic.isGameOver()) {
            toggleVisibilityForAllPlayers(numberOfPlayers);
            GameOverButton.setVisible(!GameOverButton.isVisible());


        } else {
            nextTurn();
        }*/

    };

    // initializing data from received from previous windows and play game logic
    public void initData(int numberOfPlayers, boolean difficulty, int resolutionXValue, int resolutionYValue) throws IOException {
        // data received from previous windows
        this.numberOfPlayers = numberOfPlayers;
        this.difficulty = difficulty;

        this.resolutionXValue = resolutionXValue;
        this.resolutionYValue = resolutionYValue;

        // turn off visibility of GameOverButton at the beginning of the game
        GameOverButton.setVisible(!GameOverButton.isVisible());

        // toggle visibility of player 4 off if only 3 players are playing
        if (numberOfPlayers == 3) {
            toggleVisibilityForPlayer4();
        }
        System.out.printf("number of Players: %d\n", numberOfPlayers);
        logic = new TestLogic(numberOfPlayers);
        logic.initializeGame(numberOfPlayers);
        initializeStartingHand();
        initializeField();
        // run game logic
        nextTurn();

        if (isHandEmpty) {
            toggleVisibilityForAllPlayers(numberOfPlayers);
            GameOverButton.setVisible(!GameOverButton.isVisible());


        }

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
