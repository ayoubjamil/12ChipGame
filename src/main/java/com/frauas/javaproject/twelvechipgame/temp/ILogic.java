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
     * Provides a list of all active players currently participating in the game.
     * This method is typically used to iterate over players for gameplay mechanics such as turns and scoring.
     */
    List<Player> getPlayers();

    /**
     * Creates the set of coins that will be used in the game. This includes initializing
     * the coins with specific values and possibly attributes, and shuffling them if necessary
     * to ensure fair distribution.
     */
    void createCoins();

    /**
     * Distributes the initial set of coins among all players. This setup phase is crucial
     * for ensuring each player starts with the correct number and type of coins, according to the game rules.
     */
    void distributeCoin();

    /**
     * Selects coins that are available for the winning player to choose from after a round.
     * This selection might be based on specific game rules, such as only allowing the selection of certain types
     * of coins or from certain players.
     */
    List<Coin> getCoinsForChoose();

    /**
     * Determines which player played the highest valued coin in the current round.
     * This is a critical component of the game logic that may determine the winner of a round
     * or who gets first pick of coins from the table.

     * return The player who played the highest-valued coin, or null if no such coin was played.
     */
    Player checkForHighestPlayedCoins();

    /**
     * Facilitates a player's action of playing a coin. This includes logic to validate the play,
     * record it, and update game state accordingly. This method is central to the game's progression.
     */
    void playCoin(Player player, Coin coin);

    /**
     * Assigns a chosen coin to either the winning player's pile of won coins or returns it to their hand,
     * depending on the outcome of the round and the rules of the game. This method helps manage the state
     * of coins post-selection.
     */
    void chooseCoinToSiteToHand(Player player, Coin coin);

    /**
     * Evaluates whether the conditions to end the game have been met. This could be based on a variety of
     * conditions such as all coins being claimed, a player reaching a scoring threshold, or other end-game criteria.
     * <p>
     * return: true if the game should end based on current conditions, false otherwise.
     */
    boolean shouldEndGameBasedOnPlayerConditions();

    /**
     * Determines the winner of the game based on the current state of player scores, which are calculated
     * from the sum of coin values each player has won or still holds. This method is critical for
     * concluding the game and declaring the final winner.
     * <p>
     * return: A CustomPair containing the player with the smallest sum of coin values (indicating the winner in some games)
     * and their total coin value sum.
     */
    CustomPair<Player, Integer> checkWinningPlayer();

    List<CustomPair<Player, Integer>> calculateTotalCoinSumsForPlayers();

    /**
     * Retrieves the player who won the most recent round of the game. This could influence game dynamics,
     * such as who starts the next round or special bonuses awarded to the round winner.
     * <p>
     * return: The Player who won the most recent round, or null if there was no clear winner.
     */
    Player getWinnerOfRound();

    /**
     * Advances the game to the next round. This method is responsible for updating round-specific settings,
     * resetting or updating necessary player and game state attributes to reflect the new round's beginning.
     */
    void startNextRound();

    /**
     * Retrieves the next active player based on the game's rules for player rotation.
     * This could involve determining player order based on round wins, coin values, or other criteria.
     */
    Player getNextActivePlayer();
}