# 0101.Symmetric Tree 对称二叉树

给你一个二叉树的根节点 root ， 检查它是否轴对称。


## 思路
与上一题 一样的思路，遍历比较。但是不同的是，这次的遍历是反过来的。即左子树对应的是右子树

### 结果
0ms  100.00%

39.8MB  11.65%

## 代码
```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);

    }

    public boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null) {
            return q == null;
        }
        return q != null && p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }
}
```

## 优化/其他方法
```java

```
