import java.util.Comparator;
import java.util.List;

public class NPC extends Player {
    private final boolean isHardMode; // True for Hard NPC, False for Easy NPC

    // Constructor
    public NPC(int playerNumber, boolean isHardMode) {
        super(playerNumber); // Call Player's constructor
        this.isHardMode = isHardMode; // Set the mode (Hard or Easy)
    }

    // Play a Coin based on NPC logic
    public Coin playCoin() {
        if (getCoinsOnHand().isEmpty()) {
            throw new IllegalStateException("No coins left to play!"); // Error if no coins are available.
        }

        // Hard NPC Logic: Plays the biggest coin
        if (isHardMode) {
            return getCoinsOnHand().stream()
                    .max(Comparator.comparingInt(Coin::getNumber)) // Find the biggest coin
                    .map(coin -> {
                        getCoinsOnHand().remove(coin); // Remove it from hand
                        return coin;
                    })
                    .orElseThrow(() -> new IllegalStateException("No coin found!")); // Shouldn't happen
        }

        // Easy NPC Logic: Plays the smallest coin
        else {
            return getCoinsOnHand().stream()
                    .min(Comparator.comparingInt(Coin::getNumber)) // Find the smallest coin
                    .map(coin -> {
                        getCoinsOnHand().remove(coin); // Remove it from hand
                        return coin;
                    })
                    .orElseThrow(() -> new IllegalStateException("No coin found!")); // Shouldn't happen
        }
    }

    // Choose a Coin from available options (overrides Player's method)
    @Override
    public Coin chooseCoin(List<Coin> availableCoins) {
        if (availableCoins.isEmpty()) {
            throw new IllegalStateException("No coins available to choose from!"); // Error if empty list
        }

        // Hard NPC Logic: Chooses the biggest RED coin first, then BLUE
        if (isHardMode) {
            return availableCoins.stream()
                    .filter(coin -> coin.getColor() == coinColors.RED) // Look for RED coins
                    .max(Comparator.comparingInt(Coin::getNumber)) // Find the biggest RED coin
                    .or(() -> availableCoins.stream()
                            .filter(coin -> coin.getColor() == coinColors.BLUE) // Fallback to BLUE
                            .max(Comparator.comparingInt(Coin::getNumber)))
                    .orElseThrow(() -> new IllegalStateException("No coin found!")); // Shouldn't happen
        }

        // Easy NPC Logic: Chooses the smallest RED coin first, then BLUE
        else {
            return availableCoins.stream()
                    .filter(coin -> coin.getColor() == coinColors.RED) // Look for RED coins
                    .min(Comparator.comparingInt(Coin::getNumber)) // Find the smallest RED coin
                    .or(() -> availableCoins.stream()
                            .filter(coin -> coin.getColor() == coinColors.BLUE) // Fallback to BLUE
                            .min(Comparator.comparingInt(Coin::getNumber)))
                    .orElseThrow(() -> new IllegalStateException("No coin found!")); // Shouldn't happen
        }
    }
}
