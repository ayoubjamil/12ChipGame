package logic;

import data.coin.ACoin;

import java.util.List;

public abstract class ACoinFactory {

    protected List<Integer> availableNumbers;

    public ACoinFactory(List<Integer> list) {
        this.availableNumbers = list;
    }

    abstract ACoin createCoin();
}
