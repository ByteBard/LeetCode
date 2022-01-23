package LeetCode;
/*
209. 长度最小的子数组
给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。



示例 1：

输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
示例 2：

输入：target = 4, nums = [1,4,4]
输出：1
示例 3：

输入：target = 11, nums = [1,1,1,1,1,1,1,1]
输出：0


提示：

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 105


进阶：

如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class MinimumSizeSubArraySum {

    public void run() {
        int target = 11;
        int[] nums = new int[]{1, 2, 3, 4, 5};

        System.out.println(minSubArrayLen_209(target, nums));
    }

    //17%, 11%
    public int minSubArrayLen_mliu(int target, int[] nums) {

        int left = 0;
        int right = 0;
        int sum = 0;
        int size = nums.length;
        int minLength = 0;

        while (right < size) {
            int currentNum = nums[right];
            if (currentNum >= target) return 1;
            sum += currentNum;
            if (sum >= target) {
                if (minLength == 0) {
                    minLength = right - left + 1;
                } else {
                    minLength = Math.min(minLength, right - left + 1);
                }
            }

            while (left <= right && sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                int currentNumAtLeft = nums[left];
                left++;
                sum -= currentNumAtLeft;
            }

            right++;
        }

        return minLength;
    }

    //50%, 28%
    public int minSubArrayLen_209(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
