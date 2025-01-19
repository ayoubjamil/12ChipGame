package com.frauas.javaproject.twelvechipgame.temp;



import com.frauas.javaproject.twelvechipgame.gamecomponets.Coin;
import com.frauas.javaproject.twelvechipgame.gamecomponets.Player;

import java.util.List;

public interface ILogic {
    void setAmountPlayers(int amountPlayers);

    void setHardMode(boolean isHardMode);

    void createPlayers();

    List<Player> getPlayers();

    void createCoins() throws Exception;

    void distributeCoin() throws Exception;

    List<Coin> getCoinsForChoose();

    Player checkForHighestPlayedCoins() throws Exception;

    void playCoin(Player player, Coin coin);

    void chooseCoinToSiteToHand(Player player, Coin coin);

    boolean shouldEndGameBasedOnPlayerConditions();

    CustomPair<Player, Integer> checkWinningPlayer();

    Player getWinnerOfRound();

    void startNextRound();

    Player getNextActivePlayer();
}
