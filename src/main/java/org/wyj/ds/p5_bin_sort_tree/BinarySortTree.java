package org.wyj.ds.p5_bin_sort_tree;

import java.util.*;

/**
 * @auther 武耀君
 * @date 2024/7/12
 *
 * 二叉排序树，存储键值对
 */
public class BinarySortTree<K extends Comparable<K>, V> {
    /**
     * 根节点
     */
    private Node root;
    /**
     * 树中元素的个数
     */
    private int size;

    public BinarySortTree() { }

    /**
     * 向树中添加元素
     */
    public void add(K key, V value) {
        Node node = new Node(null, key, value, null);
        if (root == null) {
            root = node;
            size++;
            return;
        }
        Node parent = null;
        Node curNode = root;
        while (curNode != null) {
            parent = curNode;
            int cmp = key.compareTo(curNode.key);
            if (cmp < 0) {
                curNode = curNode.left;
            } else if (cmp > 0) {
                curNode = curNode.right;
            } else {
                // 新增的键已存在，修改原先的指针
                curNode.value = value;
                return;
            }
        }
        if (key.compareTo(parent.key) < 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        size++;
    }

    /**
     * 根据键获取值
     */
    public V getValue(K key) {
        Node node = getNode(key);
        return node != null ? node.value : null;
    }

    public boolean contains(K key) {
        return getNode(key) != null;
    }

    /**
     * 移除指定键值对
     */
    public void remove(K key) {
        root = remove(root, key);
    }

    /**
     * 获取树中最大的键
     */
    public K getMaxK() {
        return getMaxNode(root).key;
    }

    /**
     * 获取树中最小的键
     */
    public K getMinK() {
        return getMinNoe(root).key;
    }

    /**
     * 前序遍历
     */
    public List<Entry<K, V>> preTraverse() {
        List<Entry<K, V>> list = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        Node curNode = root;
        stack.push(curNode);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            list.add(new Entry<>(node.key, node.value));
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    /**
     * 中序遍历
     */
    public List<Entry<K, V>> midTraverse() {
        ArrayList<Entry<K, V>> list = new ArrayList<>(size);

        Stack<Node> stack = new Stack<>();
        Node curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop();
            list.add(new Entry<>(curNode.key, curNode.value));
            curNode = curNode.right;
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
        }
        return list;
    }

    /**
     * 后序遍历
     */
    public List<Entry<K, V>> postTraverse() {
        ArrayList<Entry<K, V>> list = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        Node curNode = root;
        Node lastDeal = null;
        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            Node node = stack.pop();
            if (node.right == null || node.right == lastDeal) {
                list.add(new Entry<>(node.key, node.value));
                lastDeal = node;
            } else {
                stack.push(node);
                curNode = node.right;
            }
        }
        return list;
    }

    /**
     * 层序遍历
     */
    public List<Entry<K, V>> levelTraverse() {
        ArrayList<Entry<K, V>> list = new ArrayList<>();

        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            list.add(new Entry<>(node.key, node.value));
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
        return list;
    }

    /**
     * 树的最大深度
     */
    public int maxDepth() {
        int depth = 0;

        if (root == null) {
            return 0;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.removeFirst();
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return depth;
    }

    /**
     * 树的最小深度
     */
    public int minDepth() {
        int depth = 0;

        if (root == null) {
            return 0;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        while (true) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.removeFirst();
                if (node.left != null) {
                    queue.addLast(node.left);
                } else {
                    return depth;
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                } else {
                    return depth;
                }
            }
        }
    }

    /**
     * 使用递归实现前序遍历：根节点 - 左节点 - 右节点
     */
    public List<Entry<K, V>> preOrder() {
        LinkedList<Entry<K, V>> list = new LinkedList<>();
        preOrder(list, root);
        return list;
    }

    /**
     * 使用递归实现中序遍历：左 - 根 - 右
     */
    public List<Entry<K, V>> midOrder() {
        LinkedList<Entry<K, V>> list = new LinkedList<>();
        midOrder(list, root);
        return list;
    }

    /**
     * 使用递归实现后序遍历：左 - 根 - 右
     */
    public List<Entry<K, V>> postOrder() {
        LinkedList<Entry<K, V>> list = new LinkedList<>();
        postOrder(list, root);
        return list;
    }

