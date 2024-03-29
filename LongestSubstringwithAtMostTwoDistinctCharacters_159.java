package LeetCode;

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringwithAtMostTwoDistinctCharacters_159 {
    public void run() {
        String s = "ccaabbb";
        System.out.println(lengthOfLongestSubstringTwoDistinct_mliu02(s));
    }

    public int lengthOfLongestSubstringTwoDistinct_mliu(String s) {
        HashMap<Character, Integer> slideWin = new HashMap<>();

        int left = 0;
        int right = 0;
        int length = 1;
        int size = s.length();
        char[] characters = s.toCharArray();
        while (left <= right && right < size) {

            char currentChar = characters[right];
            slideWin.put(currentChar, slideWin.getOrDefault(currentChar, 0) + 1);

            while (slideWin.size() > 2) {
                char charAtLeft = characters[left];
                slideWin.put(charAtLeft, slideWin.get(charAtLeft) - 1);
                if (slideWin.get(charAtLeft) == 0) {
                    slideWin.remove(charAtLeft);
                }
                left++;
            }

            length = Math.max(length, right - left + 1);
            right++;
        }

        return length;
    }

    // my method bind the count and hashmap together, the official separate them
    public int lengthOfLongestSubstringTwoDistinct_100(String s) {
        int n = s.length();
        if (n < 3) return n;
        char[] arr = s.toCharArray();
        int[] window = new int[256];
        int l = 0, r = 0, count = 0, res = 0;
        while (r < n) {
            char c = arr[r];
            if (window[c] == 0) count++;
            window[c]++;
            if (count < 3) res = Math.max(res, r - l + 1);
            while (count == 3) {
                c = arr[l];
                l++;
                //means this char will not exist in kept arr
                if (window[c] == 1) count--;
                window[c]--;
            }
            r++;
        }
        return res;
    }


    public int LengthOfLongestSubstringTwoDistinct_ml01(String s) {
        int right = 0;
        int left = 0;
        int maxLength = 0;
        int size = s.length();
        HashMap<Character, Integer> hashMap = new HashMap();
        while (right < size) {
            char currentRightChar = s.charAt(right);
            if (hashMap.containsKey(currentRightChar)) {
                int currentValueRight = hashMap.get(currentRightChar);
                hashMap.put(currentRightChar, currentValueRight + 1);
            } else {
                hashMap.put(currentRightChar, 1);
            }

            right++;

            while (hashMap.size() > 2) {
                char currentLeftChar = s.charAt(left);
                int currentValueLeft = hashMap.get(currentLeftChar);
                if (currentValueLeft > 1) {
                    hashMap.put(currentLeftChar, currentValueLeft - 1);
                } else {
                    hashMap.remove(currentLeftChar);
                }

                left++;
            }

            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;

    }

    public int lengthOfLongestSubstringTwoDistinct_mliu02(String s) {
        char[] arr = s.toCharArray();
        char[] win = new char[128];
        int left = 0;
        int right = 0;
        int maxLen = 0;
        int count = 0;
        while (right < arr.length) {
            char rightCur = arr[right];
            if (win[rightCur] == 0) {
                ++count;
            }
            win[rightCur]++;
            while (count > 2) {
                char leftCur = arr[left];
                win[leftCur]--;
                if (win[leftCur] == 0) {
                    --count;
                }
                ++left;
            }

            if (count <= 2) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            ++right;
        }
        return maxLen;
    }
}


//
//    给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
//
//        示例 1:
//
//        输入: "eceba"
//        输出: 3
//        解释: t 是 "ece"，长度为3。
//        示例 2:
//
//        输入: "ccaabbb"
//        输出: 5
//        解释: t 是 "aabbb"，长度为5。
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。