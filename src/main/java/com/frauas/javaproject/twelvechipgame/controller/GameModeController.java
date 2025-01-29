package com.frauas.javaproject.twelvechipgame.controller;

import com.frauas.javaproject.twelvechipgame.gamecomponets.Coin;
import com.frauas.javaproject.twelvechipgame.gamecomponets.NPC;
import com.frauas.javaproject.twelvechipgame.gamecomponets.Player;
import com.frauas.javaproject.twelvechipgame.gamelogic.CustomPair;
import com.frauas.javaproject.twelvechipgame.gamelogic.Logic;

import com.frauas.javaproject.twelvechipgame.gamelogic.Round;
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


    //Button to change scene to Game Finished scene
    @FXML
    public Button GameOverButton;

    // Needed to disable the anchorPane after game is over and enable after the GameOverButton is clicked
    public javafx.scene.layout.VBox vBox;

    // Window resolution
    int resolutionXValue = 1280;
    int resolutionYValue = 720;

    // Window creation
    private Stage stage;
    private Scene scene;

    private int numberOfPlayers;


    // Safes the total sum of coins players have
    private int player1Total = 0;
    private int player2Total = 0;
    private int player3Total = 0;
    private int player4Total = 0;

    // Safes the player who won the game
    private int playerWhoWonTheGame = 0;

    // Counts how many players need to still play a coin
    private int turnsLeftInRound = 0;
    // Counts how many players still need to pick up a coin
    private int processingPlayersLeftInRound = 0;

    // Wait time between moves. Used to let player1 see which coins where chosen by which NPC (This information is not otherwise given to player1 to simulate real world game play)
    private final int PAUSE_BETWEEN_MOVES = 1;

    // Create the instance of the logic class;
    private static Logic logic = (Logic) Logic.getInstance();


    // List of Buttons and Labels to assign the coins on hand to
    List<Button> coinsOnHandPlayer1;
    List<Label> coinsOnHandPlayer2;
    List<Label> coinsOnHandPlayer3;
    List<Label> coinsOnHandPlayer4;


    // Players Lists of coins Taken (moved to FieldOfWonCoins)

    List<Label> coinsTakenPlayer1;
    List<Label> coinsTakenPlayer2;
    List<Label> coinsTakenPlayer3;
    List<Label> coinsTakenPlayer4;

    // Coins on the field, available to choose from
    List<Button> coinsToChooseFromField;

    // Saving the round winner to pass on to the Logic class
    Player roundWinner = null;



    // initializing data received from previous windows and play game logic
    public void initData(int numberOfPlayers, boolean difficulty, int resolutionXValue, int resolutionYValue) throws IOException {
        // data received from previous windows
        this.numberOfPlayers = numberOfPlayers;

        this.resolutionXValue = resolutionXValue;
        this.resolutionYValue = resolutionYValue;

        // Responsible to track how many player still need to play a coin from hand
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

        // Coins on the field, available to choose from
        coinsToChooseFromField  = Arrays.asList(FieldButton1, FieldButton2, FieldButton3, FieldButton4);

        // Disable all Buttons
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


        // Initialize the Logic
        logic.setHardMode(difficulty);
        logic.setAmountPlayers(numberOfPlayers);

        logic.createPlayers();
        logic.createCoins();

        logic.distributeCoin();

        // Update starting hand
        initializeStartingHand();

        // Start the first Round
        logic.startNextRound();//init first round
        nextTurn();


    }



    //--------------- Methods to toggle the Labels and Buttons of the Game Window -------------------

    private void toggleVisibilityForPlayer4() {

        Player4Label.setVisible(!Player4Label.isVisible());

        for (Label handLabel : coinsOnHandPlayer4) {
            handLabel.setVisible(!handLabel.isVisible());
        }
        for (Label takenLabel : coinsTakenPlayer4) {
            takenLabel.setVisible(!takenLabel.isVisible());
        }

    }

    private void toggleVisibilityForPlayer1() {

        Player1Label.setVisible(!Player1Label.isVisible());

        for (Button handButton : coinsOnHandPlayer1) {
            handButton.setVisible(!handButton.isVisible());
        }
        for (Label takenLabel : coinsTakenPlayer1) {
            takenLabel.setVisible(!takenLabel.isVisible());
        }

    }

    private void toggleVisibilityForPlayer2() {

        Player2Label.setVisible(!Player2Label.isVisible());

        for (Label handLabel : coinsOnHandPlayer2) {
            handLabel.setVisible(!handLabel.isVisible());
        }
        for (Label takenLabel : coinsTakenPlayer2) {
            takenLabel.setVisible(!takenLabel.isVisible());
        }

    }

    private void toggleVisibilityForPlayer3() {

        Player3Label.setVisible(!Player3Label.isVisible());

        for (Label handLabel : coinsOnHandPlayer3) {
            handLabel.setVisible(!handLabel.isVisible());
        }
        for (Label takenLabel : coinsTakenPlayer3) {
            takenLabel.setVisible(!takenLabel.isVisible());
        }

    }

    private void toggleVisibilityForFieldButtons() {

        for (Button fieldButton : coinsToChooseFromField) {
            fieldButton.setVisible(!fieldButton.isVisible());
        }

    }

    private void toggleVisibilityForAllPlayersAndFieldButtons(int numberOfPlayers) {
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



    //------------ Methods to initialize the corresponding UI elements with coins -------------

    // update the UI with the coins the players have at the beginning of the game
    private void initializeStartingHand() {

        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < 4; j++) {
                initializeStartingHandHelper(logic.getPlayers().get(i), logic.getPlayers().get(i).getCoinsOnHand().get(j));
            }
        }
    }

    // Helper method to initialize the starting hands
    private void initializeStartingHandHelper(Player player, Coin coin) {

        String color = coin.getColor().toString();

        switch (player.getPlayerNumber()) {
            case 1:
                updateStartingHandsUI( coinsOnHandPlayer1, coin,"-fx-text-fill: " + color + ";");
                break;
            case 2:
                updateStartingHandsUI(coinsOnHandPlayer2, coin,"-fx-text-fill: " + color + ";" + "-fx-background-color: " + color + ";");
                break;
            case 3:
                updateStartingHandsUI(coinsOnHandPlayer3, coin,"-fx-text-fill: " + color + ";" + "-fx-background-color: " + color + ";");
                break;
            case 4:
                updateStartingHandsUI(coinsOnHandPlayer4, coin,"-fx-text-fill: " + color + ";" + "-fx-background-color: " + color + ";");
                break;
        }
    }


    // Initialize the starting hands
    private <T extends Labeled> void updateStartingHandsUI(List<T> hand, Coin coin, String style ) {

        for (var handSlot : hand) {

            if (handSlot.getText().isEmpty()) {
                handSlot.setText(Integer.toString(coin.getNumber()));
                handSlot.setStyle(style);
                return;
            }
        }
    }

    // ------- Methods to Update the Game UI during Game Loop ---------


    // Calls removeCoinFromHandUI to update the corresponding Lists of Buttons/ Labels
    private void updateHandOnPlay(Player player, Coin coin) {
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


    // Removes one Coin from the Hand UI by setting the button/ label to empty and resetting the background color after playing the Coin
    private <T extends Labeled> void removeCoinFromHandUI(List<T> list, Coin coin) {
        for (T object: list) {
            if (object.getText().equals(String.valueOf(coin.getNumber()))) {
                object.setStyle("");
                object.setText("");
                break;
            }
        }
    }



    private void updateCoinsTaken(Player player, Coin coin) {
        Button button = null;

        // Find the FieldButton corresponding to the passed coin
        for (Button btn : coinsToChooseFromField) {
            if (btn.getText().equals(String.valueOf(coin.getNumber()))) {
                button = btn;
                break;
            }
        }

        // Put the given Coin to the given players hand UI and remove it from the field UI
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

    // Clears the given played Coin from the field UI
    private void clearFieldButton(Button button) {
        button.setStyle("");
        button.setText("");
    }

    // Redraws the hand UI
    private <T extends Labeled> void updateHandUI(List<T> hand, List<Coin> coins) {

    // Draw an empty hand UI
        for (T object : hand) {
            object.setStyle("");
            object.setText("");
        }
        // Place the Coins onto the hand UI one by one
        // for Labels
        for (int i = 0; i < coins.size(); i++) {
            if (hand.get(i) instanceof Label object) {
                object.setText(Integer.toString(coins.get(i).getNumber()));
                object.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", coins.get(i).getColor(), coins.get(i).getColor()));

        // For Buttons
            } else if (hand.get(i) instanceof Button object) {
                object.setText(Integer.toString(coins.get(i).getNumber()));
                object.setStyle(String.format(" -fx-text-fill: %s;", coins.get(i).getColor()));
            }
        }
    }


    // Get the Coins played inside the Round Class to assign to the corresponding FieldButtons
    private void updateCoinsOnPlayingField() {

        Round round = logic.getRound();
        for (CustomPair<Player, Coin> pair : round.getPlayedCoins()) {
            Player player = pair.getKey();
            Coin coin = pair.getValue();

            switch (player.getPlayerNumber()) {
                case 1:
                    updateFieldButton(FieldButton1, coin);
                    break;
                case 2:
                    updateFieldButton(FieldButton2, coin);
                    break;
                case 3:
                    updateFieldButton(FieldButton3, coin);
                    break;
                case 4:
                    updateFieldButton(FieldButton4, coin);
                    break;
            }
        }
    }

    // Update the given FieldButton UI to reflect the Coin that was placed on it
    private void updateFieldButton(Button button, Coin coin) {

        button.setText(Integer.toString(coin.getNumber()));
        button.setStyle(String.format("-fx-text-fill: %s;", coin.getColor()));
    }


    // Update won Coins
    // Adds the given Coin to the corresponding won Coins area in the UI
    private void updateWonCoins(List<Label> wonCoins, Coin coin) {
        for (Label label : wonCoins) {
            if (label.getText().isEmpty()) {
                label.setText(String.valueOf(coin.getNumber()));
                label.setStyle(String.format("-fx-text-fill: %s;", coin.getColor()));
                break;
            }
        }
    }


    // ------------------------ Methods to handle game flow ----------------------------------



    // Lets the next Player play Cooin from the hand
    public void nextTurn() {

        // Stops the recursion
        if (turnsLeftInRound <= 0) {

            // All Players have played a coin
            endRoundAndCheckGameOver();
            return;
        }

        Player activePlayer;

        // Winner of the last round starts the first round
        if (turnsLeftInRound == numberOfPlayers) {
            activePlayer = logic.getNextActivePlayer(roundWinner);
        }
        // Every other turn is decided by the Logic Class (Clockwise)
        else {
            activePlayer = logic.getNextActivePlayer(null);
        }

        // Let NPC play Coin
        if (activePlayer instanceof NPC) {
            handleNPCPlayer((NPC) activePlayer);
        } else {
            // Let Player play Coin
            handleRegularPlayer(activePlayer);
        }
    }



    private void processWinnerOfRoundPlayer(Player player) {
        List<Coin> coinsForChoose = logic.getCoinsForChoose();
        if (!coinsForChoose.isEmpty()) {

            // This gets called if the NPC has won the round
            if (player.getPlayerNumber() != 1) {
                Coin chosenCoin = logic.chooseCoinToSiteToHand(player, coinsForChoose.get(0));

                // Update the corresponding UI
                updateCoinsTaken(player, chosenCoin);

                switch (player.getPlayerNumber()) {
                    case 2: updateWonCoins(coinsTakenPlayer2, chosenCoin); break;
                    case 3: updateWonCoins(coinsTakenPlayer3, chosenCoin); break;
                    case 4: updateWonCoins(coinsTakenPlayer4, chosenCoin); break;
                }

                // Wait for PAUSE_BETWEEN_SECONDS seconds and let the next player takes their Coin from the field
                PauseTransition pause = new PauseTransition(Duration.seconds(PAUSE_BETWEEN_MOVES));
                pause.setOnFinished(e -> {
                    processPlayers(player);
                });
                pause.play();
            }

            // This gets called if the Player has won the round
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

                // Sets the Event Listener for the field buttons
                for (Button button : coinsToChooseFromField) {
                    button.setOnAction(event -> {
                        if (!coinsForChoose.isEmpty()) {
                            // Disables all buttons to prevent double-clicking
                            for (Button button2 : coinsToChooseFromField) {
                                button2.setDisable(true);
                            }
                            // Gets the clicked Coin
                            Coin chosenCoin = null;
                            for (Coin coin : coinsForChoose) {
                                if (button.getText().equals(String.valueOf(coin.getNumber()))) {
                                    chosenCoin = coin;
                                    break;
                                }
                            }
                            // Tells the Logic Class which coin was chosen
                            logic.chooseCoinToSiteToHand(player, chosenCoin);

                            // Updates the UI
                            updateCoinsTaken(player, chosenCoin);
                            updateWonCoins(coinsTakenPlayer1, chosenCoin);

                            // Wait for PAUSE_BETWEEN_SECONDS seconds and let the next player take a Coin from the field
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
    }


    // Handles the picking of Coins from the field for the players that have lost the round and checks for Game Over
    private  void processPlayers(Player player) {
        // Stops the recursion
        if (processingPlayersLeftInRound <= 0) {

            // On Game Over
            if (logic.shouldEndGameBasedOnPlayerConditions()) {

                // Get the winning Player
                CustomPair<Player, Integer> win = logic.checkWinningPlayer();
                playerWhoWonTheGame = win.getKey().getPlayerNumber();

                // Turns visibility of UI elements off and disables them
                toggleVisibilityForAllPlayersAndFieldButtons(numberOfPlayers);
                vBox.setDisable(true);

                // Turns visibility of Game Over Button on
                GameOverButton.setVisible(!GameOverButton.isVisible());

            } else {
                // Start next round
                turnsLeftInRound = numberOfPlayers;
                logic.startNextRound();
                nextTurn();
            }

            return;
        }

        // Gets the next active Player
        Player activePlayer = switch (player.getPlayerNumber()) {
            case 1 -> getPlayerByPlayerNumber(numberOfPlayers);
            case 2 -> getPlayerByPlayerNumber(1);
            case 3 -> getPlayerByPlayerNumber(2);
            case 4 -> getPlayerByPlayerNumber(3);
            default -> null;
        };

//        Player activePlayer = logic.getNextActivePlayer(null);

        // Lets an NPC player pick a Coin from the field
        if (activePlayer.getPlayerNumber() != 1) {
            handleNPCFieldChoice((NPC) activePlayer);
        }
        // Lets a Player pick a Coin from the field
        else if (activePlayer.getPlayerNumber() == 1) {
            handleRegularPlayerFieldChoice(activePlayer);
        }

    }


    // gets Player by their player number
    private Player getPlayerByPlayerNumber(int playerNumber) {
        for (Player player : logic.getPlayers()) {
            if (player.getPlayerNumber() == playerNumber) {
                return player;
            }
        }

        return null;
    }



    // Lets the NPC play a coin from hand and updates the UI accordingly
    // This method mediates between the Logic Class and the Game UI
    private void handleNPCPlayer(NPC npc) {


        Coin playedCoin = logic.playCoinForNPC(npc);


        // update the Game UI
        updateHandOnPlay(npc, playedCoin);
        updateCoinsOnPlayingField();

        // Wait for the PAUSE_BETWEEN_MOVES seconds and allow the next Player to proceed afterward
        PauseTransition pause = new PauseTransition(Duration.seconds(PAUSE_BETWEEN_MOVES));
        pause.setOnFinished(e -> {
            // after the pause, proceed
            turnsLeftInRound--;
            nextTurn();
        });
        pause.play();


    }

    private void handleRegularPlayer(Player player) throws IllegalArgumentException {

        // Clear old handlers and disable empty hand buttons
        for (Button button : coinsOnHandPlayer1) {
            button.setOnAction(null);
            if (!button.getText().isEmpty()) {
                button.setDisable(false);
            }
        }

        // Setup new event handlers for each coin
        for (Button button : coinsOnHandPlayer1) {
            button.setOnAction(event -> {
                int clickedValue = Integer.parseInt(button.getText());
                // Find that coin in player's hand

                for (int i = 0; i < player.getCoinsOnHand().size(); i++) {
                    if (player.getCoinsOnHand().get(i).getNumber() == clickedValue) {
                        // Handle the logic side of things
                        Coin coinToPlay = player.getCoinsOnHand().get(i);


                        logic.playCoinForPlayer(player, coinToPlay);

                        // Update the UI
                        updateHandOnPlay(player, coinToPlay);
                        updateCoinsOnPlayingField();
                        System.out.println(">>>  " + "Player " + 1 + " plays " + coinToPlay);

                        break;
                    }
                }

                // Disable all buttons so they can not be double-clicked
                for (Button button2 : coinsOnHandPlayer1) {
                    button2.setDisable(true);
                }
                // Wait for PAUSE_BETWEEN_SECONDS seconds and let the next player take their turn
                PauseTransition pause = new PauseTransition(Duration.seconds(PAUSE_BETWEEN_MOVES));
                pause.setOnFinished(e -> {
                    turnsLeftInRound--;

                    nextTurn();
                });
                pause.play();

            });
        }
    }

    // Lets the NPC pick a coin from the field
    private void handleNPCFieldChoice(Player activePlayer) {

        List<Coin> coinsForChoose = logic.getCoinsForChoose();
        if (!coinsForChoose.isEmpty()) {

            // NPC picks a coin
            Coin chosenCoin = logic.chooseCoinToSiteToHand(activePlayer, coinsForChoose.get(0));
            // update UI with Coins available to choose from field
            updateCoinsTaken(activePlayer, chosenCoin);

        }

        // Wait for PAUSE_BETWEEN_SECONDS seconds and let the next player take their Coin from the field
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(PAUSE_BETWEEN_MOVES));
        pauseTransition.setOnFinished(event -> {
            processingPlayersLeftInRound--;
            processPlayers(activePlayer);
        });
        pauseTransition.play();
    }

    // Lets the Player pick a coin from the field
    private void handleRegularPlayerFieldChoice(Player activePlayer) {
        List<Coin> coinsForChoose = logic.getCoinsForChoose();

        // Disable all field buttons that can not be chosen
        for (Button button : coinsToChooseFromField) {
            button.setOnAction(null);
            button.setDisable(true);
            for (Coin coin : coinsForChoose) {
                if (button.getText().equals(String.valueOf(coin.getNumber()))) {
                    button.setDisable(false);
                }
            }

        }

        // Event Listener for the Buttons
        for (Button button : coinsToChooseFromField) {
            button.setOnAction(event -> {
                if (!coinsForChoose.isEmpty()) {
                    // Disable all field buttons after a click to prevent double-clicking
                    for (Button button2 : coinsToChooseFromField) {
                        button2.setDisable(true);
                    }

                    // Get the choosen Coin
                    Coin chosenCoin = null;
                    for (Coin coin : coinsForChoose) {
                        if (button.getText().equals(String.valueOf(coin.getNumber()))) {
                            chosenCoin = coin;
                            break;
                        }
                    }

                    // Pass the chosen Coin to the Logic Class
                    logic.chooseCoinToSiteToHand(activePlayer, chosenCoin);
                    // Update the UI
                    updateCoinsTaken(activePlayer, chosenCoin);

                }

                // Wait for PAUSE_BETWEEN_SECONDS seconds and let the next player take a coin from the field
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(PAUSE_BETWEEN_MOVES));
                pauseTransition.setOnFinished(event2 -> {
                    processingPlayersLeftInRound--;

                    processPlayers(activePlayer);
                });
                pauseTransition.play();
            });
        }
    }


    private void endRoundAndCheckGameOver() {
        // Keeps track of how many players still need to choose a coin from the field
        processingPlayersLeftInRound = numberOfPlayers - 1;

        // Gets the winner and handles them
        Player winner = logic.getWinnerOfRound();
        roundWinner = winner;
        processWinnerOfRoundPlayer(winner);


    }


    // ------------ Method to switch Game Finished window

    // Switch to the Finished Game window
    public void onGameOverSwitchToGameFinished(ActionEvent event) throws IOException {

        // Sets the stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Calculates the Sum of Coins for each Player and saves them in the corresponding variables
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


            // Loads the game-finished fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/game-finished.fxml"));
            Parent root = loader.load();

            // Gets the Controller for the GameFinishedController and passes the variables for the Sums of Coins for the corresponding Player as well as the numberOfPlayers, the player who won the game and the Window resolution to the Game Finished window
            GameFinishedController gameFinishedController = loader.getController();
            gameFinishedController.initData(player1Total, player2Total, player3Total, player4Total, numberOfPlayers, playerWhoWonTheGame, resolutionXValue, resolutionYValue);

            // Disables the Vbox with the game UI elements to make it possible to click on the Game Over Button
            vBox.setDisable(false);

            // Resets the logic
            logic.resetLogic();
            Logic.resetInstance();

            // Creates the Game Finished Window
            scene = new Scene(root, resolutionXValue, resolutionYValue);
            stage.setScene(scene);
            stage.show();

        }
    }

}
