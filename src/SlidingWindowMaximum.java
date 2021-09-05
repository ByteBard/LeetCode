import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SlidingWindowMaximum {

    public void run() {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] result = maxSlidingWindow_mliu(nums, k);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);

        }
    }

    // https://leetcode-cn.com/problems/sliding-window-maximum/solution/zhe-hui-yi-miao-dong-bu-liao-liao-de-hua-7fy5/
    public int[] maxSlidingWindow_mliu(int[] nums, int k) {

        int len = nums.length;
        if (len == 0) {
            return nums;
        }
        int[] arr = new int[len - k + 1];
        int arr_index = 0;
        //我们需要维护一个单调递增的双向队列
        Deque<Integer> deque = new LinkedList<>();
        //先将第一个窗口的值按照规则入队
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.offerLast(nums[i]);
        }
        //存到数组里，队头元素
        arr[arr_index++] = deque.peekFirst();
        //移动窗口
        for (int j = k; j < len; j++) {
            //对应咱们的红色情况，则是窗口的前一个元素等于队头元素
            int temp = nums[j - k];
            if (temp == deque.peekFirst()) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.removeLast();
            }
            deque.offerLast(nums[j]);
            arr[arr_index++] = deque.peekFirst();
        }
        return arr;
    }


}
