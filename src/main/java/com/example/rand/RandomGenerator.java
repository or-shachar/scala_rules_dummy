package com.example.rand;

import java.util.Random;

public class RandomGenerator {

    private static Random randGen = null;
    public static Random getGenerator(){
        if (randGen==null) {
            randGen = new Random();
        }
        return randGen;
    }
}