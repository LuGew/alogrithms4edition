package tree.redblacktree;

/**
 * 平衡查找樹
 *
 * @author LuGew
 */
public class BST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        boolean color;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        } else {
            return node.color == RED;
        }
    }

    /**
     * BST 和LLBST的查找方法
     *
     * @param key 鍵
     * @return 值
     */
    public Value get(Key key) {
        Node currentNode = root;
        while (currentNode != null) {
            int compareResult = key.compareTo(currentNode.key);
            if (compareResult == 0) {
                return currentNode.value;
            } else if (compareResult > 0) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }
        return null;
    }

    public Key min() {
        Node currentNode = root;
        if (currentNode == null) {
            return null;
        }
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.key;
    }

    /**
     * 插入
     *
     * @param node  節點
     * @param key   鍵
     * @param value 值
     * @return 節點
     */
    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult == 0) {
            node.value = value; //不允許重複值
        } else if (compareResult < 0) {
            node.left = insert(node, key, value);
        } else {
            node.right = insert(node, key, value);
        }
        return node;
    }

    public Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, RED);
        }
        if (isRed(node.left) && isRed(node.right)) {
            colorFlip(node);
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult == 0) {
            node.value = value; //不允許重複值
        } else if (compareResult < 0) {
            node.left = put(node, key, value);
        } else {
            node.right = put(node, key, value);
        }
        if (isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        return node;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = right.left.color;
        right.left.color = RED;
        return node;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = left.right.color;
        left.right.color = RED;
        return node;
    }

    private Node colorFlip(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
        return node;
    }
}
