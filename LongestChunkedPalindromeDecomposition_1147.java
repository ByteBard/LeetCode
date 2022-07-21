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
        int length = text.length();
        int preLeft = 0;
        int left = 1;
        int res = 0;
        while (left <= length / 2) {
            String leftStr = text.substring(preLeft, left);
            int right = length - preLeft;
            int preRight = length - left;
            String rightStr = text.substring(preRight, right);
            if (leftStr.equals(rightStr)) {
                preLeft = left;
                res += 2;
            }

            left++;
        }
        if (length % 2 == 1 || preLeft < length / 2) {
            res += 1;
        }
        return res;
    }
}