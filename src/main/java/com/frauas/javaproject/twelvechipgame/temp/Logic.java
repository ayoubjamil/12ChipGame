package com.frauas.javaproject.twelvechipgame.temp;

import com.frauas.javaproject.twelvechipgame.gamecomponets.Coin;
import com.frauas.javaproject.twelvechipgame.gamecomponets.NPC;
import com.frauas.javaproject.twelvechipgame.gamecomponets.Player;
import com.frauas.javaproject.twelvechipgame.gamecomponets.coinColors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public void resetLogic() {
        players.clear();
        amountPlayers = 0;
        round = null;
        roundIndex = 0;
        activePlayerIndex = 0;
        isHardMode = false;

    }
    public static void resetInstance() {



        instance = null;
    }

    /*public Round getRound() {
        return this.round;
    }*/

    public Round getRound() {
        return round;
    }

    @Override
    public int getActualRoundNumber() {
        return roundIndex;
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
        players = players.reversed();

        Random random = new Random();
        activePlayerIndex = random.nextInt(amountPlayers);

    }

    /**
     * return list of all players in the game
     */
    @Override
    public List<Player> getPlayers() {
        return players.stream().map(CustomPair::getKey).collect(Collectors.toList());
    }

    /**
     * Create a list of coins
     *
     */
    @Override
    public List<Coin> createCoins() {
        coins = Coin.initializeCoins(amountPlayers);
        Collections.shuffle(coins);
        return coins;
    }

    /**
     * give to every players the own coins.
     */
    @Override
    public void distributeCoin() {
        // Collect a specific number of red and blue coins based on player requirements
        List<Coin> redCoins = coins.stream().filter(c -> c.getColor() == coinColors.RED).collect(Collectors.toList());

        List<Coin> blueCoins = coins.stream().filter(c -> c.getColor() == coinColors.BLUE).collect(Collectors.toList());

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
            System.out.println("distributeCoin: " + player.getKey().toString());
        }
    }

    /**
     * It will choose the coins from the field, just for the winner
     */

    @Override
    public List<Coin> getCoinsForChoose() {
        List<CustomPair<Player, Coin>> listCoins = round.getPlayedCoins();

        // Collect all red coins
        List<Coin> redCoins = listCoins.stream().map(CustomPair::getValue).filter(coin -> coin.getColor() == coinColors.RED).collect(Collectors.toList());

        if (!redCoins.isEmpty()) {
            return redCoins;  // Return all red coins if any
        } else {
            // If no red coins are found, collect and return all blue coins
            return listCoins.stream().map(CustomPair::getValue).filter(coin -> coin.getColor() == coinColors.BLUE).collect(Collectors.toList());
        }
    }

    /**
     * It will return the player, that played the highest coin
     */
    @Override
    public Player checkForHighestPlayedCoins() {
        return round.getPlayedCoins().stream().max(Comparator.comparingInt(pair -> pair.getValue().getNumber())).map(CustomPair::getKey).orElse(null);
    }

    // The coin will be played
    @Override
    public boolean  playCoin(Player player, Coin coin) {
        try {
            if (player instanceof NPC) {
                handleNPCPlayer((NPC) player);
                return true;
            } else {
                handleRegularPlayer(player, coin);
                return false;
            }
        } catch (IllegalStateException | IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }

    private void handleNPCPlayer(NPC npc) {
        Coin playedCoin = npc.playCoin();

        validateAndRecordCoin(npc, playedCoin);
        npc.playCoinByValue(playedCoin.getNumber());
        System.out.println(">>>  " + "NPC player " + npc.getPlayerNumber() + " plays " + playedCoin);
    }

    private void handleRegularPlayer(Player player, Coin coin) throws IllegalArgumentException {
        int coinIndex = findCoinIndex(player.getCoinsOnHand(), coin);
        validateAndRecordCoin(player, coin);
        player.playCoin(coinIndex);
        System.out.println(">>>  " + "Player " + player.getPlayerNumber() + " plays " + coin);

    }

    public int findCoinIndex(List<Coin> coinsOnHand, Coin coin) {
        return IntStream.range(0, coinsOnHand.size()).filter(i -> coinsOnHand.get(i).getId() == coin.getId()).findFirst().orElseThrow(() -> new IllegalArgumentException("Coin not found in hand!"));
    }

    public void validateAndRecordCoin(Player player, Coin coin) {
        if (isCoinIDInUse(player, coin)) {
            throw new IllegalStateException("Coin is already in use.");
        }
        if (isCoinAlreadyWon(coin)) {
            throw new IllegalStateException("Coin has already been won.");
        }
        round.addPlayedCoins(new CustomPair<>(player, coin));
    }


    /**
     * It will sort the coin on the table, for the winner will be placed on fieldOfWonCoins and for the losers will be taked on the hand.
     * When the player made the choise, will be set inactive.
     *
     * @return
     */
    @Override
    public Coin chooseCoinToSiteToHand(Player player, Coin coin) {
        try {
            Coin chosenCoin = coin;
            if (player instanceof NPC) {
                NPC npc = (NPC) player;
                chosenCoin = npc.chooseCoin(getCoinsForChoose());
            }

            processChosenCoin(player, chosenCoin);
            markPlayerActive(player);
            return chosenCoin;
        } catch (IllegalStateException | IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());  // Consider replacing with proper logging
        }
        return coin;
    }

    private void processChosenCoin(Player player, Coin coin) {
        validateCoinOwnership(player, coin);

        coin.setOwner(player.getPlayerNumber());
        if (player.equals(round.getRoundWinner())) {
            player.addWonCoin(coin);
            System.out.println(">>>  " + player.toString() + " moves coin " + coin.toString() + " to site (to won)");
        } else {
            player.takeCoin(coin);
            System.out.println(">>>  " + player.toString() + " takes coin " + coin.toString() + " to hand (take coin)");
        }

        round.removePlayedCoins(coin);
    }

    private void validateCoinOwnership(Player player, Coin coin) {
        if (isCoinIDInUse(player, coin)) {
            throw new IllegalStateException("Coin is already in use.");
        }
        if (isCoinAlreadyWon(coin)) {
            throw new IllegalStateException("Coin has already been won.");
        }
    }

    private void markPlayerActive(Player player) {
        IntStream.range(0, players.size()).filter(i -> players.get(i).getKey().equals(player)).findFirst().ifPresent(i -> players.set(i, new CustomPair<>(player, TRUE)));
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

    @Override
    public CustomPair<Player, Integer> checkWinningPlayer() {
        if (players.isEmpty()) {
            return null; // Return null if there are no players to process
        }

        List<CustomPair<Player, Integer>> playersWithSumBelow21 = findPlayersWithSumBelowOrEqual21();
        if (!playersWithSumBelow21.isEmpty()) {
            return findPlayerClosestTo21();
        } else {
            return findPlayerWithHighestSum();
        }
    }

    private CustomPair<Player, Integer> findPlayerWithHighestSum() {
        Player playerWithMaxSum = null;
        int maxSum = Integer.MIN_VALUE; // Initialize with the smallest possible integer to ensure any sum is larger

        for (CustomPair<Player, Boolean> playerPair : players) {
            Player player = playerPair.getKey();
            int totalSum = getTotalCoinSumForPlayer(player); // Calculate total sum of coins
            if (totalSum > maxSum) { // Check if the current player's sum is greater than the max found so far
                maxSum = totalSum;
                playerWithMaxSum = player;
            }
        }

        return new CustomPair<>(playerWithMaxSum, maxSum);
    }

    /**
     * Calculates the sum of the coins on the player's field of won coins.
     */
    private int calculateSumOfCoinsOnField(Player player) {
        List<Coin> coinsOnField = player.getFieldOfWonCoins();
        int totalSum = 0;
        for (Coin coin : coinsOnField) {
            totalSum += coin.getNumber();
        }
        return totalSum;
    }

    @Override
    public List<CustomPair<Player, Integer>> calculateTotalCoinSumsForPlayers() {
        List<CustomPair<Player, Integer>> totalSumForPlayers = new ArrayList<>();

        for (CustomPair<Player, Boolean> playerPair : players) {
            Player player = playerPair.getKey();
            int totalSum = getTotalCoinSumForPlayer(player);
            totalSumForPlayers.add(new CustomPair<>(player, totalSum));
        }
        return totalSumForPlayers;
    }

    private int getTotalCoinSumForPlayer(Player player) {
        return calculateSumOfCoinsOnField(player) + calculateSumOfCoinsInHand(player);
    }

    /**
     * Calculates the sum of the coins in the player's hand.
     */
    private int calculateSumOfCoinsInHand(Player player) {
        List<Coin> coinsInHand = player.getCoinsOnHand();
        int totalSum = 0;
        for (Coin coin : coinsInHand) {
            totalSum += coin.getNumber();
        }
        return totalSum;
    }

    private List<CustomPair<Player, Integer>> findPlayersWithSumBelowOrEqual21() {
        List<CustomPair<Player, Integer>> result = new ArrayList<>();

        for (CustomPair<Player, Boolean> playerPair : players) {
            Player player = playerPair.getKey();
            int totalSum = getTotalCoinSumForPlayer(player);

            if (totalSum <= 21) {
                result.add(new CustomPair<>(player, totalSum));
            }
        }

        return result;
    }

    private CustomPair<Player, Integer> findPlayerClosestTo21() {
        CustomPair<Player, Integer> pairValue = players.stream()
                .map(playerPair -> new CustomPair<>(playerPair.getKey(), getTotalCoinSumForPlayer(playerPair.getKey())))
                .filter(pair -> pair.getValue() <= 21) // Only consider sums that are 21 or less
                .min(Comparator.comparingInt(pair -> Math.abs(pair.getValue() - 21)))
                .orElse(null); // Return null if no valid players are found
        return pairValue;
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
            CustomPair<Player, Boolean> newPlayer = new CustomPair<>(player.getKey(), FALSE);
            players.set(i, newPlayer);
        }
    }

    /**
     * It will return the next active player.
     */
    @Override
    public Player getNextActivePlayer(Player player) {
        // Ermittle den Gewinner der Runde
        Player roundWinner = (round != null) ? player : null;
        System.out.println("============== roundWinner " + roundWinner);

        // Starte bei Spieler 0, wenn es keinen Gewinner gibt, oder bei dem Index des Gewinners
        // Ermittle den Startindex: 0, wenn kein Gewinner vorhanden, oder den Index des Gewinners
        int startIndex = (roundWinner == null) ? activePlayerIndex : IntStream.range(0, players.size()).filter(i -> players.get(i).getKey().getPlayerNumber() == roundWinner.getPlayerNumber()).findFirst().orElse(-1); // Fallback, falls der Gewinner nicht gefunden wird

        if (startIndex == -1) {
            System.out.println("==============" + "NULL");
            return null; // Gewinner nicht gefunden
        }

        // Suche nach dem nächsten inaktiven Spieler
        int currentIndex = startIndex;
        do {
            CustomPair<Player, Boolean> currentPlayer = players.get(currentIndex);

            // Wenn der Spieler inaktiv ist, markiere ihn als aktiv und gib ihn zurück
            if (!currentPlayer.getValue()) {
                activePlayerIndex = (currentIndex + 1) % players.size(); // Nächster Spieler als Startpunkt speichern
                System.out.println("==============" + "return getNextActivePlayer " + currentPlayer.getKey());
                return currentPlayer.getKey();
                //return players.get(activePlayerIndex).getKey();
            }

            // Zum nächsten Spieler wechseln (zyklisch)
            currentIndex = (currentIndex + 1) % players.size();
        } while (currentIndex != startIndex);

        // Wenn kein inaktiver Spieler gefunden wurde, gib null zurück
        return null;
    }

    private boolean isCoinIDInUse(Player currentPlayer, Coin coin) {
        return players.stream().filter(p -> !p.getKey().equals(currentPlayer)) // Exclude the current player
                .flatMap(p -> Stream.concat(p.getKey().getCoinsOnHand().stream(), p.getKey().getFieldOfWonCoins().stream())).anyMatch(c -> c.getId() == coin.getId());
    }

    private boolean isCoinAlreadyWon(Coin coin) {
        return players.stream().flatMap(p -> p.getKey().getFieldOfWonCoins().stream()).anyMatch(c -> c.getId() == coin.getId());
    }

}