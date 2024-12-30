public interface ILogic {
    void distributeCoin();
     void playCoin(APlayer player, ACoin coin) ;
    APlayer checkForHighestPlayedCoins() throws Exception;
    void chooseCoin();
    void checkEndGameCondition();
    void checkWinningPlayer();
    void calculateSumOfCoins();
    void initializeCoins();
    void getWinnerOfRound();

}
