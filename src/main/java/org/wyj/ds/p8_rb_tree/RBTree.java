package org.wyj.ds.p8_rb_tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @auther 武耀君
 * @date 2024/7/15
 */
public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    /**
     * 根节点
     */
    private Node root;
    /**
     * 树中元素的个数
     */
    private int size;

    public RBTree() { }

    /**
     * 向树中添加元素
     */
    public void add(K key, V value) {
        Node node = new Node(key, value, RED, null, null, null);
        if (root == null) {
            root = node;
            root.color = BLACK;
            size++;
            return;
        }
        Node p = null;
        Node c = root;
        while (c != null) {
            p = c;
            if (key.compareTo(c.key) < 0) {
                c = c.left;
            } else if (key.compareTo(c.key) > 0) {
                c = c.right;
            } else {
                c.value = value;  // 要新增的键已存在，修改值
                return;
            }
        }

        if (node.key.compareTo(p.key) < 0) {
            p.left = node;
        } else {
            p.right = node;
        }
        node.parent = p;
        size++;
        adjustAfterAdd(node);
    }

    /**
     * 根据键获取值
     */
    public V get(K key) {
        Node node = getNode(key);
        return node != null ? node.value : null;
    }

    /**
     * 移除树中的元素
     */
    public V remove(K key) {
        Node node = getNode(key);
        if (node == null) {
            return null;
        }
        V value = node.value;
        remove(node);
        size--;
        return value;
    }

    /**
     * 打印树的图形结构
     */
    public void printTree() {
        // 判断根节点是否为空
        if (root == null) {
            System.out.println("[EMPTY]");
            return;
        }
        // 1. 构建二维数组，存储树的图形结构，二维数组的深度是树的最大深度，宽度是树的最后一层理论上的节点数
        final String ELE = "    ";
        int depth = getMaxDepth();
        int numNodeOfLastLevel = (int) Math.pow(2, depth - 1);
        int len = numNodeOfLastLevel + (numNodeOfLastLevel - 1);  // 最后一层之间每一个元素都用一个空格来分割
        // 2. 初始化二维数组
        Object[][] objects = new Object[depth][len];
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < len; j++) {
                objects[i][j] = ELE;
            }
        }

        // 3. 把节点位置打印到树中，参数：节点、横坐标、纵坐标、二维数组
        writeArray(root, 0, len / 2, objects, depth);

        // 4. 打印树的图型结构
        for (Object[] objectArr : objects) {
            for (Object o : objectArr) {
                System.out.print(o);
            }
            System.out.println();
        }
    }

    /**
     * 获取树的最大深度
     */
    public int getMaxDepth() {
        int depth = 0;
        LinkedList<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.addLast(root);
        }
        while (!queue.isEmpty()) {
            depth++;
            int len = queue.size();
            for (int i = 0; i < len; i++) {
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
     * 获取树的最小深度
     */
    public int getMinDepth() {
        int depth = 0;
        LinkedList<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.addLast(root);
        }
        while (!queue.isEmpty()) {
            depth++;
            int len = queue.size();
            for (int i = 0; i < len; i++) {
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
        return depth;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 检查当前树是否是一颗正确的红黑树
     */
    public boolean isValidRBTree() {
        if (root == null) {
            return true;
        }
        // 1. 检测根节点的颜色
        if (isRed(root)) {
            return false;
        }

        ArrayList<Node> leafNodeList = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.left != null) {
                if (node.color == RED && node.left.color == RED) {
                    throw new RuntimeException("存在两个连续的红色节点：" + node.key + " " + node.left.key);
                }
                queue.offer(node.left);
            }
            if (node.right != null) {
                if (node.color == RED && node.right.color == RED) {
                    throw new RuntimeException("存在两个连续的红色节点：" + node.key + " " + node.right.key);
                }
                queue.offer(node.right);
            }

            // 将所有的叶子节点收集起来
            if (node.left == null || node.right == null) {
                leafNodeList.add(node);
            }
        }

        // 计算最左侧的路径中一共有多少叶子节点
        int num = 0;
        Node c = root;
        while (c != null) {
            if (c.color == BLACK) {
                num++;
            }
            c = c.left;
        }

        // 遍历所有叶子节点，查看它和最左侧路径中的黑色节点的数目是否一致
        for (Node leafNode : leafNodeList) {
            if (leafNode == null) {
                continue;
            }
            int count = 0;
            Node n = leafNode;
            while (n != null) {
                if (n.color == BLACK) {
                    count++;
                }
                n = n.parent;
            }
            if (count != num) {
                throw new RuntimeException("叶子节点：" + leafNode.key + " 所在的路径中，黑色节点的数目不正确：" + count);
            }
        }

        return true;
    }

    private void remove(Node node) {
        // 情况1：当前节点有两个子节点，获取当前节点的后继节点，然后把后继节点的值赋值给当前节点，然后删除后继节点
        if (node.left != null && node.right != null) {
            Node minNode = maxNode(node.left);// minNode(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            // 删除node的后继节点
            node = minNode;
        }
        // 情况2：被删除的节点只有一个子节点，那么被删除的节点一定是黑色，使用子结点的值代替被删除节点的值，然后删除子结点
        // 如果被删除的节点只有一个子节点，那子节点一定是红色的
        if (node.left != null || node.right != null) {
            Node son = node.left != null ? node.left : node.right;
            node.key = son.key;
            node.value = son.value;
            if (node.left == son) {
                node.left = null;
            } else {
                node.right = null;
            }
            son.value = null;
            son.parent = null;
            return;

        }

        // 情况3：如果当前节点是黑色并且当前节点没有父节点，也没有子节点，那么，当前节点就是根节点
        Node parent = node.parent;
        if (parent == null) {
            root = null;
            return;
        }
        // 情况4：如果当前节点是红色并且没有子节点
        if (isRed(node)) {
            if (parent.left == node) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            node.key = null;
            node.value = null;
            node.parent = null;
            return;
        }
        // 情况5：当前节点是黑色并且没有子节点
        adjustAfterRemove(node);
        Node p = node.parent;
        if (p.left == node) {
            p.left = null;
        } else {
            p.right = null;
        }
        node.parent = null;
    }

    private Node getNode(K key) {
        Node c = root;
        while (c != null) {
            if (key.compareTo(c.key) < 0) {
                c = c.left;
            } else if (key.compareTo(c.key) > 0) {
                c = c.right;
            } else {
                return c;
            }
        }
        return null;
    }

    /**
     * 删除元素前调整红黑树：当被删除的节点是一个黑色节点并且没有子结点时，需要对树进行调整
     */
    private void adjustAfterRemove(Node node) {
        Node parent = node.parent;
        // 1. 需要调整的节点是根节点，将它置为黑色即可
        if (parent == null) {
            node.color = BLACK;
            return;
        }
        boolean isSonOnLeft = parent.left == node;
        // 当前节点是黑色时兄弟节点一定不为null，因为黑色完美平衡性质
        Node bro = isSonOnLeft ? parent.right : parent.left;

        if (isSonOnLeft) {
            // 2. 被删除的节点是父节点的左子节点
            if (isRed(bro)) {
                // 2.1 兄弟节点是红色：父节点的颜色设为红色，兄弟节点的颜色设为黑色，
                parent.color = RED;
                bro.color = BLACK;
                leftRotate(parent);
                adjustAfterRemove(node);
            } else {
                // 2.2 兄弟节点是黑色
                if (isRed(bro.right)) {
                    // 2.2.1 兄弟节点有一个右子节点并且右子节点是红色，此时如果有两个节点，也是这么处理
                    bro.color = parent.color;
                    bro.right.color = BLACK;
                    parent.color = BLACK;
                    leftRotate(parent);
                } else if (isRed(bro.left)) {
                    // 2.2.2 兄弟节点有一个左子节点
                    bro.color = RED;
                    bro.left.color = BLACK;
                    rightRotate(bro);
                    adjustAfterRemove(node);
                } else {
                    // 2.2.3 兄弟节点没有子节点

                    if (parent.color == RED) {
                        // 2.2.3.1 父节点是红色
                        parent.color = BLACK;
                        bro.color = RED;
                    } else {
                        // 2.2.3.2 父节点是黑色
                        bro.color = RED;
                        adjustAfterRemove(parent);
                    }
                }
            }
        } else {
            // 3. 被删除的节点是父节点的右子节点，和左子节点一样的处理，只是方向正好相反

            if (isRed(bro)) {
                parent.color = RED;
                bro.color = BLACK;
                rightRotate(parent);
                adjustAfterRemove(node);
            } else {
                if (isRed(bro.left)) {
                    bro.color = parent.color;
                    bro.left.color = BLACK;
                    parent.color = BLACK;
                    rightRotate(parent);
                } else if (isRed(bro.right)) {
                    bro.color = RED;
                    bro.right.color = BLACK;
                    leftRotate(bro);
                    adjustAfterRemove(node);
                } else {
                    if (parent.color == RED) {
                        parent.color = BLACK;
                        bro.color = RED;
                    } else {
                        bro.color = RED;
                        adjustAfterRemove(parent);
                    }
                }
            }
        }
    }

    /**
     * 添加元素后调整红黑树
     */
    private void adjustAfterAdd(Node node) {
        Node p = node.parent;

        // 1. 如果当前节点是黑色，说明这已经是处理过的、合格的红黑树子树了。
        if (isBlack(node)) {
            return;
        }
        // 2. 如果父节点为空，证明node是根节点，将它染为黑色
        if (p == null) {
            root = node;
            root.color = BLACK;
            return;
        }
        // 3. 父节点的颜色是黑色，不用调整
        if (isBlack(p)) {
            return;
        }

        // 当前节点是红色
        Node gParent = p.parent;

        boolean isParentOnLeft = gParent.left == p;
        Node uncle = isParentOnLeft ? gParent.right : gParent.left;
        boolean isSonOnLeft = p.left == node;

        if (isRed(uncle)) {
            // 4.1 父叔双红
            p.color = BLACK;
            uncle.color = BLACK;
            gParent.color = RED;
            adjustAfterAdd(gParent);
        } else {
            //4.2 叔叔节点是黑色或为空

            if (isParentOnLeft) {
                // 父节点是爷爷节点的左孩子
                if (isSonOnLeft) {
                    // 4.2.1 当前节点是父节点的左孩子
                    p.color = BLACK;
                    gParent.color = RED;
                    rightRotate(gParent);
                } else {
                    // 4.2.2 当前节点是父节点的右孩子，
                    // 对当前节点的父亲节点进行左旋操作，此时情况节就转变为4.2.1了。
                    leftRotate(p);
                    // 更改当前节点。
                    adjustAfterAdd(node.left);
                }
            } else {
                //父亲节点是祖父节点的右孩子
                if (isSonOnLeft) {
                    // 4.2.3 当前节点是父节点的左孩子
                    // 对当前节点的父亲节点进行右旋操作，此时情况节就转变为4.2.4了。
                    rightRotate(p);
                    adjustAfterAdd(node.right);
                } else {
                    //4.2.4 当前节点是父节点的右孩子
                    p.color = BLACK;
                    gParent.color = RED;
                    leftRotate(gParent);
                    adjustAfterAdd(p);
                }
            }
        }
    }

    /**
     * 获取某个子树下的最大节点
     */
    private Node maxNode(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 获取某个子树下的最小节点
     */
    private Node minNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 左旋
     */
    private void leftRotate(Node node) {
        Node p = node.parent;
        Node rn = node.right;

        // 第一步：操作当前节点，将右子节点下的左子结点，挂在到当前节点的右子节点下
        node.right = rn.left;
        if (rn.left != null) {
            rn.left.parent = node;
        }
        // 第二步：将当前节点，变成之前右子节点的子节点
        node.parent = rn;
        rn.left = node;
        // 第三步：设置之前右子节点的父节点
        if (p == null) {
            this.root = rn;
            rn.parent = null;
        } else {
            if (p.left == node) {
                p.left = rn;
            } else {
                p.right = rn;
            }
            rn.parent = p;
        }
    }

    /**
     * 右旋
     */
    private void rightRotate(Node node) {
        Node p = node.parent;
        Node ln = node.left;

        // 1. 操作当前节点，将左子节点下的右子结点，挂在到当前节点的左子节点下
        node.left = ln.right;
        if (ln.right != null) {
            ln.right.parent = node;
        }
        // 2. 将当前节点变成之前左子节点的右子节点
        ln.right = node;
        node.parent = ln;
        // 3. 操作当前节点的右子节点，将它和当前节点的父节点联系起来
        if (p == null) {
            ln.parent = null;
            this.root = ln;
        } else {
            if (p.left == node) {
                p.left = ln;
            } else {
                p.right = ln;
            }
            ln.parent = p;
        }
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private boolean isBlack(Node node) {
        return node != null && node.color == BLACK;
    }

    private void writeArray(Node node, int rowIdx, int colIdx, Object[][] objects, int depth) {
        // 计算当前层父节点和下一层子节点之间在x轴上的距离，原理是，设定最后一层和倒数第二层之间的父子节点之间的
        // 空隙是1，那么依次类推第一层和第二层元素之间的位置差，就是2的倍数，具体是几倍，取决于树一共有多少层
        int gap = (int) Math.pow(2, depth - (rowIdx + 2));   // rowIdx + 2，下一层的行号

        objects[rowIdx][colIdx] = node.key + "-" + (node.color ? "R" : "B");
        if (node.left != null) {
            writeArray(node.left, rowIdx + 1, colIdx - gap, objects, depth);
        }
        if (node.right != null) {
            writeArray(node.right, rowIdx + 1, colIdx + gap, objects, depth);
        }
    }

    private class Node {
        private K key;
        private V value;
        private boolean color;  // true RED、false BLACK
        private Node parent;
        private Node left;
        private Node right;

        public Node(K key, V value, boolean color, Node parent, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }
}
