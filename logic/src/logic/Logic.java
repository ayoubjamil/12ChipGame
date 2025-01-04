package logic;

import data.Round;
import data.coin.ACoin;
import data.player.APlayer;
import data.player.NPC;
import data.player.Player;
import util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Logic implements ILogic {
    private List<Pair<APlayer, Boolean>> players = new ArrayList<>();
    private List<ACoin> coins = new ArrayList<>();
    private int amountPlayers;
    private int activePlayerIndex = 0;
    private Round round = null;
    private int roundIndex = 0;

    @Override
    public void setAmountPlayers(int amountPlayers) {
        this.amountPlayers = amountPlayers;
    }

    @Override
    public List<APlayer> getPlayers() {
        return players.stream()
                .map(Pair::getFirst)
                .collect(Collectors.toList());
    }

    @Override
    public APlayer getNextActivePlayer() {
        APlayer roundWinner = round.getRoundWinner();
        if (roundIndex > 0 && round != null && roundWinner != null) {
            List<Pair<APlayer, Boolean>> playersRound = new ArrayList<>(players);
            playersRound.removeIf(player -> player.first.equals(roundWinner) || player.second.equals(TRUE));
            if (!playersRound.isEmpty()) {
                return playersRound.get(0).getFirst();
            }
            return null;
        }
        APlayer activePlayer = players.get(activePlayerIndex).first;
        activePlayerIndex++;
        if (activePlayerIndex >= players.size()) {
            activePlayerIndex = 0; // Explicitly reset to 0
        }

        return activePlayer;
    }


    @Override
    public void createPlayers() {
        players.add(new Pair<APlayer, Boolean>(new Player("Name Player"), FALSE)); // Adding the main player
        for (int i = 1; i < amountPlayers; i++) {
            players.add(new Pair<APlayer, Boolean>(new NPC("Name NPC " + i), FALSE));
        }
    }

    @Override
    public void createCoins() {
        List<Integer> redNumbers = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8, 9));
        List<Integer> blueNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 10, 11, 12));

        // Check if the amount of players is more than 3, then add additional numbers
        if (amountPlayers > 3) {
            redNumbers.addAll(Arrays.asList(9, 4));  // Add extra red coins
            blueNumbers.addAll(Arrays.asList(10, 3));  // Add extra blue coins
        }

        // Create coin factories with the possibly modified lists
        ACoinFactory redCoinFactory = new RedCoinFactory(redNumbers);
        ACoinFactory blueCoinFactory = new BlueCoinFactory(blueNumbers);

        int coinsPerColor = amountPlayers * 2; // Each player should have 4 coins, 2 of each color

        for (int i = 0; i < coinsPerColor; i++) {
            coins.add(redCoinFactory.createCoin());
            coins.add(blueCoinFactory.createCoin());
        }
    }

    @Override
    public void distributeCoin() {
        // Collect a specific number of red and blue coins based on player requirements
        List<ACoin> redCoins = coins.stream()
                .filter(c -> c.getColor() == ACoin.CoinColors.red)
                .limit(amountPlayers * 2)
                .collect(Collectors.toList());
        List<ACoin> blueCoins = coins.stream()
                .filter(c -> c.getColor() == ACoin.CoinColors.blue)
                .limit(amountPlayers * 2)
                .collect(Collectors.toList());

        for (Pair<APlayer, Boolean> player : players) {
            if (redCoins.size() >= 2 && blueCoins.size() >= 2) {
                // Give each player 2 red and 2 blue coins
                List<ACoin> redCoinsToDistribute = new ArrayList<>(redCoins.subList(0, 2));
                List<ACoin> blueCoinsToDistribute = new ArrayList<>(blueCoins.subList(0, 2));

                // Add coins to player's hand
                player.getFirst().addCoinsOnHand(redCoinsToDistribute);
                player.getFirst().addCoinsOnHand(blueCoinsToDistribute);

                // Remove the distributed coins from the available lists
                redCoins.subList(0, 2).clear();
                blueCoins.subList(0, 2).clear();
            }
        }
    }


    @Override
    public List<ACoin> getCoinsForChoose() {
        List<Pair<APlayer, ACoin>> listCoins = round.getPlayedCoins();

        // Collect all red coins
        List<ACoin> redCoins = listCoins.stream()
                .map(Pair::getSecond)
                .filter(coin -> coin.getColor() == ACoin.CoinColors.red)
                .collect(Collectors.toList());

        if (!redCoins.isEmpty()) {
            return redCoins;  // Return all red coins if any
        } else {
            // If no red coins are found, collect and return all blue coins
            return listCoins.stream()
                    .map(Pair::getSecond)
                    .filter(coin -> coin.getColor() == ACoin.CoinColors.blue)
                    .collect(Collectors.toList());
        }
    }


    @Override
    public APlayer checkForHighestPlayedCoins() throws Exception {
        return round.getPlayedCoins().stream()
                .max(Comparator.comparingInt(pair -> pair.getSecond().getNumber()))
                .map(Pair::getFirst)
                .orElseThrow(() -> new Exception("No coins played this round."));
    }

    @Override
    public void playCoin(APlayer player, ACoin coin) {
        player.removeFromCoinsOnHand(coin);
        round.addPlayedCoins(new Pair<>(player, coin));  // Record the coin played by the player
    }

    @Override
    public void chooseCoinToSiteToHand(APlayer player, ACoin coin) {
        if (round == null) {
            throw new IllegalStateException("Round cannot be null when choosing a coin to site or hand.");
        }

        // Proceed with the method assuming round is not null
        round.removePlayedCoins(coin);

        // Check if the coin is red or if the player is the round winner
        if (coin.getColor() == ACoin.CoinColors.red || player.equals(round.getRoundWinner())) {
            player.addCoinOnSite(coin);
        } else {
            player.addCoinsOnHand(coin);
        }

        // Use a stream to find the index of the player in the players list and set the flag to true
        IntStream.range(0, players.size())
                .filter(i -> players.get(i).getFirst().equals(player))
                .findFirst()
                .ifPresent(i -> players.set(i, new Pair<>(player, true))); // Ensure to use lowercase 'true'
    }


    @Override
    public boolean shouldEndGameBasedOnPlayerConditions() {
        List<APlayer> playersList = players.stream().map(Pair::getFirst).toList();
        for (APlayer player : playersList) {
            // Check if any player has no coins left on hand or the sum of coins on site exceeds 21
            if (player.getCoinsOnHand().size() == 0 || player.calculateSumOfCoinsOnSite() > 21) {
                return true;
            }
        }
        return false;  // Return false if no players meet the end game condition
    }


    @Override
    public Pair<APlayer, Integer> checkWinningPlayer() {
        if (players.isEmpty()) {
            return null; // Return null or throw an exception if there are no players
        }

        APlayer playerWithSmallestSum = null;
        int minSum = Integer.MAX_VALUE; // Initialize with the largest possible integer to ensure any sum is smaller

        for (Pair<APlayer, Boolean> playerPair : players) {
            APlayer player = playerPair.getFirst();
            int sumOfCoins = player.calculateSumOfCoinsOnSite();
            if (sumOfCoins < minSum) { // Check if the current player's sum is less than the min found so far
                minSum = sumOfCoins;
                playerWithSmallestSum = player;
            }
        }

        return new Pair<>(playerWithSmallestSum, minSum); // Return the player with the smallest sum of coins on site
    }


    @Override
    public APlayer getWinnerOfRound() {
        if (round.getRoundWinner() == null) {
            try {
                APlayer highestPlayedCoin = checkForHighestPlayedCoins();
                round.setRoundWinner(highestPlayedCoin);
            } catch (Exception e) {
                //???
            }
        }
        return round.getRoundWinner();
    }

    @Override
    public void startNextRound() {
        roundIndex++;
        round = new Round(amountPlayers, roundIndex);
        resetPlayerPlayedFlags();
    }

    private void resetPlayerPlayedFlags() {
        for (int i = 0; i < players.size(); i++) {
            Pair<APlayer, Boolean> player = players.get(i);
            Pair<APlayer, Boolean> newPlayer = new Pair<>(player.getFirst(), false);
            players.set(i, newPlayer);
        }
    }
}
