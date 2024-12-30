import java.util.List;

public class RedCoinFactory extends ACoinFactory {



    @Override
    public ACoin createCoin() {
        if(availableNumbers.size()>0) {
            int number = availableNumbers.indexOf(0);
            availableNumbers.remove(0);
            return new RedCoin(ACoin.CoinColors.red, number);
        }
        return null;
    }


}
