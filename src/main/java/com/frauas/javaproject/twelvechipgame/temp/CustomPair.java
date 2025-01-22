package com.frauas.javaproject.twelvechipgame.temp;

public class CustomPair<F, S> {
    public final F first;
    public final S second;

    public CustomPair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }
    public S getSecond() {
        return second;
    }
}