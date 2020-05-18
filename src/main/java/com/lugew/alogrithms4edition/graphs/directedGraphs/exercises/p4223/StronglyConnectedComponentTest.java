package com.lugew.alogrithms4edition.graphs.directedGraphs.exercises.p4223;

import graphs.directedGraphs.Digraph;

import java.util.Queue;
import java.util.Stack;

/**
 * 强连通分量
 *
 * @author LuGew
 * @since 2018/4/26
 */
public class StronglyConnectedComponentTest {
    private Digraph digraph;
/*    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;*/

    public StronglyConnectedComponentTest(Digraph digraph) {
        this.digraph = digraph;
    }

    public Iterable<Integer> getStronglyConnectedComponent(int vertex) {
        Stack<Integer> order = depthFirstSearch(digraph.reverse(), vertex);
        return depthFirstSearch(digraph, vertex);
    }

    private Stack<Integer> depthFirstSearch(Digraph digraph, int vertex) {
        boolean marked[] = new boolean[digraph.getVertexes()];
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);
        marked[vertex] = true;
        Stack<Integer> reversePost = new Stack<>();
        int temp;
        while (!stack.isEmpty()) {
            temp = -1;
            for (int current :
                    digraph.getAdjacencyVertexes(vertex)) {
                if (!marked[current]) {
                    temp = current;
                    break;
                }
            }
            if (temp == -1) {
//                post.offer(stack.pop());
                reversePost.push(stack.pop());
            } else {
                stack.push(temp);
//                pre.offer(temp);
            }
        }
        return reversePost;
    }


}
