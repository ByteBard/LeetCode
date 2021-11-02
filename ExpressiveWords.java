package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class ExpressiveWords {

    public void run() {
        String S = "heeellooo";
        String[] words = new String[]{"hello", "hi", "helo"};
        int result = expressiveWords(S, words);
        System.out.println(result);
    }

    public int expressiveWords(String S, String[] words) {
        RLE R = new RLE(S);
        int ans = 0;

        for (String word : words) {
            RLE R2 = new RLE(word);
            if (!R.key.equals(R2.key)) continue;
            if (isMatch(R, R2)) {
                ans++;
            } else {
                continue;
            }
        }
        return ans;
    }

    public boolean isMatch(RLE R, RLE R2) {
        for (int i = 0; i < R.counts.size(); ++i) {
            int c1 = R.counts.get(i);
            int c2 = R2.counts.get(i);
            if (c1 < 3 && c1 != c2 || c1 < c2)
                return false;
        }

        return true;
    }
}

class RLE {
    String key;
    List<Integer> counts;

    public RLE(String S) {
        StringBuilder sb = new StringBuilder();
        counts = new ArrayList();

        char[] ca = S.toCharArray();
        int N = ca.length;
        int prev = -1;
        for (int i = 0; i < N; ++i) {
            if (i == N - 1 || ca[i] != ca[i + 1]) {
                sb.append(ca[i]);
                counts.add(i - prev);
                prev = i;
            }
        }

        key = sb.toString();
    }
//
//    作者：LeetCode
//    链接：https://leetcode-cn.com/problems/expressive-words/solution/qing-gan-feng-fu-de-wen-zi-by-leetcode/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
