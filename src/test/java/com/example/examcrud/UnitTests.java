package com.example.examcrud;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class UnitTests {

    @Test
    void testRandom() {
        Random r = new Random();
        int[] count  = new int[3];

        for (int i = 0; i < 2000; i++) {
            int tmp = r.nextInt(-1, 2);
            count[tmp + 1]++;
        }

        System.out.printf("Result: -1: %d, 0: %d, 1: %d%n",
                count[0],
                count[1],
                count[2]);

    }
}
