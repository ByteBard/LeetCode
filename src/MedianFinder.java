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