package LeetCode;

public class MinimumSizeSubArraySum {

    public void run() {
        int target = 11;
        int[] nums = new int[]{1, 2, 3, 4, 5};

        System.out.println(minSubArrayLen_209(target, nums));
    }

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
