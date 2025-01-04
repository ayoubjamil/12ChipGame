package data.coin;

public abstract class ACoin {
    private final int number;

    public ACoin( int number) {
        this.number = number;
    }

    public  abstract CoinColors getColor() ;


    public int getNumber() {
        return number;
    }
    public enum CoinColors{
        blue,
        red
    }
}
