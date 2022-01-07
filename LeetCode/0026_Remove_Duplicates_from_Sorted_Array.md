# 0026.Remove Duplicates from Sorted Array 删除有序数组中的重复项
给你一个有序数组 nums ，请你 `原地` 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

输入：`nums = [1,1,2]`

输出：`2, nums = [1,2]`

解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。**不需要考虑数组中超出新长度后面的元素。**

## 思路
快慢指针法，一个慢指针指向按顺序放数字的地方，一个快指针遍历元素，过滤重复元素。

**思路简单，但是边界条件和细节需要注意。**

建议多看该题的解析。
[LeetCode官方解析](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-tudo/)
### 结果
执行结果：
通过

执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户

内存消耗： 39.7 MB , 在所有 Java 提交中击败了 71.10% 的用户

## 代码
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        int res = 0;
        for (int a = 1; a < nums.length; a++) {
            if (nums[a] == nums[res]) {
                continue;
            }
            res ++;
            nums[res] = nums[a];
        }
        return res + 1;
    }
}
```

## 优化/其他方法
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
```
