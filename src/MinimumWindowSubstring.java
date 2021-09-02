public class MinimumWindowSubstring {

    public void run() {
//        String s = "DOABECODEBANC";
//        String t = "ABC";
//        System.out.println(minWindow1(s, t));

       String s = "aaaaaaaaaaaabbbbbcdd";
        String t = "abcdd";

        System.out.println(minWindow2(s, t));

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
            // this part is not necessary
//            if (tFreq[charArrayS[right]] == 0) {
//                right++;
//                continue;
//            }

            //todo: why change to != not work for s = "aaaaaaaaaaaabbbbbcdd"; t = "abcdd";
            //maybe: using the testing strings, for the left part there is a -1 in frequency array, if we change < to != then the left can be 20, otherwise the left can only be 19 at most, and we can debug from left == 19;
            if (winFreq[charArrayS[right]] < tFreq[charArrayS[right]]) {
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

//                if (tFreq[charArrayS[left]] == 0) {
//                    left++;
//                    continue;
//                }

                //this time the left index should already behind the right index, so this position already covered by right index stepping
                // then the compare should be simply via == or !=
                // eg. in string "...AAAA..." when the left index arrive 1st 'A', this position is already covered by right index, so we can simply try to judge the target string coverage by
                if (winFreq[charArrayS[left]] == tFreq[charArrayS[left]]) {
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
}

