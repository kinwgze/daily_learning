# 0035.Search Insert Position 搜索插入位置

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

请必须使用时间复杂度为`O(log n)`的算法。

输入: nums = [1,3,5,6], target = 5

输出: 2
## 思路
没啥好说的，经典二分法查找，**但是要注意细节和边界条件**


### 结果
0ms  100%
38MB  62.45%

## 代码
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int a = 0;
        int b = nums.length - 1;
        int ans = nums.length;
        while (a <= b) {
            int mid = ((b - a) >> 1) + a;
            if (target <= nums[mid]) {
                ans = mid;
                b = mid - 1;
            } else {
                a = mid + 1;
            }
        }
        return ans;

    }
}
```

## 优化/其他方法

