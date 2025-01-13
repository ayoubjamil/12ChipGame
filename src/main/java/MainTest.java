

import java.util.List;

public class MainTest {
   // private static APlayer winnerOfRound;

    public static void main(String[] args) {
      /*  ILogic logic = setupGame();

        while (true) {
            // Play a round
            playRound(logic);

            // Determine and process the winner
            winnerOfRound = logic.getWinnerOfRound();
            processPlayer(logic, winnerOfRound);
            processPlayers(logic);
            // Check end game condition after player processing
            if (logic.shouldEndGameBasedOnPlayerConditions()) {
                System.out.println("Game over. Ending conditions met.");
                Pair<APlayer, Integer> win = logic.checkWinningPlayer();
                System.out.println("Player: " + win.first.getName() + " with sum of coins: " + win.second);
                break;  // Exit the loop if the game should end
            }

            // Start the next round
            logic.startNextRound();
        }
    }


    private static ILogic setupGame() {
        ILogic logic = new Logic();
        logic.setAmountPlayers(3);
        logic.createPlayers();
        logic.createCoins();
        logic.distributeCoin();
        logic.startNextRound();
        return logic;
    }

    private static void playRound(ILogic logic) {
        for (int i = 0; i < logic.getPlayers().size(); i++) {
            APlayer activePlayer;
            if (winnerOfRound == null) {
                activePlayer = logic.getNextActivePlayer();
            } else {
                activePlayer = winnerOfRound;
                winnerOfRound = null;
            }
            List<ACoin> coins = activePlayer.getCoinsOnHand();
            if (!coins.isEmpty()) {
                logic.playCoin(activePlayer, activePlayer.getCoinsOnHand().get(0));
            }

        }
    }

    private static void processPlayer(ILogic logic, APlayer player) {
        List<ACoin> coinsForChoose = logic.getCoinsForChoose();
        if (!coinsForChoose.isEmpty()) {
            logic.chooseCoinToSiteToHand(player, coinsForChoose.get(0));
        }
    }

    private static void processPlayers(ILogic logic) {
        for (int i = 0; i < logic.getPlayers().size(); i++) {
            APlayer activePlayer = logic.getNextActivePlayer();
            if (activePlayer == null) {
                return;
            }
            List<ACoin> coinsForChoose = logic.getCoinsForChoose();
            if (!coinsForChoose.isEmpty()) {
                logic.chooseCoinToSiteToHand(activePlayer, coinsForChoose.get(0));
            }
        }*/
    }
}
