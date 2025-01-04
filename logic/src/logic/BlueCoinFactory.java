package logic;

import data.coin.ACoin;
import data.coin.BlueCoin;
import data.coin.RedCoin;

import java.util.List;

public class BlueCoinFactory extends ACoinFactory {
    public BlueCoinFactory(List<Integer> list) {
        super(list);
    }

    @Override
    public ACoin createCoin() {
        if (!availableNumbers.isEmpty()) {
            int number = availableNumbers.get(0);
            availableNumbers.remove(0);
            return new BlueCoin( number);
        }
        return null;
    }
}
