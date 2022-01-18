package LeetCode;

public class RobotBoundedInCircle {
/*
1041. 困于环中的机器人
在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。机器人可以接受下列三条指令之一：

"G"：直走 1 个单位
"L"：左转 90 度
"R"：右转 90 度
机器人按顺序执行指令 instructions，并一直重复它们。

只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。



示例 1：

输入："GGLLGG"
输出：true
解释：
机器人从 (0,0) 移动到 (0,2)，转 180 度，然后回到 (0,0)。
重复这些指令，机器人将保持在以原点为中心，2 为半径的环中进行移动。
示例 2：

输入："GG"
输出：false
解释：
机器人无限向北移动。
示例 3：

输入："GL"
输出：true
解释：
机器人按 (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ... 进行移动。


提示：

1 <= instructions.length <= 100
instructions[i] 在 {'G', 'L', 'R'} 中

https://leetcode-cn.com/problems/robot-bounded-in-circle/
 */
    public void run() {
        String s = "GGLLGG";
        String s1 = "GL";
        boolean result = isRobotBounded(s1);
        System.out.println(result);
    }

    public boolean isRobotBounded(String instructions) {
        // perform some initial sanity checks
        if (instructions.length() >= 1 && instructions.length() <= 100) {

            // create `x`, `y` and `dir` variables
            // for the coordinate system, as well as the direction
            int x = 0;
            int y = 0;
            int dir = 0;

            // loop through all instructions
            for (int i = 0; i < instructions.length(); i++) {
                // get the current instruction
                char ch = instructions.charAt(i);

                if (ch == 'G') {
                    // if Going Forward
                    //  _____
                    // |  0  |
                    // |3 + 1|
                    // |__2__|
                    //
                    // works like a clock:
                    // 0==up (y++)
                    // 1==right (x++)
                    // 2==down (y--)
                    // 3==left (x--)

                    if (dir == 0) y++;
                    if (dir == 3) x--;
                    if (dir == 2) y--;
                    if (dir == 1) x++;

                } else if (ch == 'R') {
                    // change direction if right
                    dir = (dir + 1) % 4;
                } else if (ch == 'L') {
                    // change direction if left
                    dir = (dir + 3) % 4;
                }

            }

            if(x == 0 && y == 0){
                return true;
            }else{
                return dir != 0;
            }
            //return (x == 0 && y == 0) ? true : (dir != 0);
        } else return false;
    }
}


//https://ao.ms/how-to-solve-the-robot-bounded-in-circle-challenge-in-java/
