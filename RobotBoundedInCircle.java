package LeetCode;

public class RobotBoundedInCircle {

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
