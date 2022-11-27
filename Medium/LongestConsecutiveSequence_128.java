package LeetCode.Medium;

import java.util.HashSet;

public class LongestConsecutiveSequence_128 {
    /*
    Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

    You must write an algorithm that runs in O(n) time.



    Example 1:

    Input: nums = [100,4,200,1,3,2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
    Example 2:

    Input: nums = [0,3,7,2,5,8,4,6,0,1]
    Output: 9

     */
    public void run() {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
//        int[] nums = new int[]{0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutive(nums));

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
