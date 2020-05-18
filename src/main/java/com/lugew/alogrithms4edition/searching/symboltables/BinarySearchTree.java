package com.lugew.alogrithms4edition.searching.symboltables;

import java.util.ArrayList;
import java.util.List;

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

    private void putRecursive(Key key, Value value) {
        root = putRecursive(root, key, value);
    }

    private Node putRecursive(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = putRecursive(node.left, key, value);
        } else if (compareResult > 0) {
            node.right = putRecursive(node.right, key, value);
        } else {
            node.value = value;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public boolean deleteMin() {
        root = deleteMin(root);
        return true;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Node deleteMinNotRecursive(Node node) {
        Node currentNode = node;
        Node previousNode = node;
        while (currentNode.left != null) {
            previousNode = currentNode;
            currentNode = currentNode.left;
        }
        if (previousNode == node) {
            previousNode = currentNode.right;
        } else {
            previousNode.left = currentNode.right;
        }
        if (previousNode == null) {
            return null;
        }
        while (previousNode != null) {
            previousNode.size = size(previousNode.left) + size(previousNode.right) + 1;
            node = previousNode;
            previousNode = previousNode.parent;
        }
        return node;
    }

    public Node deleteMaxNotRecursive(Node node) {
        Node currentNode = node;
        Node previousNode = node;
        while (currentNode.right != null) {
            previousNode = currentNode;
            currentNode = currentNode.right;
        }
        if (previousNode == node) {
            previousNode = currentNode.left;
        } else {
            previousNode.right = currentNode.left;
        }
        if (previousNode == null) {
            return null;
        }
        while (previousNode != null) {
            previousNode.size = size(previousNode.left) + size(previousNode.right) + 1;
            node = previousNode;
            previousNode = previousNode.parent;
        }
        return node;
    }

    @Override
    public boolean deleteMax() {
        root = deleteMax(root);
        return true;
    }

    private Node deleteMax(Node node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = deleteMax(node.right);
        node.size = size(node.left) + size(node.right) + 1;
        return node;

    }

    public boolean deleteNotRecursive(Key key) {
        Node currentNode = root;
        Node previousNode = root;
        int compareResult;
        int direction = 0;
        while (currentNode != null) {
            compareResult = key.compareTo(currentNode.key);
            if (compareResult < 0) {
                direction = -1;
                previousNode = currentNode;
                currentNode = currentNode.left;
            } else if (compareResult > 0) {
                direction = 1;
                previousNode = currentNode;
                currentNode = currentNode.right;
            } else {
                break;
            }
        }
        if (currentNode == null) {
            return false;
        }
        Node min = min(currentNode.right);
        Node right = deleteMaxNotRecursive(currentNode.right);
        if (right == null) {
            if (direction > 0) {
                previousNode.right = currentNode.left;
            } else if (direction < 0) {
                previousNode.left = currentNode.left;
            } else {
                root = currentNode.left;
                previousNode = root;
            }
        } else {
            currentNode.key = min.key;
            currentNode.value = min.value;
            currentNode.size -= 1;
            currentNode.right = right;
        }
        while (previousNode != null) {
            previousNode.size = size(previousNode.left) + size(previousNode.right) + 1;
            previousNode = previousNode.parent;
        }

        return true;

    }

    @Override
    public boolean delete(Key key) {
        root = delete(root, key);
        return true;
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = delete(node.left, key);
        } else if (compareResult > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }

            Node temp = node;

            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
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
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
    }

    @Override
    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) {
            return null;
        } else {
            return node.key;
        }
    }

    public Key floorNotRecursive(Key key) {
        Node currentNode = root;
        int compareResult;
        Node floor = null;
        while (currentNode != null) {
            compareResult = key.compareTo(currentNode.key);
            if (compareResult < 0) {
                currentNode = currentNode.left;
            } else if (compareResult > 0) {
                floor = currentNode;
                currentNode = currentNode.right;
            } else {
                floor = currentNode;
            }
        }
        if (floor != null) {
            return floor.key;
        } else {
            return null;
        }
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult == 0) {
            return node;
        }
        if (compareResult < 0) {
            node = floor(node.left, key);
        }
        Node temp = floor(node.right, key);
        if (temp != null) {
            return temp;
        } else {
            return node;
        }
    }

    @Override
    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if (node == null) {
            return null;
        } else {
            return node.key;
        }
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult == 0) {
            return node;
        }
        if (compareResult > 0) {
            node = floor(node.right, key);
        }
        Node temp = floor(node.left, key);
        if (temp != null) {
            return temp;
        } else {
            return node;
        }
    }

    public Key ceilingNotRecursive(Key key) {
        Node currentNode = root;
        int compareResult;
        Node ceiling = null;
        while (currentNode != null) {
            compareResult = key.compareTo(currentNode.key);
            if (compareResult > 0) {
                currentNode = currentNode.right;
            } else if (compareResult < 0) {
                ceiling = currentNode;
                currentNode = currentNode.left;
            } else {
                ceiling = currentNode;
            }
        }
        if (ceiling != null) {
            return ceiling.key;
        } else {
            return null;
        }
    }

    @Override
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rankNotRecursive(Key key) {
        Node currentNode = root;
        int compareResult;
        int rank = 0;
        while (currentNode != null) {
            compareResult = key.compareTo(currentNode.key);
            int currentRank = size(currentNode.left);
            if (compareResult < 0) {
                currentNode = currentNode.left;
            } else if (compareResult > 0) {
                currentNode = currentNode.right;
                rank += currentRank + 1;
            } else {
                rank += currentRank;
            }
        }
        return rank;
    }


    private int rank(Key key, Node node) {
        if (node == null) {
            return 0;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            return rank(key, node.left);
        } else if (compareResult > 0) {
            return 1 + size(node.left) + rank(key, node.right);
        } else {
            return size(node.left);
        }
    }

    @Override
    public Key select(int rank) {
        return select(root, rank).key;
    }

    public Key selectNotRecursive(int rank) {
        Node currentNode = root;

        while (rank > 0) {
            int currentRank = size(currentNode.left);

            if (currentRank < rank) {
                currentNode = currentNode.right;
                rank = rank - currentRank - 1;
            } else if (currentRank > rank) {
                currentNode = currentNode.left;
            } else {
                break;
            }
        }
        if (currentNode != null) {
            return currentNode.key;
        } else {

            return null;
        }
    }

    private Node select(Node node, int rank) {
        if (node == null) {
            return null;
        }
        int size = size(node.left);
        if (size == rank) {
            return node;
        } else if (size > rank) {
            return select(node.left, rank);
        } else {
            return select(node.right, rank - size - 1);
        }
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
    public Iterable<Key> keys(Key low, Key high) {
        List<Key> keys = new ArrayList<>();
        keys(root, keys, low, high);
        return keys;
    }

    private void keys(Node node, List<Key> keys, Key low, Key high) {
        if (node == null) {
            return;
        }
        int compareResultLow = low.compareTo(node.key);
        int compareResultHigh = high.compareTo(node.key);
        if (compareResultLow < 0) {
            keys(node.left, keys, low, high);
        }
        if (compareResultLow <= 0 && compareResultHigh >= 0) {
            keys.add(node.key);
        }
        if (compareResultHigh > 0) {
            keys(node.right, keys, low, high);
        }
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
