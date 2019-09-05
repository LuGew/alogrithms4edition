package tree.redblacktree;

import tree.util.Tree;

/**
 * 234樹
 *
 * @author LuGew
 */
public class Tree234<T extends Comparable<T>> implements Tree<T> {
    private Node root;

    public Tree234() {
    }

    public Tree234(Node root) {
        this.root = root;
    }

    @Override
    public T get(T element) {
        Node currentNode = root;

        return null;
    }

    @Override
    public boolean put(T element) {
        return false;
    }

    @Override
    public void display() {

    }

    private class Node<T extends Comparable<T>> {
        private static final int ORDER = 4;
        private int size;
        private Node[] children = new Node[ORDER];
        private Node parent;
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

        public Node disconnectChild(int childIndex) {
            Node child = children[childIndex];
            children[childIndex] = null;
            return child;
        }

        public Node getChild(int childIndex) {
            return children[childIndex];
        }

        public Node getParent() {
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
