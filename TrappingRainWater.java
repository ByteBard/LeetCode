package LeetCode;

import java.util.Stack;

public class TrappingRainWater {

    public void run() {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result = trap_03(height);
        System.out.println(result);
    }

    //请注意max_left和max_right都是要掐头去尾，因为头尾是不需要計算max_left、max_right，没有意义，他们是单边状态，不能形成trap，不能蓄水，一定要形成双边状态才能蓄水，所以不可能是头尾两端
    public int trap_01(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 1; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }


//    我们成功将 max_left 数组去掉了。但是会发现我们不能同时把 max_right 的数组去掉，因为最后的 for 循环是从左到右遍历的，而 max_right 的更新是从右向左的。
//    所以这里要用到两个指针，left 和 right，从两个方向去遍历。
//    那么什么时候从左到右，什么时候从右到左呢？根据下边的代码的更新规则，我们可以知道
//    max_left = Math.max(max_left, height[i - 1]);
//    height [ left - 1] 是可能成为 max_left 的变量， 同理，height [ right + 1 ] 是可能成为 right_max 的变量。
//    只要保证 height [ left - 1 ] < height [ right + 1 ] ，那么 max_left 就一定小于 max_right。
//    因为 max_left 是由 height [ left - 1] 更新过来的，而 height [ left - 1 ] 是小于 height [ right + 1] 的，而 height [ right + 1 ] 会更新 max_right，所以间接的得出 max_left 一定小于 max_right。
//    反之，我们就从右到左更。
//    作者：windliang
//    链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //我的理解是01的解法是为每个元素设定了绝对左右max值，但其实没有必要，例如当height[left - 1] < height[right + 1]时，根据木桶最短板决定容量，height[right + 1]其实是一个限定值，
    // 只要它比height[left - 1]大，它就能约束我们容量计算只能依赖height[left - 1]，如果height[left] < height[left-1]我们才能计算容积，否则越过它判断下一个，可见
    // height[right+1]的任务完成了，它促成了左边的判断
    // 继续推测，如何height[right + 1]往左边有比它大的：没关系，height[left - 1]依然小于这个值，所以height[left]容积是不会变的
    // 如何height[right + 1]往左边有比它大小的：没关系，height[left - 1]就算大于这个值，所以height[left]容积还是不会变，因为height[right + 1]正在围着它
    // 示例：3,2,0,0,0,8,9，可以看到9 > 3这时候2的容积就是以3为顶，因为3是最短板，无论是9往左的数是1还是8，2的容积始终以3为顶
    public int trap_02(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        int right = height.length - 2; // 加右指针进去
        for (int i = 1; i < height.length - 1; i++) {
            //从左到右更
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                left++;
                //从右到左更
            } else {
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;
    }


//    作者：windliang
//    链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    // this is much slower than two-pointer way
    public int trap_03(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }

}
