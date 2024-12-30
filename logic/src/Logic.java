import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logic implements ILogic {
    private List<APlayer> players;
    private List<ACoin> coins;
    private List<ACoin> playedCoins;
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
        List<Integer> availableNumbersRed = Arrays.asList(4,5,6,7,8,9,9,4);
        List<Integer> availableNumbersBlue = Arrays.asList(1,2,3,10,11,12,10,3);
        ACoinFactory redCoinFactory = new RedCoinFactory();
        ACoinFactory blueCoinFactory = new BlueCoinFactory();

        redCoinFactory.setAvailableNumbers(availableNumbersRed);
        blueCoinFactory.setAvailableNumbers(availableNumbersBlue);

        amountCoins = amountPlayers*4;

        for (int i = 0; i < amountCoins/2; i++) {
            coins.add(redCoinFactory.createCoin());
            coins.add(blueCoinFactory.createCoin());
        }
    }


    @Override
    public void distributeCoin() {
        for(APlayer p: players) {
            List<ACoin> redCoins = coins.stream()
                    .filter(f -> f.getColor() == ACoin.CoinColors.red)
                    .limit(2)
                    .toList();
            p.addCoinsOnHand(redCoins);
            playedCoins.addAll(redCoins);
            List<ACoin> blueCoins = coins.stream()
                    .filter(f -> f.getColor() == ACoin.CoinColors.blue)
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
