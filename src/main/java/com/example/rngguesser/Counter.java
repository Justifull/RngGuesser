package com.example.rngguesser;

public class Counter {
    private int count;

    public Counter() {
        this.count = 0;
    }

    public void addCount() {
        this.count = this.count + 1;
    }

    public int getCount() {
        return this.count;
    }
}