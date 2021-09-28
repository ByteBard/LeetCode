import java.util.PriorityQueue;

class MedianFinder {
    PriorityQueue<Integer> l = new PriorityQueue<>((a, b) -> b - a);
    PriorityQueue<Integer> r = new PriorityQueue<>((a, b) -> a - b);

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

    public void addNum(int num) {
        int s1 = l.size(), s2 = r.size();
        if (s1 == s2) {
            if (r.isEmpty() || num <= r.peek()) {
                l.add(num);
            } else {
                l.add(r.poll());
                r.add(num);
            }
        } else {
            if (l.peek() <= num) {
                r.add(num);
            } else {
                r.add(l.poll());
                l.add(num);
            }
        }
    }

    public double findMedian() {
        int s1 = l.size(), s2 = r.size();
        if (s1 == s2) {
            return (l.peek() + r.peek()) / 2.0;
        } else {
            return l.peek();
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