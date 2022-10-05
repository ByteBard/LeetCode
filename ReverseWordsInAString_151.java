package LeetCode;

import java.util.*;

/*
151. 翻转字符串里的单词
给你一个字符串 s ，逐个翻转字符串中的所有 单词 。

单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。

请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。

说明：

输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
翻转后单词间应当仅用一个空格分隔。
翻转后的字符串中不应包含额外的空格。


示例 1：

输入：s = "the sky is blue"
输出："blue is sky the"
示例 2：

输入：s = "  hello world  "
输出："world hello"
解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
示例 3：

输入：s = "a good   example"
输出："example good a"
解释：如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
示例 4：

输入：s = "  Bob    Loves  Alice   "
输出："Alice Loves Bob"
示例 5：

输入：s = "Alice does not even like bob"
输出："bob like even not does Alice"


提示：

1 <= s.length <= 104
s 包含英文大小写字母、数字和空格 ' '
s 中 至少存在一个 单词


进阶：

请尝试使用 O(1) 额外空间复杂度的原地解法。

 */
public class ReverseWordsInAString_151 {

    public void run() {
        String s = "the   sky is blue";
        String result = reverseWords02(s);
        System.out.println(result);
    }

    //11%, 26%
//    public String reverseWords01(String s) {
//        // 除去开头和末尾的空白字符
//        s = s.trim();
//        // 正则匹配连续的空白字符作为分隔符分割
//        List<String> wordList = Arrays.asList(s.split("\\s+"));
//        Collections.reverse(wordList);
//        return String.join(" ", wordList);
//    }

    //78%, 59%
    public String reverseWords02(String s) {
        StringBuilder sb = trimSpaces(s);

        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }

    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            ++left;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        // should not be equal
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                //注意这里的end其实不是cursor，是不为空格的char的长度，所以要转换为cursor还需要-1（end-1）
                ++end;
            }
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }

    //23%, 30%
//    public String reverseWords03(String s) {
//        int left = 0, right = s.length() - 1;
//        // 去掉字符串开头的空白字符
//        while (left <= right && s.charAt(left) == ' ') {
//            ++left;
//        }
//
//        // 去掉字符串末尾的空白字符
//        while (left <= right && s.charAt(right) == ' ') {
//            --right;
//        }
//
//        Deque<String> d = new ArrayDeque<String>();
//        StringBuilder word = new StringBuilder();
//
//        while (left <= right) {
//            char c = s.charAt(left);
//            if ((word.length() != 0) && (c == ' ')) {
//                // 将单词 push 到队列的头部
//                d.offerFirst(word.toString());
//                word.setLength(0);
//            } else if (c != ' ') {
//                word.append(c);
//            }
//            ++left;
//        }
//        //do not forget the last word!
//        d.offerFirst(word.toString());
//
//        return String.join(" ", d);
//    }

//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/reverse-words-in-a-string/solution/fan-zhuan-zi-fu-chuan-li-de-dan-ci-by-leetcode-sol/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/reverse-words-in-a-string/solution/fan-zhuan-zi-fu-chuan-li-de-dan-ci-by-leetcode-sol/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/reverse-words-in-a-string/solution/fan-zhuan-zi-fu-chuan-li-de-dan-ci-by-leetcode-sol/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/reverse-words-in-a-string/solution/fan-zhuan-zi-fu-chuan-li-de-dan-ci-by-leetcode-sol/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/reverse-words-in-a-string/solution/fan-zhuan-zi-fu-chuan-li-de-dan-ci-by-leetcode-sol/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}

//https://leetcode-cn.com/problems/reverse-words-in-a-string/solution/fan-zhuan-zi-fu-chuan-li-de-dan-ci-by-leetcode-sol/
