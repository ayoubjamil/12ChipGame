

import java.util.List;

public interface ILogic {
    void setAmountPlayers(int amountPlayers);

    void createPlayers();

    List<Player> getPlayers();

    void createCoins() throws Exception;

    void distributeCoin() throws Exception;

    List<Coin> getCoinsForChoose();

    Player checkForHighestPlayedCoins() throws Exception;

    void playCoin(Player player, Coin coin);

    void chooseCoinToSiteToHand(Player player, Coin coin);

    boolean shouldEndGameBasedOnPlayerConditions();

    Pair<Player, Integer> checkWinningPlayer();

    Player getWinnerOfRound();

    void startNextRound();

}
