package com.frauas.javaproject.twelvechipgame.controller;

import com.frauas.javaproject.twelvechipgame.gamecomponets.Coin;
import com.frauas.javaproject.twelvechipgame.gamecomponets.NPC;
import com.frauas.javaproject.twelvechipgame.gamecomponets.Player;
import com.frauas.javaproject.twelvechipgame.temp.CustomPair;
import com.frauas.javaproject.twelvechipgame.temp.Logic;

import com.frauas.javaproject.twelvechipgame.temp.Round;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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


    private int player1Total = 0;
    private int player2Total = 0;
    private int player3Total = 0;
    private int player4Total = 0;
    private int playerWhoWonTheGame = 0;

    private int turnsLeftInRound = 0;
    private int processingPlayersLeftInRound = 0;

    private final int PAUSE_BETWEEN_MOVES = 1;

    // logic;

    private static Logic logic = (Logic) Logic.getInstance();


    // Player 1 Coins on hand
    List<Button> coinsOnHandPlayer1;


    List<Label> coinsOnHandPlayer2;
    List<Label> coinsOnHandPlayer3;
    List<Label> coinsOnHandPlayer4;


    // Players Lists of coins Taken (moved to FieldOfWonCoins)

    List<Label> coinsTakenPlayer1;
    List<Label> coinsTakenPlayer2;
    List<Label> coinsTakenPlayer3;
    List<Label> coinsTakenPlayer4;

    // Coins on the field, available to choose
    List<Button> coinsToChooseFromField;

    Player roundWinner = null;

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
    public void updateHands(Player player, Coin coin) {
        String color = coin.getColor().toString();
//        System.out.printf("player number: " + player.getPlayerNumber() + "\n");
        if (player.getPlayerNumber() == 1) {

            if (Player1Coin1.getText().isEmpty()) {
                Player1Coin1.setText(Integer.toString(coin.getNumber()));
                Player1Coin1.setStyle(String.format("-fx-text-fill: %s;", color));

            } else if (Player1Coin2.getText().isEmpty()) {
                Player1Coin2.setText(Integer.toString(coin.getNumber()));
                Player1Coin2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player1Coin3.getText().isEmpty()) {
                Player1Coin3.setText(Integer.toString(coin.getNumber()));
                Player1Coin3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player1Coin4.getText().isEmpty()) {
                Player1Coin4.setText(Integer.toString(coin.getNumber()));
                Player1Coin4.setStyle(String.format("-fx-text-fill: %s;", color));
            }


        } else if (player.getPlayerNumber() == 2) {
//            System.out.printf("player number inside: " + player.getPlayerNumber() + "\n");
            if (Player2Coin1.getText().isEmpty()) {
                Player2Coin1.setText(Integer.toString(coin.getNumber()));
                Player2Coin1.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));

            } else if (Player2Coin2.getText().isEmpty()) {
                Player2Coin2.setText(Integer.toString(coin.getNumber()));
                Player2Coin2.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));

            } else if (Player2Coin3.getText().isEmpty()) {
                Player2Coin3.setText(Integer.toString(coin.getNumber()));
                Player2Coin3.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));

            } else if (Player2Coin4.getText().isEmpty()) {
                Player2Coin4.setText(Integer.toString(coin.getNumber()));
                Player2Coin4.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));

            }

        } else if (player.getPlayerNumber() == 3) {

            if (Player3Coin1.getText().isEmpty()) {
                Player3Coin1.setText(Integer.toString(coin.getNumber()));
                Player3Coin1.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player3Coin2.getText().isEmpty()) {
                Player3Coin2.setText(Integer.toString(coin.getNumber()));
                Player3Coin2.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player3Coin3.getText().isEmpty()) {
                Player3Coin3.setText(Integer.toString(coin.getNumber()));
                Player3Coin3.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player3Coin4.getText().isEmpty()) {
                Player3Coin4.setText(Integer.toString(coin.getNumber()));
                Player3Coin4.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            }


        } else if (player.getPlayerNumber() == 4) {

            if (Player4Coin1.getText().isEmpty()) {
                Player4Coin1.setText(Integer.toString(coin.getNumber()));
                Player4Coin1.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player4Coin2.getText().isEmpty()) {
                Player4Coin2.setText(Integer.toString(coin.getNumber()));
                Player4Coin2.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player4Coin3.getText().isEmpty()) {
                Player4Coin3.setText(Integer.toString(coin.getNumber()));
                Player4Coin3.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            } else if (Player4Coin4.getText().isEmpty()) {
                Player4Coin4.setText(Integer.toString(coin.getNumber()));
                Player4Coin4.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", color, color));
            }


        }
    }


    public void updateHandOnTake(Player player, Coin coin) {

    }

    public <T> void removeCoinFromHandUI(List<T> list, Coin coin) {
        for (T item : list) {
            if (item instanceof Labeled object) {
                if (object.getText().equals(String.valueOf(coin.getNumber()))) {
                    object.setStyle("");
                    object.setText("");
                    break;
                }
            }
        }
    }

    public void updateHandOnPlay(Player player, Coin coin) {
        int playerNumber = player.getPlayerNumber();

        switch (playerNumber) {
            case 1:
                removeCoinFromHandUI(coinsOnHandPlayer1, coin);
                break;
            case 2:
                removeCoinFromHandUI(coinsOnHandPlayer2, coin);
                break;
            case 3:
                removeCoinFromHandUI(coinsOnHandPlayer3, coin);
                break;
            case 4:
                removeCoinFromHandUI(coinsOnHandPlayer4, coin);
                break;

        }
    }


    public <T> void updateHandUI(List<T> hand, List<Coin> coins) {
        for (T item : hand) {
            if (item instanceof Labeled object) {
                object.setStyle("");
                object.setText("");
            }
        }
        for (int i = 0; i < coins.size(); i++) {
            if (hand.get(i) instanceof Label object) {
                object.setText(Integer.toString(coins.get(i).getNumber()));
                object.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", coins.get(i).getColor(), coins.get(i).getColor()));

            } else if (hand.get(i) instanceof Button object) {
                object.setText(Integer.toString(coins.get(i).getNumber()));
                object.setStyle(String.format(" -fx-text-fill: %s;", coins.get(i).getColor()));
            }
        }
    }

    public void clearFieldButton(Button button) {
        button.setStyle("");
        button.setText("");
    }


    public void updateCoinsTaken(Player player, Coin coin) {
        Button button = null;

        for (Button btn : coinsToChooseFromField) {
            if (btn.getText().equals(String.valueOf(coin.getNumber()))) {
                button = btn;
                break;
            }
        }

        switch (player.getPlayerNumber()) {
            case 1:
                updateHandUI(coinsOnHandPlayer1, player.getCoinsOnHand());
                clearFieldButton(button);
                break;
            case 2:
                updateHandUI(coinsOnHandPlayer2, player.getCoinsOnHand());
                clearFieldButton(button);
                break;
            case 3:
                updateHandUI(coinsOnHandPlayer3, player.getCoinsOnHand());
                clearFieldButton(button);
                break;
            case 4:
                updateHandUI(coinsOnHandPlayer4, player.getCoinsOnHand());
                clearFieldButton(button);
                break;
        }


    }

    public void updateFieldButtons(Button button, Coin coin) {
        button.setText(Integer.toString(coin.getNumber()));
        button.setStyle(String.format("-fx-text-fill: %s;", coin.getColor()));
    }

    public void updateCoinsOnPlayingField() {
        Round round = logic.getRound();
        for (CustomPair<Player, Coin> pair : round.getPlayedCoins()) {
            Player player = pair.getKey();
            Coin coin = pair.getValue();

            switch (player.getPlayerNumber()) {
                case 1:
                    updateFieldButtons(FieldButton1, coin);
                    break;
                case 2:
                    updateFieldButtons(FieldButton2, coin);
                    break;
                case 3:
                    updateFieldButtons(FieldButton3, coin);
                    break;
                case 4:
                    updateFieldButtons(FieldButton4, coin);
                    break;

            }
        }


    }


    // Methods for returning chosen coin numbers
    public int getCoinNumber(ActionEvent event) {
        System.out.printf("button text: " + ((Button) event.getSource()).getText() + "\n");
        return !((Button) event.getSource()).getText().isEmpty() ? Integer.parseInt(((Button) event.getSource()).getText()) : 0;

    }


    // Methods to initialize the corresponding UI elements with coins
    public void initializeFieldWithZeros(Player player, Coin coin) {
        String color = coin.getColor().toString();
        if (player.getPlayerNumber() == 1) {
            if (Player1CoinTaken1.getText().isEmpty()) {
                Player1CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player1CoinTaken2.getText().isEmpty()) {
                Player1CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player1CoinTaken3.getText().isEmpty()) {
                Player1CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player1CoinTaken4.getText().isEmpty()) {
                Player1CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player1CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }

        } else if (player.getPlayerNumber() == 2) {

            if (Player2CoinTaken1.getText().isEmpty()) {
                Player2CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player2CoinTaken2.getText().isEmpty()) {
                Player2CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player2CoinTaken3.getText().isEmpty()) {
                Player2CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player2CoinTaken4.getText().isEmpty()) {
                Player2CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player2CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }


        } else if (player.getPlayerNumber() == 3) {

            if (Player3CoinTaken1.getText().isEmpty()) {
                Player3CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player3CoinTaken2.getText().isEmpty()) {
                Player3CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player3CoinTaken3.getText().isEmpty()) {
                Player3CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player3CoinTaken4.getText().isEmpty()) {
                Player3CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player3CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }


        } else if (player.getPlayerNumber() == 4) {

            if (Player4CoinTaken1.getText().isEmpty()) {
                Player4CoinTaken1.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken1.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player4CoinTaken2.getText().isEmpty()) {
                Player4CoinTaken2.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken2.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player4CoinTaken3.getText().isEmpty()) {
                Player4CoinTaken3.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken3.setStyle(String.format("-fx-text-fill: %s;", color));
            } else if (Player4CoinTaken4.getText().isEmpty()) {
                Player4CoinTaken4.setText(Integer.toString(coin.getNumber()));
                Player4CoinTaken4.setStyle(String.format("-fx-text-fill: %s;", color));
            }


        }


    }

    // update the UI with the coins the players have at the beginning of the game
    public void initializeStartingHand() {

        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < 4; j++) {
                updateHands(logic.getPlayers().get(i), logic.getPlayers().get(i).getCoinsOnHand().get(j));
                System.out.printf("Player: %d; Coin :%d\n", logic.getPlayers().get(i).getPlayerNumber(), logic.getPlayers().get(i).getCoinsOnHand().get(j).getNumber());
            }

        }

    }

    public void initializeField() {

        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < 4; j++) {
                initializeFieldWithZeros(logic.getPlayers().get(i), logic.getPlayers().get(i).getFieldOfWonCoins().get(j));
                System.out.printf("Player: %d; Coin :%d\n", logic.getPlayers().get(i).getPlayerNumber(), logic.getPlayers().get(i).getFieldOfWonCoins().get(j).getNumber());
            }
        }


    }


    private void printAllPlayers() {
        List<Player> players = logic.getPlayers();
        System.out.println("\n--- Listing All Players ---");
        for (Player player : players) {
            System.out.println("Player ID: " + player.getPlayerNumber() +
                    ", Coins on Hand: " + player.getCoinsOnHand().stream()
                    .map(Coin::toString)
                    .collect(Collectors.joining(", ")) +
                    ", Coins Won: " + player.getFieldOfWonCoins().stream()
                    .map(Coin::toString)
                    .collect(Collectors.joining(", ")));
        }
        System.out.println("--- End of Player List ---\n");

        System.out.println("\n--- Listing All Players with Sums ---");
        List<CustomPair<Player, Integer>> playersSum = logic.calculateTotalCoinSumsForPlayers();
        for (CustomPair<Player, Integer> pair : playersSum) {
            if (pair.getKey().getPlayerNumber() == 1) {
                player1Total = pair.getValue();
            } else if (pair.getKey().getPlayerNumber() == 2) {
                player2Total = pair.getValue();
            } else if (pair.getKey().getPlayerNumber() == 3) {
                player3Total = pair.getValue();
            } else if (pair.getKey().getPlayerNumber() == 4) {
                player4Total = pair.getValue();
            }

            System.out.println("Player " + pair.getKey().getPlayerNumber() + " sum of coins: " + pair.getValue());
        }
        System.out.println("\n--- End of Players with Sums  ---\n");
    }



    private void handleNPCPlayer(NPC npc) {
        // disable Buttons

        //  disableHumanCoinButtons(true);

        Coin playedCoin = npc.playCoin();
        logic.validateAndRecordCoin(npc, playedCoin);


        npc.playCoinByValue(playedCoin.getNumber());

        updateHandOnPlay(npc, playedCoin);
        updateCoinsOnPlayingField();

        // Show the NPC's coin choice in the UI
        // maybe set a label or highlight
        System.out.println(">>>  " + "NPC player " + npc.getPlayerNumber() + " plays " + playedCoin);
        PauseTransition pause = new PauseTransition(Duration.seconds(PAUSE_BETWEEN_MOVES));
        pause.setOnFinished(e -> {
            // after the pause, proceed
            turnsLeftInRound--;
            nextTurn();
        });
        pause.play();


    }

    private void handleRegularPlayer(Player player) throws IllegalArgumentException {
        // enable Buttons
        // disableHumanCoinButtons(false);

        // Clear old handlers
        for (Button button : coinsOnHandPlayer1) {
            button.setOnAction(null);
            if (!button.getText().isEmpty()) {
                button.setDisable(false);
            }

        }

        boolean foundCoinsOnHand = false;

        // Setup new event handlers for each coin
        for (Button button : coinsOnHandPlayer1) {
            button.setOnAction(event -> {
                int clickedValue = Integer.parseInt(button.getText());
                // find that coin in player's hand
                for (int i = 0; i < player.getCoinsOnHand().size(); i++) {
                    if (player.getCoinsOnHand().get(i).getNumber() == clickedValue) {
                        Coin coinToPlay = player.getCoinsOnHand().get(i);
                        logic.validateAndRecordCoin(player, coinToPlay);
                        player.playCoin(i);
                        updateHandOnPlay(player, coinToPlay);
                        updateCoinsOnPlayingField();
                        System.out.println(">>>  " + "Player " + 1 + " plays " + coinToPlay);

                        break;
                    }
                }
                for (Button button2 : coinsOnHandPlayer1) {
                    button2.setDisable(true);
                }
                PauseTransition pause = new PauseTransition(Duration.seconds(PAUSE_BETWEEN_MOVES));
                pause.setOnFinished(e -> {
                    turnsLeftInRound--;
                    // After the user picks a coin, go to next turn
                    nextTurn();
                });
                pause.play();

            });
        }

        // logic.validateAndRecordCoin(player, coin);
        // player.playCoin(coinIndex.get());
        //  System.out.println(">>>  " + "Player " + player.getPlayerNumber() + " plays " + coin);

    }

    private void handleNPCFieldChoice(Player activePlayer) {
        System.out.println("====================== Active Player  =================================");
        System.out.printf(processingPlayersLeftInRound + " players left\n");

        /*if (activePlayer == null) {
            return;
        }*/
        List<Coin> coinsForChoose = logic.getCoinsForChoose();
        if (!coinsForChoose.isEmpty()) {
            Coin chosenCoin = logic.chooseCoinToSiteToHand(activePlayer, coinsForChoose.get(0));
            updateCoinsTaken(activePlayer, chosenCoin);
            // update UI with Coins available to choose from field
        }

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(PAUSE_BETWEEN_MOVES));
        pauseTransition.setOnFinished(event -> {
            processingPlayersLeftInRound--;
            processPlayers(activePlayer);
        });
        pauseTransition.play();
    }

    private void handleRegularPlayerFieldChoice(Player activePlayer) {
        List<Coin> coinsForChoose = logic.getCoinsForChoose();
        // Disable all buttons that can not be chosen
        for (Button button : coinsToChooseFromField) {
            button.setOnAction(null);
            button.setDisable(true);
            for (Coin coin : coinsForChoose) {
                if (button.getText().equals(String.valueOf(coin.getNumber()))) {
                    button.setDisable(false);
                }
            }

        }
        System.out.println("====================== Active Player  =================================");
        System.out.printf(processingPlayersLeftInRound + " players left\n");

        for (Button button : coinsToChooseFromField) {
            button.setOnAction(event -> {
                if (!coinsForChoose.isEmpty()) {
                    for (Button button2 : coinsToChooseFromField) {
                        button2.setDisable(true);
                    }
                    Coin chosenCoin = null;
                    for (Coin coin : coinsForChoose) {
                        if (button.getText().equals(String.valueOf(coin.getNumber()))) {
                            chosenCoin = coin;
                            break;
                        }
                    }
                    logic.chooseCoinToSiteToHand(activePlayer, chosenCoin); //0 is just example
                    updateCoinsTaken(activePlayer, chosenCoin);

//                    for (Button button2 : coinsToChooseFromField) {
//                        button2.setDisable(true);
//                    }
                }

                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(PAUSE_BETWEEN_MOVES));
                pauseTransition.setOnFinished(event2 -> {
                    processingPlayersLeftInRound--;
                    processPlayers(activePlayer);
                });
                pauseTransition.play();
            });
        }
    }


    public boolean isNPC(Player player) {

        if (player instanceof NPC) {
            // handleNPCPlayer((NPC) player);
            return true;
        } else {
            // handleRegularPlayer(player, coin);
            return false;
        }

    }


    private void updateWonCoins(List<Label> wonCoins, Coin coin) {
        for (Label label : wonCoins) {
            if (label.getText().isEmpty()) {
                label.setText(String.valueOf(coin.getNumber()));
                label.setStyle(String.format("-fx-text-fill: %s;", coin.getColor()));
                break;
            }
        }
    }


    private void processWinnerOfRoundPlayer(Player player) {
        List<Coin> coinsForChoose = logic.getCoinsForChoose();
        if (!coinsForChoose.isEmpty()) {

            // reset UI for Coins on the Field to choose from, meaning set the text to empty
            if (player.getPlayerNumber() != 1) {
                Coin chosenCoin = logic.chooseCoinToSiteToHand(player, coinsForChoose.get(0));

                updateCoinsTaken(player, chosenCoin);

                switch (player.getPlayerNumber()) {
                    case 2: updateWonCoins(coinsTakenPlayer2, chosenCoin); break;
                    case 3: updateWonCoins(coinsTakenPlayer3, chosenCoin); break;
                    case 4: updateWonCoins(coinsTakenPlayer4, chosenCoin); break;
                }

                PauseTransition pause = new PauseTransition(Duration.seconds(PAUSE_BETWEEN_MOVES));
                pause.setOnFinished(e -> {
                    processPlayers(player);
                });
                pause.play();
            }
            else if (player.getPlayerNumber() == 1) {
                // Disable all buttons that can not be chosen
                for (Button button : coinsToChooseFromField) {
                    button.setOnAction(null);
                    button.setDisable(true);
                    for (Coin coin : coinsForChoose) {
                        if (button.getText().equals(String.valueOf(coin.getNumber()))) {
                            button.setDisable(false);
                        }
                    }

                }
                System.out.println("====================== Active Player  =================================");
                System.out.printf(processingPlayersLeftInRound + " players left\n");

                for (Button button : coinsToChooseFromField) {
                    button.setOnAction(event -> {
                        if (!coinsForChoose.isEmpty()) {
                            for (Button button2 : coinsToChooseFromField) {
                                button2.setDisable(true);
                            }
                            Coin chosenCoin = null;
                            for (Coin coin : coinsForChoose) {
                                if (button.getText().equals(String.valueOf(coin.getNumber()))) {
                                    chosenCoin = coin;
                                    break;
                                }
                            }
                            logic.chooseCoinToSiteToHand(player, chosenCoin); //0 is just example
                            updateCoinsTaken(player, chosenCoin);
                            updateWonCoins(coinsTakenPlayer1, chosenCoin);
                            PauseTransition pause = new PauseTransition(Duration.seconds(PAUSE_BETWEEN_MOVES));
                            pause.setOnFinished(e -> {
                                processPlayers(player);
                            });
                            pause.play();
                        }

                    });
                }

            }
        }
        //0 is just example


    }

    private Player getPlayerByPlayerNumber(int playerNumber) {
        for (Player player : logic.getPlayers()) {
            if (player.getPlayerNumber() == playerNumber) {
                return player;
            }
        }
        System.out.println("Returned NULL");
        return null;
    }

    private  void processPlayers(Player player) {
        if (processingPlayersLeftInRound <= 0) {
            if (logic.shouldEndGameBasedOnPlayerConditions()) {

                // show results
                //onGameOver();

                CustomPair<Player, Integer> win = logic.checkWinningPlayer();
               playerWhoWonTheGame = win.getKey().getPlayerNumber();

                toggleVisibilityForAllPlayers(numberOfPlayers);

                GameOverButton.setVisible(!GameOverButton.isVisible());



                System.out.printf("Game over\n");
            } else {
                // If not over, start next round
                turnsLeftInRound = numberOfPlayers;
                logic.startNextRound();
                nextTurn();  // Begin the new round’s first turn
            }

           return;
        }

        Player activePlayer = switch (player.getPlayerNumber()) {
            case 1 -> getPlayerByPlayerNumber(numberOfPlayers);
            case 2 -> getPlayerByPlayerNumber(1);
            case 3 -> getPlayerByPlayerNumber(2);
            case 4 -> getPlayerByPlayerNumber(3);
            default -> null;
        };

        if (activePlayer.getPlayerNumber() != 1) {
            handleNPCFieldChoice((NPC) activePlayer);
        }
        else if (activePlayer.getPlayerNumber() == 1) {
            handleRegularPlayerFieldChoice(activePlayer);
        }



    }


    // Game Logic


    public void nextTurn() {

        if (turnsLeftInRound <= 0) {
            System.out.printf("End of round is called \n");
            endRoundAndCheckGameOver();
            return;
        }
        if (turnsLeftInRound == numberOfPlayers) {
            System.out.printf("Starting new round\n");
        }
            // Play a round

        Player activePlayer;
        if (turnsLeftInRound == numberOfPlayers) {
            activePlayer = logic.getNextActivePlayer(roundWinner);
        }
        else {
            activePlayer = logic.getNextActivePlayer(null);
        }


        System.out.printf("Active player: %d\n", activePlayer.getPlayerNumber());
        System.out.printf("Turn left in Round: %d\n", turnsLeftInRound);

        if (activePlayer instanceof NPC) {
            System.out.printf("NPC is Active!!!");
            handleNPCPlayer((NPC) activePlayer);
        } else {
            System.out.printf("Player is Active!!!");
            handleRegularPlayer(activePlayer);
        }



        /*
            // Determine and process the winner
            Player winnerOfRound = logic.getWinnerOfRound();
            System.out.println(">>> Winner of Round: " + winnerOfRound);

            processWinnerOfRoundPlayer(winnerOfRound);
            processPlayers();
            // Check end game condition after player processing
            if (logic.shouldEndGameBasedOnPlayerConditions()) {
                System.out.println("\n##### Game Over #####");
                CustomPair<Player, Integer> win = logic.checkWinningPlayer();

                System.out.println("WINNER OF GAME: Player: " + win.getKey() + " with sum of coins on hand and on side: " + win.getValue());
                playerWhoWonTheGame = win.getKey().getPlayerNumber();

                printAllPlayers();
                toggleVisibilityForAllPlayers(numberOfPlayers);
                GameOverButton.setVisible(!GameOverButton.isVisible());
                //break;  // Exit the loop if the game should end
            } else {
                System.out.println("**************Start round*******************  NR:" + logic.getActualRoundNumber());
                logic.startNextRound();
              // nextTurn();
            }*/

            // Start the next round
//            System.out.println("**************Start round*******************  NR:" + logic.getActualRoundNumber());
//            logic.startNextRound();
//


    }


    // initializing data from received from previous windows and play game logic
    public void initData(int numberOfPlayers, boolean difficulty, int resolutionXValue, int resolutionYValue) throws IOException {
        // data received from previous windows
        this.numberOfPlayers = numberOfPlayers;
        this.difficulty = difficulty;

        this.resolutionXValue = resolutionXValue;
        this.resolutionYValue = resolutionYValue;

        turnsLeftInRound = numberOfPlayers;

        // initializing Lists for the corresponding Coin spots
        // Player1 coins in hand
        coinsOnHandPlayer1 = Arrays.asList(Player1Coin1, Player1Coin2, Player1Coin3, Player1Coin4);

        coinsOnHandPlayer2 = Arrays.asList(Player2Coin1, Player2Coin2, Player2Coin3, Player2Coin4);
        coinsOnHandPlayer3 = Arrays.asList(Player3Coin1, Player3Coin2, Player3Coin3, Player3Coin4);
        coinsOnHandPlayer4 = Arrays.asList(Player4Coin1, Player4Coin2, Player4Coin3, Player4Coin4);


        // Players Lists of coins Taken (moved to FieldOfWonCoins)

        coinsTakenPlayer1 = Arrays.asList(Player1CoinTaken1, Player1CoinTaken2, Player1CoinTaken3, Player1CoinTaken4);
        coinsTakenPlayer2 = Arrays.asList(Player2CoinTaken1, Player2CoinTaken2, Player2CoinTaken3, Player2CoinTaken4) ;
        coinsTakenPlayer3  = Arrays.asList(Player3CoinTaken1, Player3CoinTaken2, Player3CoinTaken3, Player3CoinTaken4);
        coinsTakenPlayer4  = Arrays.asList(Player4CoinTaken1, Player4CoinTaken2, Player4CoinTaken3, Player4CoinTaken4);

        // Coins on the field, available to choose
        coinsToChooseFromField  = Arrays.asList(FieldButton1, FieldButton2, FieldButton3, FieldButton4);

        for (Button button : coinsToChooseFromField) {
            button.setDisable(true);
        }

        for (Button button : coinsOnHandPlayer1) {
            button.setDisable(true);
        }


        // turn off visibility of GameOverButton at the beginning of the game
        GameOverButton.setVisible(!GameOverButton.isVisible());

        // toggle visibility of player 4 off if only 3 players are playing
        if (numberOfPlayers == 3) {
            toggleVisibilityForPlayer4();
        }
//        System.out.printf("number of Players: %d\n", numberOfPlayers);
//        logic = new TestLogic(numberOfPlayers);
//        logic.initializeGame(numberOfPlayers);
//     initializeStartingHand();
//        initializeField();
        // run game logic

        logic.setHardMode(difficulty);
        logic.setAmountPlayers(numberOfPlayers);

        logic.createPlayers();
        logic.createCoins();

        logic.distributeCoin();

        initializeStartingHand();

        logic.startNextRound();//init first round
        nextTurn();

/*        if (isHandEmpty) {
            toggleVisibilityForAllPlayers(numberOfPlayers);
            GameOverButton.setVisible(!GameOverButton.isVisible());


        }*/

    }

    private void endRoundAndCheckGameOver() {
        // 1. Evaluate the round winner, do coin distribution, etc.
        processingPlayersLeftInRound = numberOfPlayers - 1;
        Player winner = logic.getWinnerOfRound();
        roundWinner = winner;
        processWinnerOfRoundPlayer(winner);

        // 2. Check if the game ends

    }

    public void onGameOverSwitchToGameFinished(ActionEvent event) throws IOException {

        //        gameMode.setThreePlayerMode(true);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


        List<CustomPair<Player, Integer>> playersSum = logic.calculateTotalCoinSumsForPlayers();
        for (CustomPair<Player, Integer> pair : playersSum) {
            if (pair.getKey().getPlayerNumber() == 1) {
                player1Total = pair.getValue();
            } else if (pair.getKey().getPlayerNumber() == 2) {
                player2Total = pair.getValue();
            } else if (pair.getKey().getPlayerNumber() == 3) {
                player3Total = pair.getValue();
            } else if (pair.getKey().getPlayerNumber() == 4) {
                player4Total = pair.getValue();
            }


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/game-finished.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
            Parent root = loader.load();


            GameFinishedController gameFinishedController = loader.getController();
            gameFinishedController.initData(player1Total, player2Total, player3Total, player4Total, numberOfPlayers, playerWhoWonTheGame, resolutionXValue, resolutionYValue);

            logic.resetLogic();
            Logic.resetInstance();

            scene = new Scene(root, resolutionXValue, resolutionYValue);
            stage.setScene(scene);
            stage.show();




        }
    }

}
