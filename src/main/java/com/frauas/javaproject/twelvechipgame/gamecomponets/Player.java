package com.frauas.javaproject.twelvechipgame.gamecomponets;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int playerNumber;           // Unique identifier for the player (constant).
    private final List<Coin> coinsOnHand;     // Coins the player currently holds.
    private final List<Coin> fieldOfWonCoins; // Coins the player has won.

    private static final int MAX_COINS = 4;   // Maximum coins allowed in hand or field.

    // Constructor
    public Player(int playerNumber) {
        this.playerNumber = playerNumber;              // Assign a unique identifier to the player.
        this.coinsOnHand = new ArrayList<>();          // Initialize an empty list for coins in hand.
        this.fieldOfWonCoins = new ArrayList<>();      // Initialize an empty list for won coins.
    }

    // Get Player Number
    public int getPlayerNumber() {
        return playerNumber; // Return the unique identifier for this player.
    }

    // Play a coin by index (player chooses a specific position)
    public Coin playCoin(int index) {
        if (coinsOnHand.isEmpty()) {
            throw new IllegalStateException("No coins left to play!"); // Error if no coins are available.
        }
        if (index < 0 || index >= coinsOnHand.size()) {
            throw new IllegalArgumentException("Invalid index. Please choose a valid coin index."); // Error for invalid index.
        }
        return coinsOnHand.remove(index); // Remove and return the coin at the specified index.
    }

    // Play a coin by value (player chooses a specific coin by its value)
    public Coin playCoinByValue(int value) {
        for (int i = 0; i < coinsOnHand.size(); i++) {
            if (coinsOnHand.get(i).getNumber() == value) {
                return coinsOnHand.remove(i); // Remove and return the coin with the specified value.
            }
        }
        throw new IllegalArgumentException("No coin with the specified value found."); // Error if no matching coin is found.
    }

    // Add a Coin to the Player's Hand
    public void takeCoin(Coin coin) {
        if (coinsOnHand.size() >= MAX_COINS) {
            throw new IllegalStateException("Cannot hold more than " + MAX_COINS + " coins!"); // Error if the hand is full.
        }
        coinsOnHand.add(coin); // Add the coin to the player's hand.
    }

    // Check if the player has no coins left
    public boolean hasNoCoinsLeft() {
        return coinsOnHand.isEmpty(); // Return true if the player's hand is empty.
    }

    // Get Coins on Hand
    public List<Coin> getCoinsOnHand() {
        return new ArrayList<>(coinsOnHand); // Return a copy of the list to prevent external modification.
    }

    // Add a Coin to the Won Field
    public void addWonCoin(Coin coin) {
        if (fieldOfWonCoins.size() >= MAX_COINS) {
            throw new IllegalStateException("Cannot hold more than " + MAX_COINS + " coins in the won field!"); // Error if the won field is full.
        }
        fieldOfWonCoins.add(coin); // Add the coin to the player's won pile.
    }

    // Get Coins in the Field of Won Coins
    public List<Coin> getFieldOfWonCoins() {
        return new ArrayList<>(fieldOfWonCoins); // Return a copy of the list to prevent external modification.
    }

    // Choose a Coin (to be overridden by subclasses like NPC)
    public Coin chooseCoin(List<Coin> availableCoins) {
        throw new UnsupportedOperationException("This method must be implemented by subclasses."); // Default behavior for subclasses.
    }

    @Override
    public String toString() {
        return "Player " + playerNumber + " {" +
                "CoinsOnHand = " + coinsOnHand +
                ", FieldOfWonCoins = " + fieldOfWonCoins +
                '}';
    }
}