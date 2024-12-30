public class BlueCoinFactory implements ACoinFactory {

    @Override
    public ACoin createCoin() {
        if(availableNumbers.size()>0) {
            int number = availableNumbers.indexOf(0);
            availableNumbers.remove(0);
            return new BlueCoin(ACoin.CoinColors.blue, number);
        }
        return null;
    }
}
