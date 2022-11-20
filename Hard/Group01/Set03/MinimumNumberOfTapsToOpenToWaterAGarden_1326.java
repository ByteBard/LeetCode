package LeetCode.Hard.Group01.Set03;

public class MinimumNumberOfTapsToOpenToWaterAGarden_1326 {
/*
There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).

There are n + 1 taps located at points [0, 1, ..., n] in the garden.

Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.

Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.
Input: n = 5, ranges = [3,4,1,1,0,0]
Output: 1
Explanation: The tap at point 0 can cover the interval [-3,3]
The tap at point 1 can cover the interval [-3,5]
The tap at point 2 can cover the interval [1,3]
The tap at point 3 can cover the interval [2,4]
The tap at point 4 can cover the interval [4,4]
The tap at point 5 can cover the interval [5,5]
Opening Only the second tap will water the whole garden [0,5]
Example 2:

Input: n = 3, ranges = [0,0,0,0]
Output: -1
Explanation: Even if you activate all the four taps you cannot water the whole garden.

https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/description/
https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden/solution/guan-gai-hua-yuan-de-zui-shao-shui-long-tou-shu-3/

For this problem, we just need construct a new array to move the value into the leftmost point we can water,
so the problem becomes Jump Game II. For example, at index i we could water (i - arr[i]) ~ (i + arr[i]).
So we store the farthest point we can water at "i - arr[i]".
Then "left" in range [left, right] is index and "right" is the value in arr[index].
 */
    public void run(){
        int n = 5;
        int[] ranges = new int[]{3,4,1,1,0,0};
        System.out.println(minTaps(n, ranges));
    }

    public int minTaps(int n, int[] ranges) {
        // construct the arr
        int[] arr = new int[n + 1];
        for(int i = 0; i < ranges.length; i++) {
            if(ranges[i] == 0) continue;
            int left = Math.max(0, i - ranges[i]);
            arr[left] = Math.max(arr[left], i + ranges[i]);
        }

        // same part like previous problem
        int end = 0, farCanReach = 0, cnt = 0, i = 0;
        while (i < arr.length && end < n){
            cnt++;
            while (i < arr.length && i <= end) {
                farCanReach = Math.max(farCanReach, arr[i++]);
            }
            if (end == farCanReach) return -1;
            end = farCanReach;
        }

        return cnt;
    }

    public int minTaps_ForLoop(int n, int[] ranges) {
        // construct the arr
        int[] arr = new int[n + 1];
        for(int i = 0; i < ranges.length; i++) {
            if(ranges[i] == 0) continue;
            int left = Math.max(0, i - ranges[i]);
            arr[left] = Math.max(arr[left], i + ranges[i]);
        }

        // same part like previous problem
        int end = 0, farCanReach = 0, cnt = 0;
        for(int i = 0; i < arr.length && end < n; end = farCanReach) {
            cnt++;
            while(i < arr.length && i <= end) {
                farCanReach = Math.max(farCanReach, arr[i++]);
            }
            if(end == farCanReach) return -1;
        }
        return cnt;
    }

    /*

    faster than the above versions but not understandable...

    https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden/solution/guan-gai-hua-yuan-de-zui-shao-shui-long-tou-shu-3/
    class Solution {
public:
    int minTaps(int n, vector<int>& ranges) {
        vector<int> prev(n + 1);
        iota(prev.begin(), prev.end(), 0);

        for (int i = 0; i <= n; ++i) {
            int l = max(i - ranges[i], 0);
            int r = min(i + ranges[i], n);
            prev[r] = min(prev[r], l);
        }

        int breakpoint = n, furthest = INT_MAX;
        int ans = 0;
        for (int i = n; i > 0; --i) {
            furthest = min(furthest, prev[i]);
            if (i == breakpoint) {
                if (furthest >= i) {
                    return -1;
                }
                breakpoint = furthest;
                ++ans;
            }
        }

        return ans;
    }
};

作者：LeetCode-Solution
链接：https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden/solution/guan-gai-hua-yuan-de-zui-shao-shui-long-tou-shu-3/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
