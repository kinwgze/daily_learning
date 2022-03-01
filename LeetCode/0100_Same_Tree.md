# 0100.Same Tree 相同的树

给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。



## 思路
树的比较。遍历比较。BFS DFS都可以。这里我使用了BFS

改进：可以使用迭代。

### 结果
0ms  100.00%

39.2MB  5.02%

## 代码
```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        Deque<TreeNode> aTree = new LinkedList<>();
        Deque<TreeNode> bTree = new LinkedList<>();

        aTree.push(p);
        bTree.push(q);

        while (!aTree.isEmpty()) {
            TreeNode a = aTree.pop();
            TreeNode b = bTree.pop();
            boolean res = a == null && b != null || a != null && b == null || a != null && a.val != b.val;
            if (res) {
                return false;
            }
            if (a != null) {
                aTree.push(a.left);
                aTree.push(a.right);
            }
            if (b != null) {
                bTree.push(b.left);
                bTree.push(b.right);
            }
        }
        if (!bTree.isEmpty()) {
            return false;
        }
        return true;

    }
}
```

## 优化/其他方法
```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) return q == null;
        return q != null && p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```
