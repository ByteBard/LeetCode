package LeetCode;

public class MinimumWindowSubstring {
/*
76. 最小覆盖子串
给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。



注意：

对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。


示例 1：

输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
示例 2：

输入：s = "a", t = "a"
输出："a"
示例 3:

输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。


提示：

1 <= s.length, t.length <= 105
s 和 t 由英文字母组成


进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */
    public void run() {
//        String s = "DOABECODEBANC";
//        String t = "ABC";
//        System.out.println(minWindow1(s, t));

        String s = "ADOBECODEBANC";
        String t = "ABC";
        String s1 = "abc";
        String t1 = "ggg";
        System.out.println(minWindow1(s, t));

    }

    //98.02, 78.58%
    public String minWindow1(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        int[] need = new int[128];
        //记录需要的字符的个数
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        //l是当前左边界，r是当前右边界，size记录窗口大小，count是需求的字符个数，start是最小覆盖串开始的index
        int l = 0, r = 0, size = Integer.MAX_VALUE, count = t.length(), start = 0;
        //遍历所有字符
        while (r < s.length()) {
            char c = s.charAt(r);
            if (need[c] > 0) {//需要字符c
                count--;
            }
            need[c]--;//把右边的字符加入窗口
            if (count == 0) {//窗口中已经包含所有字符
                while (l < r && need[s.charAt(l)] < 0) {
                    need[s.charAt(l)]++;//释放右边移动出窗口的字符
                    l++;//指针右移
                }
                if (r - l + 1 < size) {//不能右移时候挑战最小窗口大小，更新最小窗口开始的start
                    size = r - l + 1;
                    start = l;//记录下最小值时候的开始位置，最后返回覆盖串时候会用到
                }
                //l向右移动后窗口肯定不能满足了 重新开始循环
                need[s.charAt(l)]++;
                l++;
                count++;
            }
            r++;
        }
        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
    }

    //91%, 45%
    //we are actually maintaining: 1, distance 2, source frequency array; the target frequency array is fixed already
    //each step we move the right index, we are adding new char to the source frequency array, this is mandatory, however, the distance adjustment is based on whether the frequency of char in source frequency array is less (as there maybe)
    public String minWindow2(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen == 0 || tLen == 0 || sLen < tLen) {
            return "";
        }

        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();

        int[] winFreq = new int[128];
        int[] tFreq = new int[128];
        for (char c : charArrayT) {
            tFreq[c]++;
        }

        int distance = 0;
        int minLen = sLen + 1;
        int begin = 0;
        int left = 0;
        int right = 0;

        while (right < sLen) {
            int currentCharRight = charArrayS[right];
            if (winFreq[currentCharRight] < tFreq[currentCharRight]) {
                distance++;
            }

            winFreq[charArrayS[right]]++;

            while (distance == tLen) {
                // core part to keep
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    begin = left;
                }

                char currentCharLeft = charArrayS[left];
                if (winFreq[currentCharLeft] == tFreq[currentCharLeft]) {
                    distance--;
                }
                winFreq[charArrayS[left]]--;
                left++;

            }

            right++;
        }
        if (minLen == sLen + 1) {
            return "";
        }

        return s.substring(begin, begin + minLen);
    }


    //5.71%, 5.21%
    //raw version using hashmap
    public String minWindow(String source, String target) {

        if (source == null || source.length() == 0 || target == null || target.length() == 0) {
            return "";
        }

        char[] sourceCharArr = source.toCharArray();
        int[] sourceHashMap = new int[128];
        int[] targetHashMap = new int[128];
        int[] winHashMap = new int[128];

        int winSize = 0;
        int targetUniqueSize = 0;

        int sourceSize = source.length();
        int targetSize = target.length();

        if (sourceSize < targetSize) return "";
        for (int i = 0; i < sourceSize; i++) {
            sourceHashMap[source.charAt(i)]++;
        }
        for (int i = 0; i < targetSize; i++) {
            if (targetHashMap[target.charAt(i)] == 0) targetUniqueSize++;
            targetHashMap[target.charAt(i)]++;
        }
        int left = 0;
        int right = 0;
        String result = "";
        while (right < sourceSize) {
            int currentCharRight = sourceCharArr[right];
            winHashMap[currentCharRight]++;
            int targetCount = targetHashMap[currentCharRight];
            int currentCount = winHashMap[currentCharRight];
            if (currentCount == targetCount && currentCount > 0 && targetCount > 0) {
                winSize++;
                if (winSize >= targetUniqueSize) {
                    String candidateStr = source.substring(left, right + 1);
                    if (candidateStr.length() < result.length() || result == "") {
                        result = candidateStr;
                    }
                }
            }

            while (left <= right && winSize >= targetUniqueSize) {
                String candidateStr = source.substring(left, right + 1);
                if (candidateStr.length() < result.length() || result == "") {
                    result = candidateStr;
                }

                int currentCharLeft = sourceCharArr[left];
                int targetCountLeft = targetHashMap[currentCharLeft];
                int currentCountLeft = winHashMap[currentCharLeft];
                if (currentCountLeft == targetCountLeft && currentCountLeft > 0 && targetCountLeft > 0) {
                    winSize--;
                }
                winHashMap[currentCharLeft]--;
                left++;
            }

            right++;
        }

        return result;
    }
}
//
//    给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
//         
//
//        注意：
//
//        对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
//        如果 s 中存在这样的子串，我们保证它是唯一的答案。
//         
//
//        示例 1：
//
//        输入：s = "ADOBECODEBANC", t = "ABC"
//        输出："BANC"
//        示例 2：
//
//        输入：s = "a", t = "a"
//        输出："a"
//        示例 3:
//
//        输入: s = "a", t = "aa"
//        输出: ""
//        解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//        因此没有符合条件的子字符串，返回空字符串。
//         
//
//        提示：
//
//        1 <= s.length, t.length <= 105
//        s 和 t 由英文字母组成
//         
//
//        进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/minimum-window-substring
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

