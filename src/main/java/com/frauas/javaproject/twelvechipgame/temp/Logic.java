package com.frauas.javaproject.twelvechipgame.temp;


import com.frauas.javaproject.twelvechipgame.gamecomponets.Coin;
import com.frauas.javaproject.twelvechipgame.gamecomponets.NPC;
import com.frauas.javaproject.twelvechipgame.gamecomponets.Player;
import com.frauas.javaproject.twelvechipgame.gamecomponets.coinColors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Logic implements ILogic {

    private static Logic instance = null;
    private List<CustomPair<Player, Boolean>> players = new ArrayList<>();
    private int amountPlayers;
    private List<Coin> coins;
    private Round round = null;
    private int roundIndex = 0;
    private int activePlayerIndex = 0;
    private boolean isHardMode;

    private Logic() {
    }

    /**
     * It will allowed just one logic instance to be created.
     */
    public static ILogic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    /**
     * Set amount of players.
     */
    @Override
    public void setAmountPlayers(int amountPlayers) {
        this.amountPlayers = amountPlayers;
    }

    @Override
    public void setHardMode(boolean isHardMode) {
        this.isHardMode = isHardMode;
    }

    /**
     * create list of players
     */
    @Override
    public void createPlayers() {
        players.add(new CustomPair<Player, Boolean>(new Player(1), FALSE)); // Adding the main player
        for (int i = 1; i < amountPlayers; i++) {
            players.add(new CustomPair<Player, Boolean>(new NPC(i + 1, isHardMode), FALSE));
        }
    }

    /**
     * return list of all players in the game
     */
    @Override
    public List<Player> getPlayers() {
        return players.stream()
                .map(CustomPair::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Create a list of coins
     */
    @Override
    public void createCoins() throws Exception {
        List<Integer> redNumbers = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8, 9));
        List<Integer> blueNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 10, 11, 12));

        // Check if the amount of players is more than 3, then add additional numbers
        if (amountPlayers > 3) {
            redNumbers.addAll(Arrays.asList(9, 4));  // Add extra red coins
            blueNumbers.addAll(Arrays.asList(10, 3));  // Add extra blue coins
        }

        coins = Coin.initializeCoins(amountPlayers);

        if (coins == null) throw new Exception("Null list coins");
        Collections.shuffle(coins);
    }

    /**
     * give to every players the own coins.
     */
    @Override
    public void distributeCoin() {
        // Collect a specific number of red and blue coins based on player requirements
        List<Coin> redCoins = coins.stream()
                .filter(c -> c.getColor() == coinColors.RED)
                .collect(Collectors.toList());
        List<Coin> blueCoins = coins.stream()
                .filter(c -> c.getColor() == coinColors.BLUE)
                .collect(Collectors.toList());

        for (CustomPair<Player, Boolean> player : players) {

            // Give each player 2 red and 2 blue coins
            List<Coin> redCoinsToDistribute = new ArrayList<>(redCoins.subList(0, 2));
            List<Coin> blueCoinsToDistribute = new ArrayList<>(blueCoins.subList(0, 2));

            // Add coins to player's hand
            for (Coin coin : redCoinsToDistribute) {
                coin.setOwner(player.key.getPlayerNumber());
                player.getKey().takeCoin(coin);
            }
            for (Coin coin : blueCoinsToDistribute) {
                coin.setOwner(player.key.getPlayerNumber());
                player.getKey().takeCoin(coin);
            }

            // Remove the distributed coins from the available lists
            redCoins.subList(0, 2).clear();
            blueCoins.subList(0, 2).clear();
        }
    }

    /**
     * It will choose the coins from the field, just for the winner
     */

    @Override
    public List<Coin> getCoinsForChoose() {
        List<CustomPair<Player, Coin>> listCoins = round.getPlayedCoins();

        // Collect all red coins
        List<Coin> redCoins = listCoins.stream()
                .map(CustomPair::getValue)
                .filter(coin -> coin.getColor() == coinColors.RED)
                .collect(Collectors.toList());

        if (!redCoins.isEmpty()) {
            return redCoins;  // Return all red coins if any
        } else {
            // If no red coins are found, collect and return all blue coins
            return listCoins.stream()
                    .map(CustomPair::getValue)
                    .filter(coin -> coin.getColor() == coinColors.BLUE)
                    .collect(Collectors.toList());
        }
    }

    /**
     * It will return the player, that played the highest coin
     */
    @Override
    public Player checkForHighestPlayedCoins() throws Exception {
        return round.getPlayedCoins().stream()
                .max(Comparator.comparingInt(pair -> pair.getValue().getNumber()))
                .map(CustomPair::getKey)
                .orElseThrow(() -> new Exception("No coins played this round."));
    }

    // The coin will be played
    @Override
    public void playCoin(Player player, Coin coin) {
        try {
            if (player instanceof NPC) {
                // Handle NPC-specific logic
                NPC npc = (NPC) player; // Cast to NPC
                Coin playedCoin = npc.playCoin(); // Call NPC's playCoin logic
                round.addPlayedCoins(new CustomPair<>(npc, playedCoin)); // Record the coin played by the NPC
            } else {
                // Handle regular Player logic
                List<Coin> coinsOnHand = player.getCoinsOnHand();
                int coinIndex = IntStream.range(0, coinsOnHand.size())
                        .filter(i -> coinsOnHand.get(i).getNumber() == coin.getNumber() &&
                                coinsOnHand.get(i).getOwner() == coin.getOwner())
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Coin not found in hand!"));

                player.playCoin(coinIndex);
                round.addPlayedCoins(new CustomPair<>(player, coin)); // Record the coin played by the Player
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }


    /**
     * It will sort the coin on the table, for the winner will be placed on fieldOfWonCoins and for the losers will be taked on the hand.
     * When the player made the choise, will be set inactive.
     */
    @Override
    public void chooseCoinToSiteToHand(Player player, Coin coin) {
        try {
            // Check if the coin is red or if the player is the round winner
            if (player.equals(round.getRoundWinner())) {
                player.addWonCoin(coin); //TODO: catch
            } else {
                player.takeCoin(coin);//TODO: catch
            }

            // Use a stream to find the index of the player in the players list and set the flag to true
            IntStream.range(0, players.size())
                    .filter(i -> players.get(i).getKey().equals(player))
                    .findFirst()
                    .ifPresent(i -> players.set(i, new CustomPair<>(player, true)));

            // Proceed with the method assuming round is not null
            round.removePlayedCoins(coin);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * When the player have no coins on the hand, the game will be over.
     */
    @Override
    public boolean shouldEndGameBasedOnPlayerConditions() {
        List<Player> playersList = players.stream().map(CustomPair::getKey).toList();
        for (Player player : playersList) {
            // Check if any player has 4 on the site
            if (player.getFieldOfWonCoins().size() == 4) {
                return true;
            }
        }
        return false;  // Return false if no players meet the end game condition
    }

    /**
     * It will return the winner(max sum) and also all the player with the correspondent sum of coins
     */
    @Override
    public CustomPair<Player, Integer> checkWinningPlayer() {
        if (players.isEmpty()) {
            return null; // Return null or throw an exception if there are no players
        }

        Player playerWithSmallestSum = null;
        int minSum = Integer.MAX_VALUE; // Initialize with the largest possible integer to ensure any sum is smaller

        for (CustomPair<Player, Boolean> playerPair : players) {
            Player player = playerPair.getKey();
            int sumOfCoins = calculateSumOfCoinsOnSite(player);
            sumOfCoins = sumOfCoins + calculateSumOfCoinsOnHand(player);
            if (sumOfCoins < minSum) { // Check if the current player's sum is less than the min found so far
                minSum = sumOfCoins;
                playerWithSmallestSum = player;
            }
        }

        return new CustomPair<>(playerWithSmallestSum, minSum); // Return the player with the smallest sum of coins on site
    }

    /**
     * It will calculate the sum of the coins on the side.
     */
    private int calculateSumOfCoinsOnSite(Player player) {
        List<Coin> coinsOnSite = player.getFieldOfWonCoins();
        int sumOfCoins = 0;
        for (Coin coin : coinsOnSite) {
            sumOfCoins = sumOfCoins + coin.getNumber();
        }
        return sumOfCoins;
    }

    /**
     * It will calculate the sum of the coins on the hand.
     */
    private int calculateSumOfCoinsOnHand(Player player) {
        List<Coin> coinsOnHand = player.getCoinsOnHand();
        int sumOfCoins = 0;
        for (Coin coin : coinsOnHand) {
            sumOfCoins = sumOfCoins + coin.getNumber();
        }
        return sumOfCoins;
    }

    /**
     * It will return the round winner.
     */
    @Override
    public Player getWinnerOfRound() {
        if (round.getRoundWinner() == null) {
            try {
                Player highestPlayedCoin = checkForHighestPlayedCoins();
                round.setRoundWinner(highestPlayedCoin);
            } catch (Exception e) {
                //???
            }
        }
        return round.getRoundWinner();
    }

    /**
     * It will start the next round.
     */
    @Override
    public void startNextRound() {
        roundIndex++;
        round = new Round(amountPlayers, roundIndex);
        resetPlayerPlayedFlags();
    }

    /**
     * It will active the inactive player.
     */
    private void resetPlayerPlayedFlags() {
        for (int i = 0; i < players.size(); i++) {
            CustomPair<Player, Boolean> player = players.get(i);
            CustomPair<Player, Boolean> newPlayer = new CustomPair<>(player.getKey(), false);
            players.set(i, newPlayer);
        }
    }

    /**
     * It will return the next active player.
     */
    @Override
    public Player getNextActivePlayer() {
        Player roundWinner = round.getRoundWinner();
        Player nextPlayer = null;

        if (roundIndex > 0 && round != null && roundWinner != null) {
            List<CustomPair<Player, Boolean>> playersRound = new ArrayList<>(players);

            if (activePlayerIndex < amountPlayers) {
                activePlayerIndex = roundWinner.getPlayerNumber() + 1;
                nextPlayer = playersRound.stream().filter(p -> p.getKey().getPlayerNumber() == activePlayerIndex && p.getValue().equals(FALSE))
                        .map(CustomPair::getKey)
                        .findFirst()
                        .orElse(null);
            } else {
                activePlayerIndex = activePlayerIndex + 1;
                nextPlayer = playersRound.stream().filter(p -> p.getKey().getPlayerNumber() == activePlayerIndex && p.getValue().equals(FALSE))
                        .map(CustomPair::getKey)
                        .findFirst()
                        .orElse(null);
            }

        } else {

// Find the active player
            nextPlayer = players.stream()
                    .filter(pair -> pair.getKey().getPlayerNumber() == activePlayerIndex + 1)
                    .map(CustomPair::getKey)
                    .findFirst()
                    .orElse(null);

            activePlayerIndex++;
        }

        if (activePlayerIndex >= players.size()) {
            activePlayerIndex = 0; // Explicitly reset to 0
        }

        return nextPlayer;
    }
}
