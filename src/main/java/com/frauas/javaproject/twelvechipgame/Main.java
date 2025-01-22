package com.frauas.javaproject.twelvechipgame;

import com.frauas.javaproject.twelvechipgame.gamecomponets.Coin;
import com.frauas.javaproject.twelvechipgame.gamecomponets.Player;
import com.frauas.javaproject.twelvechipgame.temp.CustomPair;
import com.frauas.javaproject.twelvechipgame.temp.ILogic;
import com.frauas.javaproject.twelvechipgame.temp.Logic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

/*public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));

        // Create the Scene (adjust the size as needed)
        Scene mainMenuScene = new Scene(root);

        // Set up the Stage
        stage.setTitle("Twelve Chip Game");
        stage.setScene(mainMenuScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }*/
//}
public class Main {
    private static ILogic logic = Logic.getInstance();

    public static void main(String[] args) {


        logic.setHardMode(true);
        logic.setAmountPlayers(4);

        logic.createPlayers();
        logic.createCoins();

        logic.distributeCoin();

        logic.startNextRound();//init first round

        while (true) {
            // Play a round
            playRound();
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
                printAllPlayers();
                break;  // Exit the loop if the game should end
            }

            // Start the next round
            System.out.println("**************Start round*******************  NR:" + logic.getActualRoundNumber());
            logic.startNextRound();
        }
    }

    private static void printAllPlayers() {
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
            System.out.println("Player "+pair.getKey().getPlayerNumber()+" sum of coins: "+pair.getValue());
        }
        System.out.println("\n--- End of Players with Sums  ---\n");
    }

    private static void playRound() {
        for (int i = 0; i < logic.getPlayers().size(); i++) {

            Player activePlayer = logic.getNextActivePlayer();

            System.out.println("Active player: " + activePlayer.toString());
            System.out.println("Active player type : " + activePlayer.getClass().getName());

            List<Coin> coins = activePlayer.getCoinsOnHand();
            if (!coins.isEmpty()) {
                logic.playCoin(activePlayer, coins.get(0));//0 is just example
            }

        }
    }

    private static void processWinnerOfRoundPlayer(Player player) {
        List<Coin> coinsForChoose = logic.getCoinsForChoose();
        if (!coinsForChoose.isEmpty()) {
            logic.chooseCoinToSiteToHand(player, coinsForChoose.get(0));//0 is just example
        }
    }

    private static void processPlayers() {
        for (int i = 0; i < logic.getPlayers().size(); i++) {
            Player activePlayer = logic.getNextActivePlayer();
            if (activePlayer == null) {
                return;
            }
            List<Coin> coinsForChoose = logic.getCoinsForChoose();
            if (!coinsForChoose.isEmpty()) {
                logic.chooseCoinToSiteToHand(activePlayer, coinsForChoose.get(0)); //0 is just example
            }

        }
    }
}