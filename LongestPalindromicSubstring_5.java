package LeetCode;

public class LongestPalindromicSubstring_5 {
    /*
    5. 最长回文子串
给你一个字符串 s，找到 s 中最长的回文子串。



示例 1：

输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
示例 2：

输入：s = "cbbd"
输出："bb"
示例 3：

输入：s = "a"
输出："a"
示例 4：

输入：s = "ac"
输出："a"


提示：

1 <= s.length <= 1000
s 仅由数字和英文字母（大写和/或小写）组成
     */
    public void run() {
        String source = "babad";
        String result = longestPalindrome(source);
        System.out.println(result);
    }

    //74%， 38%
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            //the length should be (end - start) + 1
            if (len > end - start + 1) {
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
        //this part should always -1 as the updated right and left does not meet the criteria, so the real value should be right - left -2
        //also, the length should be always +1 for right - left index, so the final value is right - left - 2 + 1 = right - left - 1;
        return right - left - 1 ;
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
//
//作者：LeetCode-Solution
//        链接：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。