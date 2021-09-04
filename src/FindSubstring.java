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
