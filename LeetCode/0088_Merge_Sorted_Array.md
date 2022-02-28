# 0088.Merge Sorted Array 合并两个有序数组

给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。

请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。

注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。


输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3

输出：[1,2,2,3,5,6]

解释：需要合并 [1,2,3] 和 [2,5,6] 。

合并结果是 [**1**,**2**,2,**3**,5,6] ，其中斜体加粗标注的为 nums1 中的元素。



## 思路
双指针遍历

**题目不难，但是一定要注意边界条件，开始和结束的边界条件**

### 结果
0ms  100.00%

41.3MB  16.53%

## 代码
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int sum = m + n;
        int[] res = new int[sum];
        int max = Integer.MAX_VALUE;
        int x = 0;
        int y = 0;
        for (int i = 0; i < sum; i++) {
            int num1 = x < m? nums1[x] : max;
            int num2 = y < n ? nums2[y] : max;
            if (num1 <= num2) {
                res[i] = num1;
                x ++;
            } else {
                res[i] = num2;
                y ++;
            }
        }
        for (int i = 0; i < sum; i++) {
            nums1[i] = res[i];
        }

    }
}
```

## 优化/其他方法
```java
// 逆向双指针
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2 --];
            } else if (p2 == -1) {
                cur = nums1[p1 --];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1 --];
            } else {
                cur = nums2[p2 --];
            }
            nums1[tail--] = cur;
        }
    }
}
```
