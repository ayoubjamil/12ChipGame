package com.frauas.javaproject.twelvechipgame.temp;

import com.frauas.javaproject.twelvechipgame.gamecomponets.Coin;
import com.frauas.javaproject.twelvechipgame.gamecomponets.Player;

import java.util.ArrayList;
import java.util.List;

public class Round {
    public int roundIndex;
    public int amountPlayers;

    private List<CustomPair<Player, Coin>> playedCoins;
    private Player roundWinner;

    public Round(int amountPlayers, int roundIndex) {
        this.amountPlayers = amountPlayers;
        this.roundIndex = roundIndex;
        playedCoins = new ArrayList<>();
    }

    //put coin on table
    public void addPlayedCoins(CustomPair<Player, Coin> coinPair) {
        playedCoins.add(coinPair);
    }

    public List<CustomPair<Player, Coin>> getPlayedCoins() {
        return playedCoins;
    }

    public Player getRoundWinner() {
        return roundWinner;
    }

    public void setRoundWinner(Player roundWinner) {
        this.roundWinner = roundWinner;
    }

    //remove coin from table
    public void removePlayedCoins(Coin coinToRemove) {
        playedCoins.removeIf(pair -> pair.getValue().equals(coinToRemove));
    }
}