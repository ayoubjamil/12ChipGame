package com.frauas.javaproject.twelvechipgame.temp;

public class CustomPair<F, S> {
    public final F key;
    public final S value;

    public CustomPair(F key, S second) {
        this.key = key;
        this.value = second;
    }

    public F getKey() {
        return key;
    }
    public S getValue() {
        return value;
    }
}