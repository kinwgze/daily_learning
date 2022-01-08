# 0027.Remove Element 移除元素
给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。

不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

输入：`nums = [3,2,2,3], val = 3`

输出：`2, nums = [2,2]`

解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，
而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。

**并且不需要考虑顺序。**

## 思路
双指针法，没啥好说的。注意边界条件。


### 结果
执行结果：
通过

执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户

内存消耗： 37.1 MB , 在所有 Java 提交中击败了 18.25% 的用户

## 代码
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0 ) {
            return 0;
        }

        int a;
        int b = nums.length - 1;
        for (a = 0; a <= b; a ++) {
            while (b >= 0 && nums[b] == val) {
                b --;
            }
            if (a <= b && nums[a] == val) {
                nums[a] = nums[b];
                b --;
            }
        }
        return b + 1;
    }
}
```

## 优化/其他方法

