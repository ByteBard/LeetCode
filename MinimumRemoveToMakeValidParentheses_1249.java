package LeetCode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/*
1249. 移除无效的括号
给你一个由 '('、')' 和小写字母组成的字符串 s。

你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。

请返回任意一个合法字符串。

有效「括号字符串」应当符合以下 任意一条 要求：

空字符串或只包含小写字母的字符串
可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」


示例 1：

输入：s = "lee(t(c)o)de)"
输出："lee(t(c)o)de"
解释："lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
示例 2：

输入：s = "a)b(c)d"
输出："ab(c)d"
示例 3：

输入：s = "))(("
输出：""
解释：空字符串也是有效的
示例 4：

输入：s = "(a(b(c)d)"
输出："a(b(c)d)"


提示：

1 <= s.length <= 10^5
s[i] 可能是 '('、')' 或英文小写字母
 */
public class MinimumRemoveToMakeValidParentheses_1249 {

    //17%, 5.6%
    public String minRemoveToMakeValid_official(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    //78%, 45%
    public String minRemoveToMakeValid02(String s) {
        int length = s.length();
        StringBuilder sb = new StringBuilder();
        //去除多余的右括号jjj
        int leftCount = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftCount++;
                sb.append(c);
            } else if (c == ')') {
                if (leftCount > 0) {
                    leftCount--;
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }
        //去除多余的左括号(从前往后删除也行)
        for (int i = sb.length() - 1; i >= 0 && leftCount > 0; i--) {
            if (sb.charAt(i) == '(') {
                sb.deleteCharAt(i);
                leftCount--;
            }
        }
        return sb.toString();


    }


// 作者：LeetCode
// 链接：https://leetcode-cn.com/problems/minimum-remove-to-make-valid-parentheses/solution/yi-chu-wu-xiao-gua-hao-by-leetcode/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
