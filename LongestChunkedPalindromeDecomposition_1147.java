package LeetCode;

public class LongestChunkedPalindromeDecomposition_1147 {

    public void run() {
        String text = "ghiabcdefhelloadamhelloabcdefghi";
        String text1 = "aaa";
        String text2 = "bqrcnnqrcb";
        int result = longestDecomposition(text2);
        System.out.println(result);
    }

    public int longestDecomposition(String text) {
        int res = 0;
        int left = 1;
        int leftPre = 0;
        int right = text.length() - 1;
        int rightPre = text.length();
        while (left <= right) {

            if(!findMatch(text, leftPre, right, rightPre)){
                right--;
            }else {
                leftPre += (rightPre - right);
                rightPre = right;

                left = leftPre + 1;
                right -= 1;

                res += 2;
            }
        }

        if (text.length() % 2 == 1 || leftPre < text.length() / 2) {
            ++res;
        }

        return res;
    }

    public boolean findMatch(String text, int leftPre, int right, int rightPre) {
        String rightPart = text.substring(right, rightPre);
        String leftPart = text.substring(leftPre, leftPre + (rightPre - right));
        return rightPart.equals(leftPart);
    }

}

//class Solution {
//    public:
//    int longestDecomposition(string text) {
//        int res = 0;
//        int prev = 0;
//        int S = text.size();
//        for (int i = 0; i < S / 2; ++i) {
//            if ((text.substr(prev, i - prev + 1)) == text.substr(S - 1 - i, i - prev + 1)) {
//                res += 2;
//                prev = i + 1;
//            }
//        }
//        if (S % 2 == 1 || prev < S / 2)
//            ++res;
//        return res;
//    }
//};
//
//作者：da-li-wang
//        链接：https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition/solution/c-jian-dan-bian-li-by-da-li-wang/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//class Solution {
//    public int longestDecomposition(String text) {
//        int i = 0, j = text.length() - 1;
//        int ans = 0;
//        int i0 = 0, j0 = text.length() - 1, k = 0;
//        while (i < j) {
//            while (i < j) {
//                if (text.charAt(i++) == text.charAt(j)) {
//                    break;
//                }
//            }
//            k = i--;
//            while (i >= i0) {
//                if (text.charAt(j) != text.charAt(i)) {
//                    break;
//                }
//                i--;
//                j--;
//            }
//            if (i < i0) {
//                ans += 2;
//                i0 = k;
//            } else {
//                j = j0;
//            }
//            i = k;
//            j0 = j;
//        }
//        ans = i0 > j0 ? ans : ans + 1;
//        return ans;
//    }
//}
//
//作者：gskfid
//        链接：https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition/solution/shuang-zhi-zhen-1msji-bai-9811-by-gskfid/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。