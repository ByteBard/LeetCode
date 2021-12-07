package LeetCode;

public class ZigZagConversion {
    public void run() {
        String s = "AB";
        String result = ZigZagConvertMV(1, s);
        System.out.println(result);
    }

    public String ZigZagConvertMV(int num, String source) {
        if(num == 1) return source;
        int column = 0;
        int row = 0;
        char[] sArr = source.toCharArray();
        int length = source.length();
        int[][] temp = new int[length][2];
        boolean goZ = false;
        for (int i = 0; i < length; i++) {
            temp[i] = new int[]{row, column};
            if (goZ) {
                if (row <= num - 1 && row > 0) {
                    row--;
                    column++;
                    if (row == 0) {
                        goZ = false;
                    }
                }
            } else {
                if (0 <= row && row < num - 1) {
                    row++;
                    if (row == num - 1) {
                        goZ = true;
                    }
                }

            }
        }

        char[][] result = new char[num][column + 1];
        for (int i = 0; i < length; i++) {
            var location = temp[i];
            var y = location[0];
            var x = location[1];
            result[y][x] = sArr[i];
        }

        StringBuilder sb = new StringBuilder();
        for (char[] x : result) {
            for (char c : x) {
                if (c != 0) sb.append(c);
            }
        }
        return sb.toString();
    }
}
