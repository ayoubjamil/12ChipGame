package data.player;

import data.coin.ACoin;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class APlayer {
    private final String name;
    private List<ACoin> coinsOnHand;
    private List<ACoin> coinsOnSite;

    public APlayer(String name) {
        coinsOnSite = new ArrayList<>();
        coinsOnHand = new ArrayList<>();
        this.name = name;
    }

    public void addCoinsOnHand(List<ACoin> coins) {
        coinsOnHand.addAll(coins);
        Collections.shuffle(coinsOnHand);

    }

    public void addCoinsOnHand(ACoin coin) {
        coinsOnHand.add(coin);
    }

    public void removeFromCoinsOnHand(ACoin coin) {
        coinsOnHand.remove(coin);
    }

    public List<ACoin> getCoinsOnHand() {
        return coinsOnHand;
    }

    public void addCoinOnSite(ACoin coin) {
        coinsOnSite.add(coin);
    }

    public List<ACoin> getCoinsOnSite() {
        return coinsOnSite;
    }

    public int calculateSumOfCoinsOnSite() {
        return coinsOnSite.stream()
                .mapToInt(ACoin::getNumber)
                .sum();  // Sum all the numbers
    }

    public String getName() {
        return name;
    }
}
