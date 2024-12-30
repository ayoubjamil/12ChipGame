import java.util.List;

public abstract class ACoinFactory {
    protected List<Integer> availableNumbers;

    abstract ACoin createCoin();

    public void setAvailableNumbers(List<Integer> availableNumbers) {
        this.availableNumbers=availableNumbers;
    }
}
