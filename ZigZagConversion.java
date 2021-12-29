package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
    public void run() {
        String s = "AB";
        String result = ZigZagConvertFormal(1, s);
        System.out.println(result);
    }

    public String ZigZagConvertFormal(int numRows, String s) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
//
//    作者：LeetCode
//    链接：https://leetcode-cn.com/problems/zigzag-conversion/solution/z-zi-xing-bian-huan-by-leetcode/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public String ZigZagConvertMingV1(int num, String source) {
        if (num == 1) return source;
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
//6. Z 字形变换
//        将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
//
//        比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
//
//        P   A   H   N
//        A P L S I I G
//        Y   I   R
//        之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
//
//        请你实现这个将字符串进行指定行数变换的函数：
//
//        string convert(string s, int numRows);
//
//
//        示例 1：
//
//        输入：s = "PAYPALISHIRING", numRows = 3
//        输出："PAHNAPLSIIGYIR"
//        示例 2：
//        输入：s = "PAYPALISHIRING", numRows = 4
//        输出："PINALSIGYAHRPI"
//        解释：
//        P     I    N
//        A   L S  I G
//        Y A   H R
//        P     I
//        示例 3：
//
//        输入：s = "A", numRows = 1
//        输出："A"
//
//
//        提示：
//
//        1 <= s.length <= 1000
//        s 由英文字母（小写和大写）、',' 和 '.' 组成
//        1 <= numRows <= 1000
//        通