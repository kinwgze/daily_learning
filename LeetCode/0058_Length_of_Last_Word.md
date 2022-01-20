# 0058.Length of Last Word 最后一个单词的长度

给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。

单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。

输入：s = "Hello World"

输出：5

## 思路
split啊
遇到空格重新计数啊
倒序查找啊

### 结果
1ms  34.19%
36.8MB  19.82%

## 代码
```java
// split
class Solution {
    public int lengthOfLastWord(String s) {
        String[] str = s.split(" ");
        if (str.length > 0) {
            return str[str.length - 1].length();
        }
        return 0;
    }
}
```

## 优化/其他方法
```java
/**
 * 倒序查找
 * 虽然时间为0ms，100.00%，但是我觉得还是split好
 */
class Solution {
    public int lengthOfLastWord(String s) {
        int length = s.length();
        int res = 0;
        if (length == 0) {
            return res;
        }
        for (int i = length - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            int num = i;
            while (num >= 0 && s.charAt(num) != ' ') {
                res ++;
                num --;
            }
            break;
        }
        return res;
    }
}
```
