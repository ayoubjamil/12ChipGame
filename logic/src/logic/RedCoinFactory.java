package logic;

import data.coin.ACoin;
import data.coin.RedCoin;

import java.util.List;

public class RedCoinFactory extends ACoinFactory {


    public RedCoinFactory(List<Integer> list) {
        super(list);
    }

    @Override
    public ACoin createCoin() {
        if (availableNumbers != null && !availableNumbers.isEmpty()) {
            int number = availableNumbers.get(0);
            availableNumbers.remove(0);
            return new RedCoin(number);
        }
        return null;
    }


}
