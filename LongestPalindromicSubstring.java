package LeetCode;

public class LongestPalindromicSubstring {
    public void run() {
        String source = "babad";
        String result = longestPalindrome(source);
        System.out.println(result);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }
}
//
//    String substring(int beginIndex, int endIndex)
//    Returns a new string that is a substring of this string.
//    The substring begins at the specified beginIndex and extends to the character at
//    index endIndex – 1. Thus the length of the substring is endIndex-beginIndex.
//    In other words you can say that beginIndex is inclusive and endIndex is exclusive while getting the substring.

// solution
// https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/