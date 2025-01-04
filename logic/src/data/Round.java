package data;

import data.coin.ACoin;
import data.player.APlayer;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Round {
    public int roundIndex;
    public int amountPlayers;

    private List<Pair<APlayer, ACoin>> playedCoins;
    private APlayer roundWinner;

    public Round(int amountPlayers, int roundIndex) {
        this.amountPlayers = amountPlayers;
        this.roundIndex = roundIndex;
        playedCoins = new ArrayList<>();
    }

    //put coin on table
    public void addPlayedCoins(Pair<APlayer, ACoin> coinPair) {
        playedCoins.add(coinPair);

    }

    public List<Pair<APlayer, ACoin>> getPlayedCoins() {
        return playedCoins;
    }

    public APlayer getRoundWinner() {
        return roundWinner;
    }

    public void setRoundWinner(APlayer roundWinner) {
        this.roundWinner = roundWinner;
    }

    //remove coin from table
    public void removePlayedCoins(ACoin coinToRemove) {
        playedCoins.removeIf(pair -> pair.getSecond().equals(coinToRemove));
    }
}
