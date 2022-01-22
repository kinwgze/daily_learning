# 0066.Plus One 加一

给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

输入：digits = [1,2,3]

输出：[1,2,4]

解释：输入数组表示数字 123。

## 思路

逆序，找第一个不为9的数字，加一。
如果一直找到开头，仍然为9，新建一个数组，**首位为1，其余全为0**

### 结果
0ms  100.00%
36.8MB  77.12%

## 代码
```java
class Solution {
    public int[] plusOne(int[] digits) {
        int length = digits.length - 1;
        for (int i = length; i >= 0; i--) {
            if (i == 0 && digits[i] == 9) {
                digits[i] = 0;
                int[] res = new int[digits.length + 1];
                res[0] = 1;
                System.arraycopy(digits, 0, res, 1, digits.length);
                return res;
            }
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                return digits;
            }
        }
        return digits;

    }
}
```

## 优化/其他方法
```java
class Solution {
    public int[] plusOne(int[] digits) {
        int length = digits.length - 1;
        for (int i = length; i >= 0; i--) {
            // 这里不需要复制过来，只要全部置0即可。
            if (i == 0 && digits[i] == 9) {
                int[] res = new int[digits.length + 1];
                res[0] = 1;
                return res;
            }
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                return digits;
            }
        }
        return digits;

    }
}
```
