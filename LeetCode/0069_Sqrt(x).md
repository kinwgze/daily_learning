# 0069.Sqrt(x) x的平方根

给你一个非负整数 x ，计算并返回x的 算术平方根 。

由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。

注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。


输入：x = 4

输出：2

## 思路
这是一个数学题

[LeetCode题解](https://leetcode-cn.com/problems/sqrtx/solution/x-de-ping-fang-gen-by-leetcode-solution/) 给了三种方法，分别
是：袖珍计算器算法（使用指数函数exp和对数函数ln代替平方根）、二分查找、牛顿迭代。这里我觉得有用的，或者说有意义的就是二分查找法。其他两种都是数学方法。

### 结果
1ms  96.06%

38.4MB  16.33%

## 代码
```java
class Solution {
    public int mySqrt(int x) {
        int l = 0;
        int r = x;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
```

## 优化/其他方法
```java

```
