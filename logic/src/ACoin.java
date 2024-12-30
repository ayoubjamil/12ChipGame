public abstract class ACoin {
    private final int number;

    // This is a placeholder
    public ACoin(CoinColors coinColors, int number) {
        this.number = number;
    }

    // This is a placeholder
    public CoinColors getColor() {
        return CoinColors.blue;
    }

    public int getNumber() {
        return number;
    }
    public enum CoinColors{
        blue,
        red
    }
}
