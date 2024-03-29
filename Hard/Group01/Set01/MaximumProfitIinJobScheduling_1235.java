package LeetCode.Hard.Group01.Set01;

import java.util.Arrays;
import java.util.TreeMap;

public class MaximumProfitIinJobScheduling_1235 {
    //    1235. 规划兼职工作
//    你打算利用空闲时间来做兼职工作赚些零花钱。
//
//    这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
//
//    给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。
//
//    注意，时间上出现重叠的 2 份工作不能同时进行。
//
//    如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
//
//
//
//    示例 1：
//
//
//
//    输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
//    输出：120
//    解释：
//    我们选出第 1 份和第 4 份工作，
//    时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
//    示例 2：
//
//
//
//    输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
//    输出：150
//    解释：
//    我们选择第 1，4，5 份工作。
//    共获得报酬 150 = 20 + 70 + 60。
//    示例 3：
//
//
//
//    输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
//    输出：6
//
//
//    提示：
//
//            1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
//            1 <= startTime[i] < endTime[i] <= 10^9
//            1 <= profit[i] <= 10^4

    /*


class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {

    }
}
}
     */


    public void run() {
//        startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
//        startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]

        int[] startTime = new int[]{1, 2, 3, 3}, endTime = new int[]{3, 4, 5, 6}, profit = new int[]{50, 10, 40, 70};
//        int[] startTime = new int[]{1,2,3,4,6}, endTime = new int[]{3,5,10,6,9}, profit = new int[]{20,20,100,70,60};
//        int[] startTime = new int[]{1,1,1}, endTime = new int[]{2,3,4}, profit = new int[]{5,6,4};

        System.out.println(jobScheduling(startTime, endTime, profit));
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        //the job is sorted by end time, not start time, same with the key of the TreeMap
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int[] job : jobs) {
            int val = job[2] + dp.floorEntry(job[0]).getValue();
            if (val > dp.lastEntry().getValue()) {
                //this is the finish time, we should always update the finish time with the max sum, if the sum > last one on the map
                dp.put(job[1], val);
            }
        }
        return dp.lastEntry().getValue();
    }
}
