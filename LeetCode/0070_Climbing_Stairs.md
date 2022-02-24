# 0070.Climbing Stairs 爬楼梯

假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？


输入：n = 2

输出：2

解释：有两种方法可以爬到楼顶。

1. 1 阶 + 1 阶

2. 2 阶

## 思路
简单的dp

从后往前，即最后一步有两种方案，i = 1或者i = 2

所以，f(x) = f(x - 1) + f (x - 2)

依次类推。

即n = 0时，f(x - 1) = 0  & f (x - 2) = 0， r = 0

n = 1时，f(x - 1) = 1  & f (x - 2) = 0， r = 1

n = 2时，f(x - 1) = 1  & f (x - 2) = 1， r = 2

n = 3时，f(x - 1) = 2  & f (x - 2) = 1， r = 3

...


[LeetCode题解](https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/)

### 结果
0ms  100.00%

37.8MB  26.66%

## 代码
```java
class Solution {
    public int climbStairs(int n) {
        int p = 0;
        int q = 0;
        int r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
```

## 优化/其他方法
```java

```
