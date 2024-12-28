import java.util.ArrayList;
import java.util.List;

public class Logic implements ILogic {
    private List<APlayer> players;
    private List<Coin> coins;
    private List<Coin> playedCoins;
    private APlayer roundWinner;
    private boolean isGameFinished = false;
    private final int amountPlayers = 3;
    private  int amountCoins ;
    public Logic() {
        players = new ArrayList<APlayer>();
        coins = new ArrayList<>();
        playedCoins = new ArrayList<>();
    }

    void createPlayers(){
        APlayer player= new Player();
        players.add(player);
        for (int i = 0; i < amountPlayers -1; i++) {
            player= new NPC();
            players.add(player);
        }
    }
    void createCoins() {
        amountCoins = amountPlayers*4;
        for (int i = 0; i < amountCoins/2; i++) {
            coins.add(new Coin(Coin.CoinColors.red));
        }
        for (int i = 0; i < amountCoins/2; i++) {
            coins.add(new Coin(Coin.CoinColors.blue));
        }
    }


    @Override
    public void distributeCoin() {
        for(APlayer p: players) {
            List<Coin> redCoins = coins.stream()
                    .filter(f -> f.getColor() == Coin.CoinColors.red)
                    .limit(2)
                    .toList();
            p.addCoinsOnHand(redCoins);
            playedCoins.addAll(redCoins);
            List<Coin> blueCoins = coins.stream()
                    .filter(f -> f.getColor() == Coin.CoinColors.blue)
                    .limit(2)
                    .toList();
            p.addCoinsOnHand(blueCoins);
            playedCoins.addAll(blueCoins);
        }
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
