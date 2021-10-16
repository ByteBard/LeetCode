import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FindSubstring {

    public void run() {
        String s = "wordgoodgoodgoodbestword";
        String[] words = new String[]{ "word","good","best","word"};
        System.out.println((findSubstring(s, words)).toString());
    }

    public List<Integer> findSubstring(String s, String[] words) {

        if (s.isEmpty() || words.length == 0) {
            return null;
        }

        int wordsLength = words.length;

        String firstWord = words[0];
        int firstWordLength = words[0].length();
        HashSet<Integer> result = new HashSet<Integer>();
        HashMap<String, Integer> targetMap = new HashMap<String, Integer>();
        for (String word :
                words) {
            targetMap.put(word, targetMap.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < firstWord.length(); i++) {
            int left = i;
            int right = i;
            int count = 0;
            HashMap<String, Integer> currentMap = new HashMap<String, Integer>();
            while (right + firstWordLength <= s.length()) {
                String currentWord = s.substring(right, right + firstWordLength);
                right += firstWordLength;

                if (targetMap.containsKey(currentWord)) {

                    currentMap.put(currentWord, currentMap.getOrDefault(currentWord,0) + 1);
                    count++;

                    while (currentMap.get(currentWord) > targetMap.get(currentWord)) {
                        String wordStartFromLeft = s.substring(left, left + firstWordLength);
                        left += firstWordLength;
                        currentMap.put(wordStartFromLeft, currentMap.getOrDefault(wordStartFromLeft, 0) - 1);
                        count--;
                    }

                    if (count == wordsLength) {
                        result.add(left);
                    }

                } else {
                    left = right;
                    count = 0;
                    currentMap.clear();
                }
            }


        }

        return new ArrayList<>(result);
    }
}


//
//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
//
//        注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
//
//         
//
//        示例 1：
//
//        输入：s = "barfoothefoobarman", words = ["foo","bar"]
//        输出：[0,9]
//        解释：
//        从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//        输出的顺序不重要, [9,0] 也是有效答案。
//        示例 2：
//
//        输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//        输出：[]
//        示例 3：
//
//        输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//        输出：[6,9,12]
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。