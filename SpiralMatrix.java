package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {
//54. 螺旋矩阵
//    https://leetcode-cn.com/problems/spiral-matrix/
//    给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
//
//
//    示例 1：
//
//
//    输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//    输出：[1,2,3,6,9,8,7,4,5]
//    示例 2：
//
//
//    输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//    输出：[1,2,3,4,8,12,11,10,9,5,6,7]
//
//
//    提示：
//
//    m == matrix.length
//    n == matrix[i].length
//1 <= m, n <= 10
//            -100 <= matrix[i][j] <= 100

    public void run() {
        // Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
        // Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        list.add(Arrays.asList(1, 2, 3));
        list.add(Arrays.asList(4, 5, 6));
        list.add(Arrays.asList(7, 8, 9));

        int columnSize = list.get(0).size();
        int rowSize = list.size();

        int[][] convertedArr = new int[columnSize][rowSize];

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                convertedArr[i][j] = list.get(i).get(j);
            }
        }

        List<Integer> result = spiralOrderV2(convertedArr);
        for (int i : result
        ) {
            System.out.println(i);
        }
    }

    //
//    m == matrix.length
//    n == matrix[i].length
//    1 <= m, n <= 10
//    -100 <= matrix[i][j] <= 10
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/spiral-matrix
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<Integer> spiralOrderFormal(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0];
            int nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

//
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/spiral-matrix/solution/luo-xuan-ju-zhen-by-leetcode-solution/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public List<Integer> spiralOrderV2(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix.length == 0) {
            return res;
        }
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (true) {
            for (int col = left; col <= right; ++col) {
                res.add(matrix[top][col]);
            }
            if (++top > bottom) {
                break;
            }
            for (int row = top; row <= bottom; ++row) {
                res.add(matrix[row][right]);
            }
            if (--right < left) {
                break;
            }
            for (int col = right; col >= left; --col) {
                res.add(matrix[bottom][col]);
            }
            if (--bottom < top) {
                break;
            }
            for (int row = bottom; row >= top; --row) {
                res.add(matrix[row][left]);
            }
            if (++left > right) {
                break;
            }
        }
        return res;
    }
}
