package LeetCode;

import java.util.PriorityQueue;

class MedianFinder_295 {
    PriorityQueue<Integer> left = new PriorityQueue<>((x, y) -> y - x);
    PriorityQueue<Integer> right = new PriorityQueue<>((x, y) -> x - y);

    public void run() {
        addNum(5);
        addNum(2);
        addNum(4);
        addNum(3);
        addNum(1);
        addNum(6);
        double result = findMedian();
        System.out.println(result);
    }

    //This is my revised version (with more understandable way)
    public void addNum(int num) {
        if (left.size() == 0) {
            left.add(num);
        } else if (left.size() == right.size()) {
            if (num > left.peek()) {
                right.add(num);
            } else {
                int val = left.poll();
                right.add(val);
                left.add(num);
            }
        } else if (left.size() < right.size()) {
            if (num < right.peek()) {
                left.add(num);
            } else {
                int val = right.poll();
                left.add(val);
                right.add(num);
            }
        } else if (left.size() > right.size()) {
            if (num > left.peek()) {
                right.add(num);
            } else {
                int val = left.poll();
                right.add(val);
                left.add(num);
            }
        }
    }

    public double findMedian() {
        if (left.size() < right.size()) {
            return right.peek();
        } else if (left.size() > right.size()) {
            return left.peek();
        } else {
            return (double) (left.peek() + right.peek()) / 2;
        }
    }
}

//作者：AC_OIer
//        链接：https://leetcode-cn.com/problems/find-median-from-data-stream/solution/gong-shui-san-xie-jing-dian-shu-ju-jie-g-pqy8/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
//
//        例如，
//
//        [2,3,4] 的中位数是 3
//
//        [2,3] 的中位数是 (2 + 3) / 2 = 2.5
//
//        设计一个支持以下两种操作的数据结构：
//
//        void addNum(int num) - 从数据流中添加一个整数到数据结构中。
//        double findMedian() - 返回目前所有元素的中位数。
//        示例：
//
//        addNum(1)
//        addNum(2)
//        findMedian() -> 1.5
//        addNum(3)
//        findMedian() -> 2
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/find-median-from-data-stream
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
