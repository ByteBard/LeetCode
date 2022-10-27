package LeetCode.Hard.Group01.Set01;

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




/*

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

    }
}

 */
    public void run() {
        int[] nums1 = new int[]{1, 3}, nums2 = new int[]{2};
//        int[] nums1 = new int[]{1, 2}, nums2 = new int[]{3, 4};
        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println(result);

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //由于我们需要通过访问“中间数分割线”左右两边的情况，因此应该在较短的数组上确定“中间数分割线”的位置，两种极端情况，分割线分别取在较短数组的头和尾
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
        //https://leetcode.cn/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
        //合并两种写法，上下取整：假设数组1长度为m，数组2长度为n，m+n为偶数的时候，left size = m+n/2 = m+n+1/2，奇数的时候任然是 m+n+1/2，所以可以合并为m+n+1/2
        while (left < right) {
            //分割线在第1个数组右边的第1个元素的下标i=分割线在第1个数组左边元素的个数
            //分割线在第2个数组右边的第1个元素的下标j=分割线在第2个数组左边元素的个数
            //在num1的区间[0,m]里查找恰当的分割线
            //使得num1[i-1]<=num2[j] && num2[j-1]<=num1[i]
            //[i-1]和[j-1]就分别表示在分割线的左边，-1是分割线左边，没变化就是分割线右边
            //记住i和j是表示个数的，换算成idx要-1
            int i = left + (right - left + 1) / 2;
            int j = halfOfTotalCount - i;
            if (nums1[i - 1] > nums2[j]) {
                //下一轮搜索区间[left,i-1]
                right = i - 1;
            } else {
                //下一轮搜索区间[i,right]
                //当只有两个元素的时候，一旦进入这个分支这个边界就不会再缩小[left[i],right]这个循环就会进入一个死循环，
                //所以取中位数的时候必须中间加上一个1(line 66)，如果中位数加1，那么在循环体内部就不会执行到[0,m]中0这个下标，所以nums[i-1]这个下标是不会越界的。
                left = i;
            }
        }
        //在退出循环的时候，我们就找到了满足了条件的分割线，重新定义shortIdx，longIdx(也可以用i,j代替)
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
