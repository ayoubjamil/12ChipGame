import java.util.ArrayList;
import java.util.List;

public class Logic implements ILogic {
    private List<APlayer> players;
    private List<Coin> coins;
    private List<Coin> playedCoins;
    private APlayer roundWinner;
    private boolean isGameFinished = false;

    public Logic() {
        players = new ArrayList<APlayer>();
        coins = new ArrayList<>();
        playedCoins = new ArrayList<>();
    }


    @Override
    public void distributeCoin() {
        
    }

    @Override
    public void playCoins() {

    }

    @Override
    public APlayer checkForHighestPlayedCoins() {
        return null;
    }

    @Override
    public void chooseCoin() {

    }

    @Override
    public void checkEndGameCondition() {

    }

    @Override
    public void checkWinningPlayer() {

    }

    @Override
    public void calculateSumOfCoins() {

    }

    @Override
    public void initializeCoins() {

    }

    @Override
    public void getWinnerOfRound() {

    }
}
