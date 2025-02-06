package org.task;

import java.util.HashMap;
import java.util.Map;

public class BalloonInstances {
    public static int maxNumberOfBalloons(String text) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        return Math.min(countMap.getOrDefault('b', 0),
                Math.min(countMap.getOrDefault('a', 0),
                        Math.min(countMap.getOrDefault('l', 0) / 2,
                                Math.min(countMap.getOrDefault('o', 0) / 2,
                                        countMap.getOrDefault('n', 0)))));
    }
}
