package org.task;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class JewelsAndStonesTest {
    @Test
    public void testNumJewelsInStones() {
        assertEquals(3, JewelsAndStones.numJewelsInStones("aA", "aAAbbbb"));
        assertEquals(0, JewelsAndStones.numJewelsInStones("z", "ZZ"));
        assertEquals(9, JewelsAndStones.numJewelsInStones("abc", "aabbccabc"));
        assertEquals(0, JewelsAndStones.numJewelsInStones("", "aAAbbbb"));
        assertEquals(0, JewelsAndStones.numJewelsInStones("aA", ""));
    }
}
