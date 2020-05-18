package com.lugew.alogrithms4edition.strings.regularexpressions;

import com.lugew.alogrithms4edition.graphs2.directedGraphs.Digraph;

import java.util.Stack;

/**
 * @author LuGew
 * @since 2020/5/18
 */
public class NFA {
    public char[] re;//匹配转换
    private Digraph g;//epsilon转换
    private int M;//状态数量

    public NFA(String regexp) {
        Stack<Integer> ops = new Stack<>();
        re = regexp.toCharArray();
        M = re.length;
        g = new Digraph(M + 1);
        for (int i = 0; i < M; i++) {
            int lp = i;
            if (re[i] == '(' || re[i] == '|') {
                ops.push(i);
            } else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    g.addEdge(lp, or + 1);
                    g.addEdge(or, i);
                } else {
                    lp = or;
                }

            }
            if (i < M - 1 && re[i + 1] == '*') {//查看下一字符
                g.addEdge(lp, i + 1);
                g.addEdge(i + 1, lp);
            }
            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
                g.addEdge(i, i + 1);
            }
        }
    }
}
