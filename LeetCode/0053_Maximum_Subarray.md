# 0053.Maximum Subarray 最大子数组和

给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组 是数组中的一个连续部分。

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]

输出：6

解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

## 思路
~~简单~~动态规划


### 结果
0ms  100%
48.7MB  24.10

## 代码
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int maxAns = nums[0];
        for (int x : nums) {
            // 这句是重点，用于判断x是加入之前的数组，还是从这里重新开始，
            // 如果之前的数组加上x仍然小于x，那么从x重新开始，否则加入。
            pre = Math.max(pre + x, x); 
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}
```

## 优化/其他方法

