package searching.symboltables;

/**
 * 二叉查找樹
 *
 * @author LuGew
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> extends AbstractSymbolTable<Key, Value> {
    private Node root;

    @Override
    public boolean put(Key key, Value value) {
        if (isEmpty()) {
            root = new Node(key, value, 1);
        }
        Node currentNode = root;
        Node previousNode = root;
        int compareResult = 0;
        while (currentNode != null) {
            compareResult = key.compareTo(currentNode.key);
            previousNode = currentNode;
            if (compareResult < 0) {
                currentNode = currentNode.left;
            } else if (compareResult > 0) {
                currentNode = currentNode.right;
            } else {
                currentNode.value = value;
            }
        }
        Node node = new Node(key, value, 1);
        if (compareResult > 0) {
            previousNode.right = node;
        } else if (compareResult < 0) {
            previousNode.left = node;
        }
        node.parent = previousNode;
        while (previousNode != null) {
            previousNode.size += size(previousNode.right) + size(previousNode.left) + 1;
            previousNode = previousNode.parent;
        }
        return true;
    }


    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        while (node != null) {
            int compareResult = key.compareTo(node.key);
            if (compareResult < 0) {
                node = node.left;
            } else if (compareResult > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int rank) {
        return null;
    }

    @Override
    public Iterable<Key> keys(Key low, Key high) {
        return null;
    }

    private class Node {
        private Key key;
        private Value value;
        private Node left, right, parent;
        private int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }


    }
}
