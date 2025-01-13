


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Boolean.FALSE;

public class Logic implements ILogic {

    private List<Pair<Player, Boolean>> players = new ArrayList<>();
    private int amountPlayers;
    private List<Coin> coins;
    private Round round = null;
    private int roundIndex = 0;


    @Override
    public void setAmountPlayers(int amountPlayers) {
        this.amountPlayers = amountPlayers;
    }

    @Override
    public void createPlayers() {
        players.add(new Pair<Player, Boolean>(new Player(1), FALSE)); // Adding the main player
        for (int i = 1; i < amountPlayers; i++) {
            players.add(new Pair<Player, Boolean>(new NPC(i + 1, true), FALSE));
        }
    }

    @Override
    public List<Player> getPlayers() {
        return players.stream()
                .map(Pair::getFirst)
                .collect(Collectors.toList());
    }

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

        for (Pair<Player, Boolean> player : players) {
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

    @Override
    public List<Coin> getCoinsForChoose() {
        List<Pair<Player, Coin>> listCoins = round.getPlayedCoins(); //TODO: check this

        // Collect all red coins
        List<Coin> redCoins = listCoins.stream()
                .map(Pair::getSecond)
                .filter(coin -> coin.getColor() == coinColors.RED)
                .collect(Collectors.toList());

        if (!redCoins.isEmpty()) {
            return redCoins;  // Return all red coins if any
        } else {
            // If no red coins are found, collect and return all blue coins
            return listCoins.stream()
                    .map(Pair::getSecond)
                    .filter(coin -> coin.getColor() == coinColors.BLUE)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Player checkForHighestPlayedCoins() throws Exception {
        return round.getPlayedCoins().stream()
                .max(Comparator.comparingInt(pair -> pair.getSecond().getNumber()))
                .map(Pair::getFirst)
                .orElseThrow(() -> new Exception("No coins played this round."));
    }

    @Override
    public void playCoin(Player player, Coin coin) {
        try {
            List<Coin> coinsOnHand = player.getCoinsOnHand();
            int coinIndex = IntStream.range(0, coinsOnHand.size()).filter(i -> coinsOnHand.get(i).getNumber() == coin.getNumber() &&
                    coinsOnHand.get(i).getOwner() == coin.getOwner()).findFirst().getAsInt();
            player.playCoin(coinIndex);
            round.addPlayedCoins(new Pair<>(player, coin));  // Record the coin played by the player
        } catch (Exception ex) {
            //TODO: ???
        }
    }

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
                .ifPresent(i -> players.set(i, new Pair<>(player, true)));
    }

    @Override
    public boolean shouldEndGameBasedOnPlayerConditions() {
        List<Player> playersList = players.stream().map(Pair::getFirst).toList();
        for (Player player : playersList) {
            // Check if any player has no coins left on hand
            if (player.getCoinsOnHand().size() == 0) {
                return true;
            }
        }
        return false;  // Return false if no players meet the end game condition
    }

    @Override
    public Pair<Player, Integer> checkWinningPlayer() {
        if (players.isEmpty()) {
            return null; // Return null or throw an exception if there are no players
        }

        Player playerWithSmallestSum = null;
        int minSum = Integer.MAX_VALUE; // Initialize with the largest possible integer to ensure any sum is smaller

        for (Pair<Player, Boolean> playerPair : players) {
            Player player = playerPair.getFirst();
            int sumOfCoins = calculateSumOfCoinsOnSite(player);
            sumOfCoins = sumOfCoins + calculateSumOfCoinsOnHand(player);
            if (sumOfCoins < minSum) { // Check if the current player's sum is less than the min found so far
                minSum = sumOfCoins;
                playerWithSmallestSum = player;
            }
        }

        return new Pair<>(playerWithSmallestSum, minSum); // Return the player with the smallest sum of coins on site
    }

    private int calculateSumOfCoinsOnSite(Player player) {
        List<Coin> coinsOnSite = player.getFieldOfWonCoins();
        int sumOfCoins = 0;
        for (Coin coin : coinsOnSite) {
            sumOfCoins = sumOfCoins + coin.getNumber();
        }
        return sumOfCoins;
    }

    private int calculateSumOfCoinsOnHand(Player player) {
        List<Coin> coinsOnHand = player.getCoinsOnHand();
        int sumOfCoins = 0;
        for (Coin coin : coinsOnHand) {
            sumOfCoins = sumOfCoins + coin.getNumber();
        }
        return sumOfCoins;
    }

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

    @Override
    public void startNextRound() {
        roundIndex++;
        round = new Round(amountPlayers, roundIndex);
        resetPlayerPlayedFlags();
    }

    private void resetPlayerPlayedFlags() {
        for (int i = 0; i < players.size(); i++) {
            Pair<Player, Boolean> player = players.get(i);
            Pair<Player, Boolean> newPlayer = new Pair<>(player.getFirst(), false);
            players.set(i, newPlayer);
        }
    }
}
