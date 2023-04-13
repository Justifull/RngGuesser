package com.example.rngguesser;

public class Rng {
    private final int definedNum;

    public Rng(int n) {
        this.definedNum = (int) Math.round(Math.random() * n);
    }

    public int num() {
        return this.definedNum;
    }
}