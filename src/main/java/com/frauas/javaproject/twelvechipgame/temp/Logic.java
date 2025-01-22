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


    private List<CustomPair<Player, Boolean>> players = new ArrayList<>();
    private int amountPlayers;
    private List<Coin> coins;
    private Round round = null;
    private int roundIndex = 0;
    private int activePlayerIndex = 0;

    private Logic() {
    }



    /**
     * Set amount of players.
     * @param amountPlayers
     */
    @Override
    public void setAmountPlayers(int amountPlayers) {
        this.amountPlayers = amountPlayers;
    }

    /**
     * create list of players
     */
    @Override
    public void createPlayers() {
        players.add(new CustomPair<Player, Boolean>(new Player(1), FALSE)); // Adding the main player
        for (int i = 1; i < amountPlayers; i++) {
            players.add(new CustomPair<Player, Boolean>(new NPC(i + 1, true), FALSE));
        }
    }

    /**
     * return list of players
     * @return
     */
    @Override
    public List<Player> getPlayers() {
        return players.stream()
                .map(CustomPair::getFirst)
                .collect(Collectors.toList());

    }

    /**
     * Create a list of coins
     * @throws Exception
     */
    @Override
    public void createCoins(int numberOfPlayers) throws Exception {

        coins = Coin.initializeCoins(numberOfPlayers);

    }

    /**
     * give to every players the own coins.
     * @throws Exception
     */
    @Override
    public void distributeCoin() throws Exception {
        if (coins == null) throw new Exception("Null list coins");

        // Collect a specific number of red and blue coins based on player requirements
        List<Coin> redCoins = coins.stream()
                .filter(c -> c.getColor() == coinColors.RED)
                .limit(amountPlayers * 2)
                .collect(Collectors.toList());
        List<Coin> blueCoins = coins.stream()
                .filter(c -> c.getColor() == coinColors.BLUE)
                .limit(amountPlayers * 2)
                .collect(Collectors.toList());

        for (CustomPair<Player, Boolean> player : players) {
            if (redCoins.size() >= 2 && blueCoins.size() >= 2) {
                // Give each player 2 red and 2 blue coins
                List<Coin> redCoinsToDistribute = new ArrayList<>(redCoins.subList(0, 2));
                List<Coin> blueCoinsToDistribute = new ArrayList<>(blueCoins.subList(0, 2));

                // Add coins to player's hand
                for (Coin coin : redCoinsToDistribute) {
                    coin.setOwner(player.first.getPlayerNumber());
                    player.getFirst().takeCoin(coin);
                }

                for (Coin coin : blueCoinsToDistribute) {
                    coin.setOwner(player.first.getPlayerNumber());
                    player.getFirst().takeCoin(coin);
                }

                // Remove the distributed coins from the available lists
                redCoins.subList(0, 2).clear();
                blueCoins.subList(0, 2).clear();
            }
        }
    }

    /** It will choose the coins from the field, just for the winner
     *
     */

    @Override
    public List<Coin> getCoinsForChoose() {
        List<CustomPair<Player, Coin>> listCoins = round.getPlayedCoins(); //TODO: check this

        // Collect all red coins
        List<Coin> redCoins = listCoins.stream()
                .map(CustomPair::getSecond)
                .filter(coin -> coin.getColor() == coinColors.RED)
                .collect(Collectors.toList());

        if (!redCoins.isEmpty()) {
            return redCoins;  // Return all red coins if any
        } else {
            // If no red coins are found, collect and return all blue coins
            return listCoins.stream()
                    .map(CustomPair::getSecond)
                    .filter(coin -> coin.getColor() == coinColors.BLUE)
                    .collect(Collectors.toList());
        }
    }

    /** It will return the player, that played the highest coin
     *
     * @return
     * @throws Exception
     */
    @Override
    public Player checkForHighestPlayedCoins() throws Exception {
        return round.getPlayedCoins().stream()
                .max(Comparator.comparingInt(pair -> pair.getSecond().getNumber()))
                .map(CustomPair::getFirst)
                .orElseThrow(() -> new Exception("No coins played this round."));
    }

    // The coin will be played
    @Override
    public void playCoin(Player player, Coin coin) {
        try {
            List<Coin> coinsOnHand = player.getCoinsOnHand();
            int coinIndex = IntStream.range(0, coinsOnHand.size()).filter(i -> coinsOnHand.get(i).getNumber() == coin.getNumber() &&
                    coinsOnHand.get(i).getOwner() == coin.getOwner()).findFirst().getAsInt();
            player.playCoin(coinIndex);
            round.addPlayedCoins(new CustomPair<>(player, coin));  // Record the coin played by the player
        } catch (Exception ex) {
            //TODO: ???
        }

    }

    /**
     * It will sort the coin on the table, for the winner will be placed on fieldOfWonCoins and for the losers will be taked on the hand.
     * When the player made the choise, will be set inactive.
     *
     */
    @Override
    public void chooseCoinToSiteToHand(Player player, Coin coin) {
        if (round == null) {
            throw new IllegalStateException("Round cannot be null when choosing a coin to site or hand.");
        }

        // Proceed with the method assuming round is not null
        round.removePlayedCoins(coin);

        // Check if the coin is red or if the player is the round winner
        if (player.equals(round.getRoundWinner())) {
            player.addWonCoin(coin); //TODO: catch
        } else {
            player.takeCoin(coin);//TODO: catch
        }

        // Use a stream to find the index of the player in the players list and set the flag to true
        IntStream.range(0, players.size())
                .filter(i -> players.get(i).getFirst().equals(player))
                .findFirst()
                .ifPresent(i -> players.set(i, new CustomPair<>(player, true)));
    }

    /**
     * When the player have no coins on the hand, the game will be over.
     */
    @Override
    public boolean shouldEndGameBasedOnPlayerConditions() {
        List<Player> playersList = players.stream().map(CustomPair::getFirst).toList();
        for (Player player : playersList) {
            // Check if any player has no coins left on hand
            if (player.getCoinsOnHand().size() == 0) {
                return true;
            }
        }
        return false;  // Return false if no players meet the end game condition
    }

    /**
     * It will return the winner(max sum) and also all the player with the correspondent sum of coins
     * @return
     */
    @Override
    public CustomPair<Player, Integer> checkWinningPlayer() {
        if (players.isEmpty()) {
            return null; // Return null or throw an exception if there are no players
        }

        Player playerWithSmallestSum = null;
        int minSum = Integer.MAX_VALUE; // Initialize with the largest possible integer to ensure any sum is smaller

        for (CustomPair<Player, Boolean> playerPair : players) {
            Player player = playerPair.getFirst();
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
     * @param player
     * @return
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
     * @return
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
            CustomPair<Player, Boolean> newPlayer = new CustomPair<>(player.getFirst(), false);
            players.set(i, newPlayer);
        }
    }

    /**
     * It will return the next active player.
     * @return
     */
    @Override
    public Player getNextActivePlayer() {
        Player roundWinner = round.getRoundWinner();
        if (roundIndex > 0 && round != null && roundWinner != null) {
            List<CustomPair<Player, Boolean>> playersRound = new ArrayList<>(players);
            playersRound.removeIf(player -> player.first.equals(roundWinner) || player.second.equals(TRUE));
            if (!playersRound.isEmpty()) {
                return playersRound.get(0).getFirst();
            }
            return null;
        }
        Player activePlayer = players.get(activePlayerIndex).first;
        activePlayerIndex++;
        if (activePlayerIndex >= players.size()) {
            activePlayerIndex = 0; // Explicitly reset to 0
        }

        return activePlayer;
    }
}