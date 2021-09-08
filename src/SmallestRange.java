import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SmallestRange {

    public void run() {
        List<Integer> l1 = Arrays.asList(4, 10, 15, 24, 26);
        List<Integer> l2 = Arrays.asList(0, 9, 12, 20);
        List<Integer> l3 = Arrays.asList(5, 8, 22, 30);

        List<List<Integer>> nums = Arrays.asList(l1, l2, l3);
        int[] result = smallestRange_02(nums);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

    }

    public int[] smallestRange_01(List<List<Integer>> nums) {
        int N = 0;
        for (List<Integer> num : nums) N += num.size();
        int[][] ordered = new int[N][2];
        int i = 0;
        int j = 0;
        for (List<Integer> group : nums) {
            for (Integer num : group) {
                ordered[i][0] = num;
                ordered[i][1] = j;
                i++;
            }
            j++;
        }

        Arrays.sort(ordered, (o1, o2) -> (o1[0] - o2[0]));

        int[] ans = new int[2];
        int[] count = new int[nums.size()];
        int winLen = 0;
        int left = 0;
        int right = 0;
        while (right < N) {
            int teamIndexRight = ordered[right][1];
            int teamHitCount = count[teamIndexRight];
            if (teamHitCount == 0) {
                winLen++;
            }
            count[teamIndexRight]++;

            //这里的winLen只是保证最小窗口已经满足长度最小为num.size()，至于左边如何收缩，他都不会小于这个长度，因为设定条件为count[teamIndexLeft] > 1才触发收缩，也就是说count[teamIndexLeft]通过收缩最多降到1，而不会是0，
            //而最小值为1的原因是右窗曾今走过这个index并且已经给了最小覆盖值hit count = 1，只会增加不会降低，左窗只是负责"有限收敛"，而不可能是无限，有限的极限就是将team的hit count降到1,然后不再收缩，让右窗继续延伸
            //比如1,0,0,0,0,0,2（该数组为组别，也就是【1】）不要担心中间过多的0会迫使1被左窗收缩掉，只要右窗还没遇到新的1，右窗始终不会收敛，
            //除非右窗碰到新的1，从而数组更新为1,0,0,0,0,0,2,1，这时组别1的hit count == 2 因为>1，左窗会一直收缩直到0，1，2每个组的hit count 都降至1，最终收敛为0，2，1，可以看到这时多余的0都被收敛掉了
            if (winLen == nums.size()) {
                while (count[ordered[left][1]] > 1) {
                    int teamIndexLeft = ordered[left][1];
                    count[teamIndexLeft]--;
                    left++;
                }
                if ((ans[0] == 0 && ans[1] == 0) || ans[1] - ans[0] > ordered[right][0] - ordered[left][0]) {
                    ans = new int[]{ordered[left][0], ordered[right][0]};
                }
            }
            right++;
        }
        return ans;
    }

    //https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists/submissions/
    //比解法1容易理解，左窗约束条件只有一个，就是是否完全覆盖全部组别，每次到达全部组别的时候左窗开始收敛（维护一个HashMap）
    public int[] smallestRange_02(List<List<Integer>> nums) {
        int N = 0;
        for (List<Integer> num : nums) N += num.size();
        int[][] ordered = new int[N][2];
        int i = 0;
        int j = 0;
        for (List<Integer> group : nums) {
            for (Integer num : group) {
                ordered[i][0] = num;
                ordered[i][1] = j;
                i++;
            }
            j++;
        }
        Arrays.sort(ordered, (o1, o2) -> (o1[0] - o2[0]));
        int[] ans = new int[2];
        HashMap<Integer, Integer> teamHitMap = new HashMap<>();
        int left = 0;
        int right = 0;
        while (right < N) {
            int teamIndexRight = ordered[right][1];
            int currentHitCountRight = teamHitMap.getOrDefault(teamIndexRight,0);
            currentHitCountRight++;
            teamHitMap.put(teamIndexRight, currentHitCountRight);

            while (teamHitMap.size() == nums.size()){
                if ((ans[0] == 0 && ans[1] == 0) || ans[1] - ans[0] > ordered[right][0] - ordered[left][0]) {
                    ans = new int[]{ordered[left][0], ordered[right][0]};
                }

                int teamIndexLeft = ordered[left][1];
                int currentHitCountLeft = teamHitMap.get(teamIndexLeft);
                currentHitCountLeft--;
                teamHitMap.put(teamIndexLeft, currentHitCountLeft);
                if(teamHitMap.get(teamIndexLeft) == 0){
                    teamHitMap.remove(teamIndexLeft);
                }
                left++;
            }
            right++;
        }
        return ans;
    }
}
