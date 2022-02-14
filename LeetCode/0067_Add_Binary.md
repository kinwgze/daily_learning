# 0067.Add Binary 二进制求和

给你两个二进制字符串，返回它们的和（用二进制表示）。

输入为 非空 字符串且只包含数字 1 和 0。

输入：a = "11", b = "1"

输出："100"

## 思路
从后往前加，按不同情况处理。最后reverse。

但是代码写的很繁琐。不好。

改进：

**使用数学方法，将对应位置的数字相加，sum % 2 与 sum / 2 的值用于判断sb.append()的值与是否进位。

使用最大的字符串的长度作为循环判断条件，从而减少判断情况与次数。**

[LeetCode题解](https://leetcode-cn.com/problems/add-binary/solution/er-jin-zhi-qiu-he-by-leetcode-solution/)
### 结果
1ms  100.00%
40.3MB  6.17%

## 代码
```java
class Solution {
    public String addBinary(String a, String b) {
        int x = a.length() - 1;
        int y = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        while (x >= 0 && y >= 0) {
            if (a.charAt(x) == '1' && b.charAt(y) == '1') {
                if (flag) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                flag = true;
            }
            if ((a.charAt(x) == '0' && b.charAt(y) == '1') || (a.charAt(x) == '1' && b.charAt(y) == '0')) {
                if (flag) {
                    sb.append("0");
                    flag = true;
                } else {
                    sb.append("1");
                    flag = false;
                }
            }

            if (a.charAt(x) == '0' && b.charAt(y) == '0') {
                if (flag) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                flag = false;
            }
            x --;
            y --;
        }
        while (x >= 0) {
            if (flag && a.charAt(x) == '1') {
                sb.append("0");
                flag = true;
            } else if (flag && a.charAt(x) == '0') {
                sb.append("1");
                flag = false;
            } else {
                sb.append(a.charAt(x));
            }
            x --;
        }
        while (y >= 0) {
            if (flag && b.charAt(y) == '1') {
                sb.append("0");
                flag = true;
            } else if (flag && b.charAt(y) == '0') {
                sb.append("1");
                flag = false;
            } else {
                sb.append(b.charAt(y));
            }
            y --;
        }
        if (flag) {
            sb.append("1");
        }
        return sb.reverse().toString();
    }
}
```

## 优化/其他方法
```java
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int n = Math.max(a.length(), b.length());
        int carry = 0;

        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            sb.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }
}
```
