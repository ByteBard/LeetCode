package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {

    public void run1() {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring2(s));
    }

    public void run2() {
        String s = "abcbd";
        System.out.println(lengthOfLongestSubstring2(s));
    }

    public int lengthOfLongestSubstring1(String s) {
        // 哈希集合，记录每个字符是否出现过
        // this solution is not perfect
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                rk++;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }
            map.put(currentChar, i);
            max = Math.max(max, i - left + 1);
        }
        return max;

    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        int size = s.length();
        if (size == 0) return 0;


        char[] sourceCharArr = s.toCharArray();
        int[] slideWin = new int[128];
        int maxLength = 1;
        int left = 0;
        int right = 0;

        while (right < size && left <= right) {
            int currentRightChar = sourceCharArr[right];
            slideWin[currentRightChar]++;

            while (slideWin[currentRightChar] > 1) {
                int currentLeftChar = sourceCharArr[left];
                slideWin[currentLeftChar]--;
                left++;
            }

            if (right - left + 1 > maxLength) maxLength = right - left + 1;
            right++;
        }

        return maxLength;
    }

    //https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
//    给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
//             
//
//    示例 1:
//
//    输入: s = "abcabcbb"
//    输出: 3
//    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//    示例 2:
//
//    输入: s = "bbbbb"
//    输出: 1
//    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//    示例 3:
//
//    输入: s = "pwwkew"
//    输出: 3
//    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//                 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//    示例 4:
//
//    输入: s = ""
//    输出: 0
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

}
