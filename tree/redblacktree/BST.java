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
        Node left, right, parent;
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
        return min(root);
    }

    private Key min(Node node) {
        Node currentNode = node;
        if (currentNode == null) {
            return null;
        }
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.key;
    }

    private Node minNode(Node node) {
        Node currentNode = node;
        if (currentNode == null) {
            return null;
        }
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode;
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

    private Node insertNotRecursive(Node node, Key key, Value value) {
        Node currentNode = node;
        Node previous = node;
        int compareResult = 0;
        while (currentNode != null) {
            if (isRed(node.left) && isRed(node.right)) {
                colorFlip(node);
            }
            compareResult = key.compareTo(currentNode.key);
            previous = currentNode;
            if (compareResult > 0) {
                currentNode = currentNode.right;
            } else if (compareResult < 0) {
                currentNode = currentNode.left;
            } else {
                currentNode.value = value;
                return currentNode;
            }
        }
        Node newNode = new Node(key, value, RED);
        newNode.parent = previous;
        if (compareResult > 0) {
            previous.right = newNode;
        } else {
            previous.left = newNode;
        }
        while (previous != null) {
            if (isRed(previous.right)) {
                previous = rotateLeft(previous);
            }
            if (isRed(node.left) && isRed(node.left.left)) {
                previous = rotateRight(previous);
            }
            previous = previous.parent;
        }
        return newNode;
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
        //2-3tree
      /*  if (isRed(node.left) && isRed(node.right)) {
            colorFlip(node);
        }*/
        return node;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = right.left.color;
        right.left.color = RED;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = left.right.color;
        left.right.color = RED;
        return left;
    }

    private Node colorFlip(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
        return node;
    }

    private Node moveRedRight(Node node) {
        colorFlip(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            colorFlip(node);
        }
        return node;
    }

    private Node moveRedLeft(Node node) {
        colorFlip(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            colorFlip(node);
        }
        return node;
    }


    private Node fixUp(Node node) {
        if (isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            colorFlip(node);
        }
        return node;
    }

    public void deleteMax() {
        root = deleteMax(root);
        root.color = BLACK;
    }

    private Node deleteMax(Node node) {
        if (isRed(node.left)) {
            node = rotateRight(node);
        }
        if (node.right == null) {
            return null;
        }
        if (!isRed(node.right) && !isRed(node.right.left)) {
            node = moveRedRight(node);
        }
        node.right = deleteMax(node.right);
        return fixUp(node);
    }

    public void deleteMin() {
        root = deleteMin(root);
        root.color = BLACK;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMax(node.left);
        return fixUp(node);
    }

    private Node delete(Node node, Key key) {
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            //如果左子節點是2節點，將紅色換到左邊
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = delete(node, key);
        } else {
            //將紅色轉換到右邊
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            //找到且無右子節點
            if (compareResult == 0 && (node.right == null)) {
                return null;
            }
            //如果左子節點是2節點，將紅色換到右邊
            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }

            if (compareResult == 0) {  //找到
                Node minNode = minNode(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                node.right = deleteMin(node);
            } else {//繼續尋找
                node.right = delete(node.right, key);
            }
        }
        return fixUp(node);
    }
}
