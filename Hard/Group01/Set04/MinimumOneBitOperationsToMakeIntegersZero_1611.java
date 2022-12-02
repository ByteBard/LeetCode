package LeetCode.Hard.Group01.Set04;

public class MinimumOneBitOperationsToMakeIntegersZero_1611 {

    public void run() {
        System.out.println(minimumOneBitOperations(6));
    }

    public static int minimumOneBitOperations(int n) {
//decimal to grey:
//        return n ^ (n >> 1);
//grey to decimal:
        int num = n;
        int mask = n;
        while (mask > 0) {
            mask = mask >> 1;
            num = num ^ mask;
        }
        return num;
    }

//    作者：charles-chou
//    链接：https://leetcode.cn/problems/minimum-one-bit-operations-to-make-integers-zero/solution/gen-zhao-ti-shi-zhao-gui-lu-kan-liao-bie-ren-de-ge/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//    https://en.wikipedia.org/wiki/Gray_code
//    https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/description/
}
