package data.coin;

public class BlueCoin extends ACoin {

    public BlueCoin( int number) {
        super( number);
    }

    @Override
    public CoinColors getColor() {
        return CoinColors.blue;
    }
}
