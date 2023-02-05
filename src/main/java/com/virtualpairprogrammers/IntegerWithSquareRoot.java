package com.virtualpairprogrammers;

import java.io.Serializable;

public class IntegerWithSquareRoot implements Serializable {

    private final int originalNumber;
    private final double squareRoot;

    public IntegerWithSquareRoot(int i) {
        this.originalNumber = i;
        this.squareRoot = Math.sqrt(originalNumber);
    }

    @Override
    public String toString() {
        return "Original Number:" + originalNumber + ", SQRT:" + squareRoot;
    }
}
