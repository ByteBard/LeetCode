package LeetCode;

import static java.lang.StrictMath.abs;

public class First_Missing_Positive_41 {

    public void run() {

//        int[] x = new int[]{1};
        int[] x = new int[]{1, 2, 0};
//        int[] x = new int[]{3, 4, -1, 1};
//         int[] x = new int[]{7,8,9,11,12};
        int res = firstMissingPositive(x);
        System.out.println(res);
    }

    public int firstMissingPositiveFormal(int[] nums) {
        int n = nums.length;

        // Base case.
        int contains = 0;
        for (int i = 0; i < n; i++)
            if (nums[i] == 1) {
                contains++;
                break;
            }

        if (contains == 0)
            return 1;

        // Replace negative numbers, zeros,
        // and numbers larger than n by 1s.
        // After this convertion nums will contain
        // only positive numbers.
        for (int i = 0; i < n; i++)
            if ((nums[i] <= 0) || (nums[i] > n))
                nums[i] = 1;

        // Use index as a hash key and number sign as a presence detector.
        // For example, if nums[1] is negative that means that number `1`
        // is present in the array.
        // If nums[2] is positive - number 2 is missing.
        for (int i = 0; i < n; i++) {
            int a = Math.abs(nums[i]);
            // If you meet number a in the array - change the sign of a-th element.
            // Be careful with duplicates : do it only once.
            if (a == n)
                nums[0] = - Math.abs(nums[0]);
            else
                nums[a] = - Math.abs(nums[a]);
        }

        // Now the index of the first positive number
        // is equal to first missing positive.
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0)
                return i;
        }

        if (nums[0] > 0)
            return n;

        return n + 1;
    }

    public int firstMissingPositive(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] < 0) nums[i] = 0;
        }
        for (int i = 0; i < size; i++) {
            int curr = abs(nums[i]);
            if (1 <= curr && curr <= size) {
                if (nums[curr - 1] > 0) {
                    nums[curr - 1] *= -1;
                }
                if (nums[curr - 1] == 0) {
                    nums[curr - 1] = -1 * (size + 1);
                }
            }
        }

        for (int i = 1; i <= size; i++) {
            int val = nums[i - 1];
            if (val >= 0) return i;
        }

        return size + 1;

    }
}
