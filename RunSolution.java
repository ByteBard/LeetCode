package LeetCode;

import LeetCode.Hard.Group01.Set03.MinimumNumberOfTapsToOpenToWaterAGarden_1326;

import java.util.HashSet;

public class RunSolution {
    public static void main(String[] args) {
        MinimumNumberOfTapsToOpenToWaterAGarden_1326 x = new MinimumNumberOfTapsToOpenToWaterAGarden_1326();
        x.run();
    }


    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currNum = num;
                int consecutive = 1;
                while (set.contains(currNum + 1)) {
                    currNum++;
                    consecutive++;
                }
                longest = Math.max(longest, consecutive);
            }
        }
        return longest;
    }

}
