package org.task;

public class Main {
    public static void main(String[] args) {
        // Task 1
        System.out.println(JewelsAndStones.numJewelsInStones("aA", "aAAbbbb"));
        JewelsAndStones.numJewelsInStones("z", "ZZ");
        JewelsAndStones.numJewelsInStones("abc", "aabbccabc");
        JewelsAndStones.numJewelsInStones("", "aAAbbbb");
        JewelsAndStones.numJewelsInStones("aA", "");

        // Task 2
        System.out.println(GoodPairs.numIdenticalPairs(new int[]{1,2,3,1,1,3}));
        GoodPairs.numIdenticalPairs(new int[]{1,1,1,1});
        GoodPairs.numIdenticalPairs(new int[]{1,2,3});

        // Task 3
        System.out.println(BalloonInstances.maxNumberOfBalloons("nlaebolko"));
        BalloonInstances.maxNumberOfBalloons("loonbalxballpoon");
        BalloonInstances.maxNumberOfBalloons("leetcode");

    }
}
