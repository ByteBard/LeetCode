import java.util.Arrays;
import java.util.HashSet;

public class PermutationInString {

    public void run() {
        String s1 = "ab", s2 = "eidboaoo";

        boolean result = checkInclusion_offical(s1, s2);
        System.out.println(result);

    }

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
        while (right < m){
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
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

    public boolean checkInclusion_offical(String s1, String s2) {
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