    private void postOrder(List<Entry<K, V>> list, Node node) {
        if (node.left != null) {
            postOrder(list, node.left);
        }
        if (node.right != null) {
            postOrder(list, node.right);
        }
        list.add(new Entry<>(node.key, node.value));
    }

    private void midOrder(List<Entry<K, V>> list, Node node) {
        if (node.left != null) {
            midOrder(list, node.left);
        }
        list.add(new Entry<>(node.key, node.value));
        if (node.right != null) {
            midOrder(list, node.right);
        }
    }

    private void preOrder(LinkedList<Entry<K, V>> list, Node node) {
        list.add(new Entry<>(node.key, node.value));
        if (node.left != null) {
            preOrder(list, node.left);
        }
        if (node.right != null) {
            preOrder(list, node.right);
        }
    }

    /**
     * 打印树的图型结构。使用一个二维数组来存储树的图形结构
     */
    public void printTree() {
        if (root == null) {
            System.out.println("[EMPTY]");
            return;
        }
        final String ELEMENT = "  ";
        // 树的最大深度
        int maxDepth = maxDepth();
        // 最后一层理论上的节点数
        int numNodeOfLastLevel = (int) Math.pow(2, (maxDepth - 1));
        // 二维数组的长度，最后一层每个节点之后都用一个单位来分割
        int len = numNodeOfLastLevel + (numNodeOfLastLevel - 1);
        // 存储图形结构的数组
        Object[][] arr = new Object[maxDepth][len];

        // 填充数组
        for (Object[] objArr : arr) {
            Arrays.fill(objArr, ELEMENT);
        }

        writeEleToArray(root, 0, len / 2, arr, maxDepth);

        // 打印二维数组
        for (Object[] objArr : arr) {
            for (Object o : objArr) {
                System.out.print(o);
            }
            System.out.println();
        }
    }

    private void writeEleToArray(Node node, int rowIdx, int colIdx, Object[][] arr, int treeDepth) {
        // 计算当前层元素和下一层元素之间在x轴上的位置插，原理是，假设最后一层和上一层元素的位置插是1，也就是说，
        // 最后一层的元素使用1个分隔符隔开，那么依次类推第一层和第二层元素之间的位置差，就是2的倍数，具体是几倍，
        // 取决于树一共有多少层
        int gap = (int) Math.pow(2, (treeDepth - (rowIdx + 2))); // rowIdx + 2，当前行的行号
        arr[rowIdx][colIdx] = node.key;

        if (node.left != null) {
            writeEleToArray(node.left, rowIdx + 1, colIdx - gap, arr, treeDepth);
        }
        if (node.right != null) {
            writeEleToArray(node.right, rowIdx + 1, colIdx + gap, arr, treeDepth);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空树，使用前序遍历，遍历数组的每一根节点，将它们赋值为null
     */
    public void clear() {
        Node curNode = root;
        root = null;
        size = 0;

        Stack<Node> stack = new Stack<>();
        stack.push(curNode);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            // 删除节点
            Node left = node.left;
            Node right = node.right;
            node.left = null;
            node.right = null;
            node.key = null;
            node.value = null;

            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
        }
    }

    private Node remove(Node node, K key) {
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left != null && node.right != null) {
                Node minNoe = getMinNoe(node.right);
                node.key = minNoe.key;
                node.value = minNoe.value;
                node.right = remove(node.right, node.key);
            } else if (node.left != null) {
                node = node.left;
                size--;
            } else if (node.right != null) {
                node = node.right;
                size--;
            } else {
                node = null;
                size--;
            }
            return node;
        }
        return node;
    }

    /**
     * 获取某个子树下的最大节点
     */
    private Node getMaxNode(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 获取某个子树下的最小节点
     */
    private Node getMinNoe(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    private Node getNode(K key) {
        Node curNode = root;
        while (curNode != null) {
            int cmp = key.compareTo(curNode.key);
            if (cmp < 0) {
                curNode = curNode.left;
            } else if (cmp > 0) {
                curNode = curNode.right;
            } else {
                return curNode;
            }
        }
        return null;
    }

    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private class Node {
        private Node left;
        private K key;
        private V value;
        private Node right;

        public Node(Node left, K key, V value, Node right) {
            this.left = left;
            this.key = key;
            this.value = value;
            this.right = right;
        }
    }
}
