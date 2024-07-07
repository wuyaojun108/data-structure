package org.wyj.ds.bin_sort_tree;

import java.util.Iterator;
import java.util.LinkedList;

// 一个存储键值对的二叉树，键不可以为null，不可以重复
public class BinaryTree<K extends Comparable<K>, V> {
    // 二叉树的根节点
    private Node root;

    // 二叉树的大小
    private int size;

    public BinaryTree() {
    }

    // 向二叉树中添加元素。如果根节点为空，直接把新元素赋值给根节点。如果根节点不为空，将根节点赋值给一个临时变量，
    // 作为当前节点；要添加的元素和当前节点比较，如果比当前节点小，当前节点向左移动，如果比当前节点大， 当前节点向
    // 右移动，如果相同，替换当前节点的值；在比较的同时，在循环外记录当前节点的值，当前节点向子结点移动后，这个值就
    // 是子结点的父节点，如果子结点为null，说明找到了合适的父节点，要添加的值和父节点进行比较，添加到父节点之下
    public void put(K key, V value) {
        Node node = new Node(key, value, null, null);
        if (root == null) {
            root = node;
            size++;
            return;
        }
        Node curNode = root;
        Node parent = null;
        while (curNode != null) {
            parent = curNode;
            int cmp = key.compareTo(curNode.key);
            if (cmp < 0) {
                curNode = curNode.left;
            } else if (cmp > 0) {
                curNode = curNode.right;
            } else {
                curNode.key = key;
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

    // 根据键，返回树中的节点
    private Node getNode(K key) {
        Node curNode = root;
        int cmp;
        while (curNode != null) {
            cmp = key.compareTo(curNode.key);
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

    // 判断树中是否包含某个键
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    // 根据键，取值
    public V getValue(K key) {
        Node node = getNode(key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    // 返回树中元素的个数
    public int size() {
        return size;
    }

    // 获取某个子树下最小的节点
    private Node getMinNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 获取某个子树下最大的节点
    private Node getMaxNode(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // 删除树中的某个节点
    public void remove(K key) {
        root = remove(root, key);
    }

    // 使用递归的方式来删除树中的元素，因为树中的节点没有指向父节点的指针。要删除的元素和当前节点的元素向比较，
    // 如果比当前节点小，递归的调用当前方法，删除左子树下的元素，并把执行删除逻辑后的左子树返回给当前节点指向
    // 左子树的指针，如果比当前节点大，也是同理；如果和当前节点的值相同，说明要删除的节点就是当前节点，执行删除
    // 逻辑。删除逻辑分为4种情况：如果当前节点的左子树和右子树都不为空，取当前节点的右子树下的最小值，替换当前节
    // 点的值，递归地调用当前方法，删除当前节点右子树的的最小值；如果左子节点不为空，当前节点等于左子结点；
    // 如果右子结点不为空，当前节点等于右子结点；当前节点没有子节点，当前节点赋为null值。
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            // 执行删除逻辑
            if (node.left != null && node.right != null) {
                Node minNode = getMinNode(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
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

    // 使用递归的方式进行前序遍历，先访问根节点，然后访问递归地访问左子树，然后递归地访问右子树
    public LinkedList<String> preOrderTraverse() {
        LinkedList<String> queue = new LinkedList<>();
        preOrderTraverse(queue, root);
        return queue;
    }

    private void preOrderTraverse(LinkedList<String> queue, Node node) {
        if (node != null) {
            queue.offer(node.key + ": " + node.value);
            if (node.left != null) {
                preOrderTraverse(queue, node.left);
            }
            if (node.right != null) {
                preOrderTraverse(queue, node.right);
            }
        }
    }

    // 使用循环的方式进行前序遍历。如果根节点为空，直接返回一个空队列；根节点赋值给一个临时变量，作为遍历
    // 过程中的当前节点，当前节点入栈；如果栈不为空，进入循环；栈定元素出栈并且处理栈定元素；如果右子结点
    // 不为空，右子结点入栈；如果左子结点不为空，左子结点入栈。
    public LinkedList<String> preOrder() {
        LinkedList<String> queue = new LinkedList<>();
        if (root == null) {
            return queue;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Node curNode = root;
        stack.push(curNode);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            queue.offer(node.key + ": " + node.value);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return queue;
    }

    // 使用递归的方式实现中序遍历，先递归地访问左子树，然后访问根节点，然后递归地访问右子树
    public LinkedList<String> midOrderTraverse() {
        LinkedList<String> queue = new LinkedList<>();
        midOrderTraverse(queue, root);
        return queue;
    }

    private void midOrderTraverse(LinkedList<String> queue, Node node) {
        if (node != null) {
            if (node.left != null) {
                midOrderTraverse(queue, node.left);
            }
            queue.offer(node.key + ": " + node.value);
            if (node.right != null) {
                midOrderTraverse(queue, node.right);
            }
        }
    }

    // 使用循环实现中序遍历。如果根节点为空，直接返回一个空队列；根节点赋值给一个临时变量，作为遍历过程中的当前节点，
    // 如果当前节点不为空或者栈不为空，进入循环，如果当前节点不为空，当前节点入栈，同时当前节点向左移动，继续入栈，
    // 直到树的最左侧；如果栈不为空，栈顶元素出栈并处理，当前节点向右移动。
    // 使用循环实现前序遍历和中序遍历之间的逻辑关系：前序遍历需要先访问根节点，所以根节点需要先入栈；中序遍历先递归
    // 地访问左子树，所以需要先地将左子树的左子结点入栈，直到最左侧，然后访问左子结点，访问左子节点的父节点，父节点
    // 的右子结点入栈。
    public LinkedList<String> midOrder() {
        LinkedList<String> queue = new LinkedList<>();
        if (root == null) {
            return queue;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Node curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop();
            queue.offer(curNode.key + ": " + curNode.value);
            curNode = curNode.right;
        }
        return queue;
    }

    // 使用递归实现后序遍历，先递归地访问左子树，然后递归的访问右子树，最后访问根节点
    public LinkedList<String> postOrderTraverse() {
        LinkedList<String> queue = new LinkedList<>();
        postOrderTraverse(queue, root);
        return queue;
    }

    private void postOrderTraverse(LinkedList<String> queue, Node node) {
        if (node != null) {
            if (node.left != null) {
                postOrderTraverse(queue, node.left);
            }
            if (node.right != null) {
                postOrderTraverse(queue, node.right);
            }
            queue.offer(node.key + ": " + node.value);
        }
    }

    // 使用循环实现后序遍历。如果根节点为空，直接返回一个空队列；根节点赋值给一个临时变量，作为遍历过程中的当前节点，
    // 使用一个临时变量来记录上一次处理的变量。左子节点循环入栈，直到树的最左侧；栈顶元素出栈，如果栈顶元素的右子结点
    // 为null或右子结点是已处理果的节点，处理栈顶元素，否则，栈定元素重新入栈，当前节点向右移动。
    public LinkedList<String> postOrder() {
        LinkedList<String> queue = new LinkedList<>();
        if (root == null) {
            return queue;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Node curNode = root;
        Node lastDealNode = null;
        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            Node node = stack.pop();
            if (node.right == null || node.right == lastDealNode) {
                queue.offer(node.key + ": " + node.value);
                lastDealNode = node;
            } else {
                stack.push(node);
                curNode = node.right;
            }
        }
        return queue;
    }

    // 使用循环实现层序遍历。如果根节点为null，直接返回一个空队列；使用一个队列来辅助遍历。首先根节点入队，
    // 如果队列大小不为空，进入循环，队列头的元素出队并处理，如果元素的左节点不为空，左节点入队，如果
    // 元素的右节点不为空，右节点入队。
    public LinkedList<String> levelOrder() {
        LinkedList<String> resultQueue = new LinkedList<>();
        if (root == null) {
            return resultQueue;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            resultQueue.offer(node.key + ": " + node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return resultQueue;
    }

    // 求树的最大深度
    public int maxDepth() {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.pop();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }

    // 求树的最小深度
    public int minDepth() {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.pop();
                if (node.left == null) {
                    return depth;
                } else {
                    queue.offer(node.left);
                }
                if (node.right == null) {
                    return depth;
                } else {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }

    // 返回迭代器的方法
    public Iterator<String> iterator() {
        return new Itr();
    }

    // 基于中序遍历的迭代器
    private class Itr implements Iterator<String> {

        private final LinkedList<String> queue;

        public Itr() {
            queue = midOrder();
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public String next() {
            return queue.poll();
        }
    }


    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }

    // 二叉树内每个元素的数据类型
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node() { }

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
