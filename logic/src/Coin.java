import java.util.ArrayList;
import java.util.List;

public class Coin {
    private final int number;       // Numerical value of the coin (fixed).
    private final coinColors color; // Color of the coin (RED or BLUE).
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

    public int getValue() {
        return number; // Alias for getNumber(), used by NPC and Player classes.
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
        coins.add(new Coin(1, coinColors.BLUE));
        coins.add(new Coin(2, coinColors.BLUE));
        coins.add(new Coin(3, coinColors.BLUE));
        coins.add(new Coin(4, coinColors.RED));
        coins.add(new Coin(5, coinColors.RED));
        coins.add(new Coin(6, coinColors.RED));
        coins.add(new Coin(7, coinColors.RED));
        coins.add(new Coin(8, coinColors.RED));
        coins.add(new Coin(9, coinColors.RED));
        coins.add(new Coin(10, coinColors.BLUE));
        coins.add(new Coin(11, coinColors.BLUE));
        coins.add(new Coin(12, coinColors.BLUE));

        // Extra Chips for 4 Players
        if (numberOfPlayers == 4) {
            coins.add(new Coin(3, coinColors.BLUE));  // Extra BLUE Chip 3
            coins.add(new Coin(4, coinColors.RED));  // Extra RED Chip 4
            coins.add(new Coin(9, coinColors.RED));  // Extra RED Chip 9
            coins.add(new Coin(10, coinColors.BLUE)); // Extra BLUE Chip 10
        }

        return coins;
    }
}
