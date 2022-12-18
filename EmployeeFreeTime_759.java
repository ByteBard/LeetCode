package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeFreeTime_759 {
    /*
    We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.



Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
     */

    public void run() {
        Interval i1 = new Interval(1, 2);
        Interval i2 = new Interval(5, 6);
        Interval i3 = new Interval(1, 3);
        Interval i4 = new Interval(4, 10);

        List<Interval> l1 = Arrays.asList(new Interval[]{i1, i2});
        List<Interval> l2 = Arrays.asList(new Interval[]{i3});
        List<Interval> l3 = Arrays.asList(new Interval[]{i4});
        List<List<Interval>> avails = new ArrayList<>();
        avails.add(l1);
        avails.add(l2);
        avails.add(l3);
        for (Interval x : employeeFreeTime(avails)
        ) {
            System.out.println("start: " + x.start);
            System.out.println("end: " + x.end);
        }
        System.out.println(employeeFreeTime(avails));

    }

    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        int OPEN = 0, CLOSE = 1;

        List<int[]> events = new ArrayList();
        for (List<Interval> employee : avails)
            for (Interval iv : employee) {
                events.add(new int[]{iv.start, OPEN});
                events.add(new int[]{iv.end, CLOSE});
            }

        Collections.sort(events, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        List<Interval> ans = new ArrayList();

        int prev = -1, bal = 0;
        for (int[] event : events) {
            // event[0] = time, event[1] = command
            if (bal == 0 && prev >= 0) {
                ans.add(new Interval(prev, event[0]));
            }
            bal += event[1] == OPEN ? 1 : -1;
            prev = event[0];
        }

        return ans;
    }
//
//    作者：LeetCode
//    链接：https://leetcode.cn/problems/employee-free-time/solution/yuan-gong-kong-xian-shi-jian-by-leetcode/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
