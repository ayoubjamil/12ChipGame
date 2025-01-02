import java.util.ArrayList;
import java.util.List;

public class Coin {
    private final int number;       // Numerical value of the coin (fixed).
    private final coinColors color; // Color of the coin (red or blue).
    private int owner;              // Current owner of the coin (-1 if unowned).

    // Constructor
    public Coin(int number, coinColors color) {
        if (number < 1 || number > 16) {  // Ensure the number is within a valid range.
            throw new IllegalArgumentException("Coin number must be between 1 and 16.");
        }
        this.number = number;
        this.color = color;
        this.owner = -1; // Default: unowned at initialization.
    }

    // Getters
    public int getNumber() {
        return number; // Return the numerical value of the coin.
    }

    public coinColors getColor() {
        return color; // Return the color of the coin.
    }

    public int getOwner() {
        return owner; // Return the current owner of the coin.
    }

    // Setter for Ownership
    public void setOwner(int owner) {
        this.owner = owner; // Assign the coin to a player.
    }

    // Check if the coin is owned
    public boolean isOwned() {
        return owner != -1; // Return true if the coin is owned.
    }

    @Override
    public String toString() {
        return "Coin {number = " + number +
                ", color = " + color +
                ", owner = " + (owner == -1 ? "Unowned" : "Player " + owner) +
                "}";
    }

    // Factory Method to Initialize Coins
    public static List<Coin> initializeCoins(int numberOfPlayers) {
        List<Coin> coins = new ArrayList<>();

        // Standard Chips (1-12)
        coins.add(new Coin(1, coinColors.blue));
        coins.add(new Coin(2, coinColors.blue));
        coins.add(new Coin(3, coinColors.blue));
        coins.add(new Coin(4, coinColors.red));
        coins.add(new Coin(5, coinColors.red));
        coins.add(new Coin(6, coinColors.red));
        coins.add(new Coin(7, coinColors.red));
        coins.add(new Coin(8, coinColors.red));
        coins.add(new Coin(9, coinColors.red));
        coins.add(new Coin(10, coinColors.blue));
        coins.add(new Coin(11, coinColors.blue));
        coins.add(new Coin(12, coinColors.blue));

        // Extra Chips for 4 Players
        if (numberOfPlayers == 4) {
            coins.add(new Coin(3, coinColors.blue));  // Extra Blue Chip 3
            coins.add(new Coin(4, coinColors.red));  // Extra Red Chip 4
            coins.add(new Coin(9, coinColors.red));  // Extra Red Chip 9
            coins.add(new Coin(10, coinColors.blue)); // Extra Blue Chip 10
        }

        return coins;
    }
}
