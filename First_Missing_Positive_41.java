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
