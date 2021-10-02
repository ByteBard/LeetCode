public class MinimumWindowSubstring {

    public void run() {
//        String s = "DOABECODEBANC";
//        String t = "ABC";
//        System.out.println(minWindow1(s, t));

        String s = "ADOBECODEBANC";
        String t = "ABC";
        String s1 = "abc";
        String t1 = "ggg";
        System.out.println(minWinSubStr(s, t));

    }

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
            var currentCharRight = charArrayS[right];
            if (winFreq[currentCharRight] < tFreq[currentCharRight]) {
                distance++;
            }

            winFreq[charArrayS[right]]++;
            right++;

            while (distance == tLen) {
                // core part to keep
                if (right - left < minLen) {
                    minLen = right - left;
                    begin = left;
                }

                var currentCharLeft = charArrayS[left];
                if (winFreq[currentCharLeft] == tFreq[currentCharLeft]) {
                    distance--;
                }
                winFreq[charArrayS[left]]--;
                left++;

            }
        }
        if (minLen == sLen + 1) {
            return "";
        }

        return s.substring(begin, begin + minLen);
    }


    public String minWinSubStr(String source, String target) {
        var sourceSize = source.length();
        var targetSize = target.length();
        if (source == null || sourceSize == 0 || target == null || targetSize == 0 || sourceSize < targetSize) {
            return "";
        }
        var sourceCharArr = source.toCharArray();
        var targetHashMap = new int[128];
        var winHashMap = new int[128];
        for (int i = 0; i < targetSize; i++) {
            targetHashMap[target.charAt(i)]++;
        }
        var left = 0;
        var right = 0;
        var begin = 0;
        var minLength = 0;
        var distance = 0;
        while (right < sourceSize) {
            var currentCharRight = sourceCharArr[right];
            var targetCount = targetHashMap[currentCharRight];
            var currentCountRight = winHashMap[currentCharRight];
            if (currentCountRight < targetCount) {
                distance++;
            }
            winHashMap[currentCharRight]++;
            //in the final round right++ is out of bound but still be used for minLength calculation in the left contraction loop
            right++;

            while (distance == targetSize) {
                //in the final round, right would be out of arr bound (+1) but it's necessary to get the distance,
                if(right - left < minLength) minLength = right - left;

                var currentCharLeft = sourceCharArr[left];
                var targetCountLeft = targetHashMap[currentCharLeft];
                var currentCountLeft = winHashMap[currentCharLeft];

                //to get into the loop the slide window should fully contained the target chars (with the char count)
                //so if currentCountLeft == targetCountLeft then it means we find the exact char of the target chars
                // then distance--; and next round it will definitely quit the loop
                if (currentCountLeft == targetCountLeft) {
                    distance--;
                }

                winHashMap[currentCharLeft]--;
                left++;
            }
        }

        if(minLength == targetSize + 1) return "";
        return source.substring(begin, begin + minLength);
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

