import java.util.HashMap;

public class LengthOfLongestSubstringTwoDistinct {
    public void run() {
        String s = "aa";
        System.out.println(lengthOfLongestSubstringTwoDistinct_100(s));
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
}
