package org.task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoodPairsTest {
    @Test
    public void numIdenticalPairs() {
        assertEquals(4, GoodPairs.numIdenticalPairs(new int[]{1,2,3,1,1,3}));
        assertEquals(6, GoodPairs.numIdenticalPairs(new int[]{1,1,1,1}));
        assertEquals(0, GoodPairs.numIdenticalPairs(new int[]{1,2,3}));
    }
}
