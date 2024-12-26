public interface ILogic {
    void distributeCoin();
    void playCoins();
    APlayer checkForHighestPlayedCoins();
    void chooseCoin();
    void checkEndGameCondition();
    void checkWinningPlayer();
    void calculateSumOfCoins();
    void initializeCoins();
    void getWinnerOfRound();

}
