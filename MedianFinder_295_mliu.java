package LeetCode;

import java.util.PriorityQueue;

public class MedianFinder_295_mliu {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder_295_mliu() {
        left = new PriorityQueue<>((x, y) -> y - x);
        right = new PriorityQueue<>((x, y) -> x - y);
    }

    public void addNum(int num) {
        if (left.size() == 0) {
            left.offer(num);
        } else if (left.size() == right.size()) {
            //same size, add to left, left size > right size

            // > max left
            if (num > left.peek()) {
                right.offer(num);
                int minRight = right.poll();
                left.offer(minRight);
            } else {
                // <= max left
                left.offer(num);
            }
        } else {
            //left size > right size, add to right

            if (num > left.peek()) {
                right.offer(num);
            } else {
                left.offer(num);
                int maxLeft = left.poll();
                right.offer(maxLeft);
            }

        }
    }

    public double findMedian() {
        if(left.size() == 0 && right.size() == 0) return 0;
        if (left.size() == right.size()) return (double) (left.peek() + right.peek()) / 2;
        return left.peek();
    }
}
