import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int playerNumber;           // Unique identifier for the player (constant).
    private List<Coin> coinsOnHand;           // Coins the player currently holds.
    private List<Coin> fieldOfWonCoins;       // Coins the player has won.

    // Constructor
    public Player(int playerNumber) {
        this.playerNumber = playerNumber;     // Assign the unique player number.
        this.coinsOnHand = new ArrayList<>(); // Initialize an empty hand.
        this.fieldOfWonCoins = new ArrayList<>(); // Initialize an empty won pile.
    }

    // Get Player Number
    public int getPlayerNumber() {
        return playerNumber; // Return the player's unique number.
    }

    // Play a Coin
    public Coin playCoin() {
        if (coinsOnHand.isEmpty()) {
            throw new IllegalStateException("No coins left to play!"); // Error if no coins available.
        }
        return coinsOnHand.remove(0); // Remove and return the first coin in the list.
    }

    // Add a Coin to the Player's Hand
    public void takeCoin(Coin coin) {
        coinsOnHand.add(coin); // Add the coin to the player's hand.
    }

    // Check if the player has no coins left
    public boolean hasNoCoinsLeft() {
        return coinsOnHand.isEmpty(); // Return true if no coins are left in hand.
    }

    // Choose a Coin (red first, then blue if no red is available)
    public Coin chooseCoin(List<Coin> availableCoins) {
        Coin selectedCoin = null;
        // Prioritize red coins
        for (Coin coin : availableCoins) {
            if (coin.getColor() == coinColors.red) {
                selectedCoin = coin;
                break;
            }
        }
        // If no red coin, choose a blue coin
        if (selectedCoin == null) {
            for (Coin coin : availableCoins) {
                if (coin.getColor() == coinColors.blue) {
                    selectedCoin = coin;
                    break;
                }
            }
        }
        return selectedCoin;
    }

    // Add a Coin to the Won Field
    public void addWonCoin(Coin coin) {
        fieldOfWonCoins.add(coin); // Add the coin to the won pile.
    }

    // Get Coins on Hand
    public List<Coin> getCoinsOnHand() {
        return coinsOnHand; // Return the player's current hand of coins.
    }

    // Set Coins on Hand
    public void setCoinsOnHand(List<Coin> coins) {
        this.coinsOnHand = coins; // Replace the current hand with a new list.
    }

    // Get Coins in the Field of Won Coins
    public List<Coin> getFieldOfWonCoins() {
        return fieldOfWonCoins; // Return the player's won pile.
    }

    // Set Field of Won Coins
    public void setFieldOfWonCoins(List<Coin> coins) {
        this.fieldOfWonCoins = coins; // Replace the current won pile with a new list.
    }

    @Override
    public String toString() {
        return "Player " + playerNumber + " {" +
                "CoinsOnHand = " + coinsOnHand +
                ", FieldOfWonCoins = " + fieldOfWonCoins +
                '}';
    }
}
