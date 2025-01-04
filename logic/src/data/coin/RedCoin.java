package data.coin;

public class RedCoin extends ACoin {
    public RedCoin(int number) {
        super(number);
    }

    @Override
    public CoinColors getColor() {
        return CoinColors.red;
    }
}
