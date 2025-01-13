

import java.util.ArrayList;
import java.util.List;

public class Round {
    public int roundIndex;
    public int amountPlayers;

    private List<Pair<Player, Coin>> playedCoins;
    private Player roundWinner;

    public Round(int amountPlayers, int roundIndex) {
        this.amountPlayers = amountPlayers;
        this.roundIndex = roundIndex;
        playedCoins = new ArrayList<>();
    }

    //put coin on table
    public void addPlayedCoins(Pair<Player, Coin> coinPair) {
        playedCoins.add(coinPair);

    }

    public List<Pair<Player, Coin>> getPlayedCoins() {
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
        playedCoins.removeIf(pair -> pair.getSecond().equals(coinToRemove));
    }
}

