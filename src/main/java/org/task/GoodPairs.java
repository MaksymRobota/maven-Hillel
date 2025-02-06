package org.task;

import java.util.HashMap;
import java.util.Map;

public class GoodPairs {
    public static int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int count = 0;
        for (int num : nums) {
            count += countMap.getOrDefault(num, 0);
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        return count;
    }
}
