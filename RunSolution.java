package LeetCode;

import LeetCode.Hard.Group01.Set03.BasicCalculator_224;
import LeetCode.Hard.Group01.Set03.MinimumNumberOfTapsToOpenToWaterAGarden_1326;

import javax.swing.plaf.basic.BasicTextUI;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class RunSolution {
    public static void main(String[] args) {
        ReverseNodesInKGroup_25 x = new ReverseNodesInKGroup_25();
        x.run();
//        int[] x = new int[]{1, 2, 1, 0, 2, 1, 0, 1};
//        System.out.println(minTapsFormal(7, x));

    }


    public static int minTapsFormal(int n, int[] ranges) {
        int[] pre = new int[n + 1];
        Arrays.fill(pre, Integer.MAX_VALUE);
        for (int i = 0; i <= n; ++i) {
            int left = i - ranges[i];
            int right = i + ranges[i];
            left = left < 0 ? 0 : left;
            right = right > n ? n : right;
            pre[right] = Math.max(pre[right], left);
        }

        int res = 0, breakpoint = n, lowest = Integer.MAX_VALUE;
        for (int i = n; i >= 0; --i) {
            lowest = Math.min(pre[i], lowest);
            if (i == breakpoint) {
                res++;
                if(lowest > breakpoint) return -1;
                if(lowest == 0) return res;
                breakpoint = lowest;
                lowest = Integer.MAX_VALUE;
            }
        }
        return -1;
    }
}
