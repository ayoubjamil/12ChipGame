package com.frauas.javaproject.twelvechipgame.temp;
import com.frauas.javaproject.twelvechipgame.gamecomponets.Coin;
import com.frauas.javaproject.twelvechipgame.gamecomponets.Player;

import java.util.List;

public interface ILogic {
    /**
     * Retrieves the number of the current round within the gameplay sequence.
     * This method is useful for tracking the progression of the game across its various stages.
     */
    int getActualRoundNumber();

    /**
     * Configures the game with a specific number of players. This setting is crucial as it
     * determines the scale of the game and influences initialization of player-specific resources.
     */
    void setAmountPlayers(int amountPlayers);

    /**
     * Sets the difficulty level of the game. Enabling hard mode may alter gameplay mechanics,
     * increasing the challenge for players.
     */
    void setHardMode(boolean isHardMode);

    /**
     * Initializes players for the game. This method is responsible for creating and setting up
     * the list of players, including both human and NPC (Non-Player Characters), based on the previously
     * set number of players.
     */
    void createPlayers();

    /**
     * Provides a list of all players currently participating in the game.
     *
     */
    List<Player> getPlayers();

    /**
     * Creates the set of coins that will be used in the game. This includes initializing
     * the coins with specific values and possibly attributes, and shuffling them if necessary
     * to ensure fair distribution.
     */
    List<Coin> createCoins();

    /**
     * Distributes the initial set of coins among all players. This setup phase is crucial
     * for ensuring each player starts with the correct number and type of coins, according to the game rules.
     */
    void distributeCoin();

    /**
     * Selects coins that are available for the player to choose from after a round.
     */
    List<Coin> getCoinsForChoose();

    /**
     * Determines which player played the highest valued coin in the current round.
     * return The player who played the highest-valued coin.
     */
    Player checkForHighestPlayedCoins();

    /**
     * Facilitates a player's action of playing a coin.
     */
    boolean playCoin(Player player, Coin coin);

    /**
     * Assigns a chosen coin to either the winning player's pile of won coins or returns it to their hand.
     *
     * @return
     */
    Coin chooseCoinToSiteToHand(Player player, Coin coin);

    /**
     * Evaluates whether the conditions to end the game have been met.
     * return: true if the game should end based on current conditions, false otherwise.
     */
    boolean shouldEndGameBasedOnPlayerConditions();

    /**
     * Get the winner of the game, which are calculated
     * from the sum of coin values each player has won or still holds.
     * return: A CustomPair containing the player with the sum of coin values
     */
    CustomPair<Player, Integer> checkWinningPlayer();

    List<CustomPair<Player, Integer>> calculateTotalCoinSumsForPlayers();

    /**
     * Retrieves the player who won the recent round of the game.
     * return: The Player who won the recent round.
     */
    Player getWinnerOfRound();

    /**
     * Start the game to the next round. This method is responsible for updating round-specific settings,
     * resetting or updating necessary player and game state attributes to reflect the new round's beginning.
     */
    void startNextRound();

    /**
     * Retrieves the next active player based on the game's rules for player rotation.
     * This could involve determining player order based on round wins, coin values, or other criteria.
     */
    Player getNextActivePlayer(Player player);


}