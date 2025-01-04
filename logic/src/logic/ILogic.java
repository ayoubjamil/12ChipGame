package logic;

import data.coin.ACoin;
import data.player.APlayer;
import util.Pair;

import java.util.List;

public interface ILogic {

    void setAmountPlayers(int amountPlayers);

    List<APlayer> getPlayers();

    APlayer getNextActivePlayer();

    void createPlayers();

    void createCoins();

    void distributeCoin();

    void playCoin(APlayer player, ACoin coin);

    APlayer checkForHighestPlayedCoins() throws Exception;

    void chooseCoinToSiteToHand(APlayer player, ACoin coin);

    List<ACoin> getCoinsForChoose();

    boolean shouldEndGameBasedOnPlayerConditions();

    Pair<APlayer, Integer> checkWinningPlayer();

    APlayer getWinnerOfRound();

    void startNextRound();
}
