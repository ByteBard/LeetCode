package LeetCode;

import java.util.Arrays;
import java.util.HashSet;

public class PermutationInString {

    /*
    567. 字符串的排列
给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。

换句话说，s1 的排列之一是 s2 的 子串 。



示例 1：

输入：s1 = "ab" s2 = "eidbaooo"
输出：true
解释：s2 包含 s1 的排列之一 ("ba").
示例 2：

输入：s1= "ab" s2 = "eidboaoo"
输出：false


提示：

1 <= s1.length, s2.length <= 104
s1 和 s2 仅包含小写字母
     */

    public void run() {
        //String s1 = "ab", s2 = "eidboaoo";
        String s1 = "abb", s2 = "aaacccbb";

        boolean result = checkInclusion_mliu01(s1, s2);
        System.out.println(result);

    }

    //66.87%, 38.41%
    public boolean checkInclusion_mliu(String s1, String s2) {
        int left = 0;
        int right = 0;
        int count = 0;
        char[] targetCharArr = s1.toCharArray();
        char[] sourceCharArr = s2.toCharArray();
        int targetArrSize = targetCharArr.length;
        int sourceArrSize = sourceCharArr.length;
        int targetArrFreq[] = new int[128];
        int slideWinArrFreq[] = new int[128];

        if (targetArrSize > sourceArrSize || targetArrSize == 0 || sourceArrSize == 0) return false;

        if (targetArrSize == 1) {
            for (char x :
                    sourceCharArr) {
                if (x == targetCharArr[0]) return true;
            }
        }

        for (char x :
                targetCharArr) {
            targetArrFreq[x]++;
        }

        while (right < sourceArrSize) {
            char currentCharRight = s2.charAt(right);
            slideWinArrFreq[currentCharRight]++;
            right++;
            while (right - left > targetArrSize) {
                char currentCharLeft = s2.charAt(left);
                slideWinArrFreq[currentCharLeft]--;
                left++;
            }
            if (Arrays.equals(slideWinArrFreq, targetArrFreq)) return true;
        }

        return false;
    }

    //66.87%, 23.00%
    public boolean checkInclusion_mliu01(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
        }

        int left = 0;
        int right = 0;
        while (right < m) {
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            //means the char frequency is bigger than target char frequency, we have to remove the scrupulous ones until cnt[x] <= 0 if <0,
            // then it means this char in target string is not fully covered but exist in target string, =0 means exactly fully covered or not exist in target string
            while (cnt[x] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
            right++;
        }
        return false;
    }

    //76.32%, 38.21%
    public boolean checkInclusion_official(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
        }
        int left = 0;
        for (int right = 0; right < m; ++right) {
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            while (cnt[x] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }

//    作者：LeetCode
//    链接：https://leetcode-cn.com/problems/permutation-in-string/solution/zi-fu-chuan-de-pai-lie-by-leetcode-q6tp/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //imitate 76 (same idea)
    //57.29%, 18.9%
    public boolean checkInclusion_official_01(String s1, String s2) {
        char[] pattern = s1.toCharArray();
        char[] text = s2.toCharArray();

        int pLen = s1.length();
        int tLen = s2.length();

        int[] pFreq = new int[26];
        int[] winFreq = new int[26];

        for (int i = 0; i < pLen; i++) {
            pFreq[pattern[i] - 'a']++;
        }

        int pCount = 0;
        for (int i = 0; i < 26; i++) {
            if (pFreq[i] > 0) {
                pCount++;
            }
        }

        int left = 0;
        int right = 0;
        // 当滑动窗口中的某个字符个数与 s1 中对应相等的时候才计数
        int winCount = 0;
        while (right < tLen) {
            if (pFreq[text[right] - 'a'] > 0) {
                winFreq[text[right] - 'a']++;
                if (winFreq[text[right] - 'a'] == pFreq[text[right] - 'a']) {
                    winCount++;
                }
            }
            right++;

            while (pCount == winCount) {
                if (right - left == pLen) {
                    return true;
                }
                if (pFreq[text[left] - 'a'] > 0) {
                    winFreq[text[left] - 'a']--;
                    if (winFreq[text[left] - 'a'] < pFreq[text[left] - 'a']) {
                        winCount--;
                    }
                }
                left++;
            }
        }
        return false;
    }


// Author: Huahua
// Running time: 12 ms
//    class Solution {
//        public:
//        bool checkInclusion(string s1, string s2) {
//            int l1 = s1.length();
//            int l2 = s2.length();
//            vector<int> c1(26);
//            vector<int> c2(26);
//            for (const char c : s1)
//            ++c1[c - 'a'];
//            for (int i = 0; i < l2; ++i) {
//                if (i >= l1)
//                    --c2[s2[i - l1] - 'a'];
//                ++c2[s2[i] - 'a'];
//                if (c1 == c2) return true;
//            }
//            return false;
//        }
//    };
}
