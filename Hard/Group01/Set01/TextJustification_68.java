package LeetCode.Hard.Group01.Set01;

import java.util.ArrayList;
import java.util.List;

//68. 文本左右对齐
//        给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
//
//        你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
//
//        要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
//
//        文本的最后一行应为左对齐，且单词之间不插入额外的空格。
//
//        注意:
//
//        单词是指由非空格字符组成的字符序列。
//        每个单词的长度大于 0，小于等于 maxWidth。
//        输入单词数组 words 至少包含一个单词。
//
//
//        示例 1:
//
//        输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
//        输出:
//        [
//        "This    is    an",
//        "example  of text",
//        "justification.  "
//        ]
//        示例 2:
//
//        输入:words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
//        输出:
//        [
//        "What   must   be",
//        "acknowledgment  ",
//        "shall be        "
//        ]
//        解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
//        因为最后一行应为左对齐，而不是左右两端对齐。
//        第二行同样为左对齐，这是因为这行只包含一个单词。
//        示例 3:
//
//        输入:words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 20
//        输出:
//        [
//        "Science  is  what we",
//        "understand      well",
//        "enough to explain to",
//        "a  computer.  Art is",
//        "everything  else  we",
//        "do                  "
//        ]










//
//class Solution {
//    public List<String> fullJustify(String[] words, int maxWidth) {
//
//    }
//}













public class TextJustification_68 {

    public void run() {
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
//        String[] words = new String[]{"What","must","be","acknowledgment","shall","be"};
        //int maxWidth = 16;
//        String[] words = new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        //int maxWidth = 20;
        List<String> res = fullJustify(words, maxWidth);
        for (String rec : res
        ) {
            System.out.println(rec);
        }
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0, n = words.length;
        while (i < n) {
            int j = i + 1;
            int lineLength = words[i].length();
            while (j < n && (lineLength + words[j].length() + (j - i) <= maxWidth)) {
                lineLength += words[j].length();
                ++j;
            }
            int diff = maxWidth - lineLength;
            int numberOfWords = j - i;
            if (numberOfWords == 1 || j >= n) result.add(leftJustify(words, diff, i, j));
            else result.add(middleJustify(words, diff, i, j));
            i = j;
        }

        return result;
    }

    private String middleJustify(String[] words, int diff, int i, int j) {
        int spaceNeeded = j - i - 1;
        int space = diff / spaceNeeded;
        int extraSpaces = diff % spaceNeeded;
        StringBuilder result = new StringBuilder(words[i]);
        for (int k = i + 1; k < j; ++k) {
            int spacesToApply = space + (extraSpaces-- > 0 ? 1 : 0);
            result.append(" ".repeat(spacesToApply) + words[k]);
        }
        return result.toString();
    }

    private String leftJustify(String[] words, int diff, int i, int j) {
        int spaceOnRight = diff - (j - i - 1);
        StringBuilder result = new StringBuilder(words[i]);
        for (int k = i + 1; k < j; ++k) {
            result.append(" " + words[k]);
        }
        result.append(" ".repeat(spaceOnRight));
        return result.toString();
    }
}
