package com.lugew.alogrithms4edition.tree.redblacktree;

import com.lugew.alogrithms4edition.tree.util.Tree;

/**
 * 234樹
 *
 * @author LuGew
 */
public class Tree234<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;

    public Tree234() {
    }

    public Tree234(Node<T> root) {
        this.root = root;
    }

    @Override
    public int get(T element) {
        Node<T> currentNode = root;
        int index;
        while (true) {
            if ((index = currentNode.find(element)) != -1) {
                return index;
            } else if (currentNode.isLeaf()) {
                return -1;
            } else {
                currentNode = getNextChild(currentNode, element);
            }
        }
    }

    private void split(Node<T> node) {
        T elementB, elementC;
        Node<T> parent, child2, child3;
        int index;
        elementC = node.remove();
        elementB = node.remove();
        child2 = node.disconnectChild(2);
        child3 = node.disconnectChild(3);

        Node<T> right = new Node<>();
        if (node == root) {
            root = new Node<>();
            parent = root;
            root.connectChild(0, node);
        } else {
            parent = node.getParent();
        }
        index = parent.insert(elementB);
        int n = parent.getSize();
        for (int i = n - 1; i > index; i--) {
            Node<T> temp = parent.disconnectChild(i);
            parent.connectChild(i + 1, temp);
        }
        parent.connectChild(index + 1, right);
        right.insert(elementC);
        right.connectChild(0, child2);
        right.connectChild(1, child3);
    }

    private Node<T> getNextChild(Node<T> node, T element) {
        int index;
        int size = node.getSize();
        for (index = 0; index < size; index++) {
            if (element.compareTo(node.data[index]) < 0) {
                return node.getChild(index);
            }
        }
        return node.getChild(index);
    }

    @Override
    public boolean put(T element) {
        Node<T> currentNode = root;
        while (true) {
            if (currentNode.isFull()) {
                split(currentNode);
                currentNode = currentNode.parent;
                currentNode = getNextChild(currentNode, element);
            } else if (currentNode.isLeaf()) {
                break;
            } else {
                currentNode = getNextChild(currentNode, element);
            }
        }
        currentNode.insert(element);
        return true;
    }

    @Override
    public void display() {

    }

    private class Node<T extends Comparable<T>> {
        private static final int ORDER = 4;
        private int size;
        private Node<T>[] children = new Node[ORDER];
        private Node<T> parent;
        private T[] data = (T[]) new Object[ORDER - 1];

        private Node() {
        }

        public int find(T element) {
            for (int i = 0; i < size; i++) {
                if (data[i].compareTo(element) == 0) {
                    return i;
                }
            }
            return -1;
        }

        public int insert(T element) {
            if (!isFull()) {
                int index = size - 1;
                while (index - 1 >= 0 && element.compareTo(data[index - 1]) < 0) {
                    data[index] = data[index - 1];
                    index--;
                }
                data[index] = element;
                size++;
                return index;
            }
            return -1;
        }

        public T remove() {
            if (!isEmpty()) {
                T element = data[size - 1];
                data[size--] = null;
                return element;
            }
            return null;
        }

        public void connectChild(int childIndex, Node child) {
            children[childIndex] = child;
            if (child != null) {
                child.parent = this;
            }
        }

        public Node<T> disconnectChild(int childIndex) {
            Node<T> child = children[childIndex];
            children[childIndex] = null;
            return child;
        }

        public Node<T> getChild(int childIndex) {
            return children[childIndex];
        }

        public Node<T> getParent() {
            return parent;
        }

        public boolean isLeaf() {
            return children[0] == null;
        }

        public boolean isFull() {
            return size == ORDER - 1;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int getSize() {
            return size;
        }


    }
}
