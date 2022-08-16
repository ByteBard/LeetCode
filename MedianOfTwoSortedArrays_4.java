package LeetCode;

public class MedianOfTwoSortedArrays_4 {

    //4. 寻找两个正序数组的中位数
//    https://leetcode.cn/problems/median-of-two-sorted-arrays/
//    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
//    算法的时间复杂度应该为 O(log (m+n)) 。
//
//
//
//    示例 1：
//
//    输入：nums1 = [1,3], nums2 = [2]
//    输出：2.00000
//    解释：合并数组 = [1,2,3] ，中位数 2
//    示例 2：
//
//    输入：nums1 = [1,2], nums2 = [3,4]
//    输出：2.50000
//    解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
//
//
//
//
//    提示：
//
//    nums1.length == m
//    nums2.length == n
//0 <= m <= 1000
//            0 <= n <= 1000
//            1 <= m + n <= 2000
//            -106 <= nums1[i], nums2[i] <= 106
    public void run() {
        int[] nums1 = new int[]{1, 3}, nums2 = new int[]{2};
//        int[] nums1 = new int[]{1, 2}, nums2 = new int[]{3, 4};
        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println(result);

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }

        int shortLen = nums1.length;
        int longLen = nums2.length;

        int left = 0;
        int right = shortLen;
        int halfOfTotalCount = (shortLen + longLen + 1) / 2;

        while (left < right) {
            int i = left + (right - left + 1) / 2;
            int j = halfOfTotalCount - i;
            if (nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
        }

        int shortIdx = left;
        int longIdx = halfOfTotalCount - shortIdx;
        int shortLeftMax = shortIdx == 0 ? Integer.MIN_VALUE : nums1[shortIdx - 1];
        int shortRightMin = shortIdx == shortLen ? Integer.MAX_VALUE : nums1[shortIdx];
        int longLeftMax = longIdx == 0 ? Integer.MIN_VALUE : nums2[longIdx - 1];
        int longRightMin = longIdx == longLen ? Integer.MAX_VALUE : nums2[longIdx];

        if ((shortLen + longLen) % 2 == 1) {
            return Math.max(shortLeftMax, longLeftMax);
        }
        return (double) (Math.max(shortLeftMax, longLeftMax) + Math.min(shortRightMin, longRightMin)) / 2;
    }
}
