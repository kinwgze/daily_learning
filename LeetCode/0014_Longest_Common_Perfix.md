# 0014. Longest Common Prefix 最长公共前缀
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

## 思路
从第一个字符串s1开始，使用s1和第二个字符串s2的中较短的一个的长度，匹配较长的字符串中的subString。
如果匹配，则返回较短的字符串，否则，较短的字符串--，即`s1 = s1.substring(0, s1.length() - 1);`
从第一个开始，依次往下比较。

### 结果
执行结果：
通过
执行用时：
0 ms , 在所有 Java 提交中击败了 100.00% 的用户
内存消耗：
36.6 MB , 在所有 Java 提交中击败了 26.14% 的用户

## 代码
```java
class Solution{
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0){
            return "";
        }
        String s = strs[0];
        for (int i = 1; i < strs.length; i++) {
            s = compareString(s, strs[i]);
        }
        return s;
    }

    public String compareString(String s1, String s2) {
        while (s1.length() > 0 && s2.length() > 0) {
            if (s1.length() <= s2.length()) {
                if (s1.equals(s2.substring(0, s1.length()))) {
                    return s1;
                }
                s1 = s1.substring(0, s1.length() - 1);
            } else {
                if (s2.equals(s1.substring(0, s2.length()))) {
                    return s2;
                }
                s2 = s2.substring(0, s2.length() - 1);
            }
        }
        return "";
    }
}
```

## 优化

### 1.对上述方法的优化
如果在尚未遍历完所有的字符串时，最长公共前缀已经是空串，则最长公共前缀一定是空串，因此不需要继续遍历剩下的字符串，直接返回空串即可。
即：
```java
    for (int i = 1; i < strs.length; i++) {
        s = compareString(s, strs[i]);
        if(s.length() == 0){
            break;
        }
    }
```

### 2. 纵向扫描
纵向扫描时，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。

一定要注意边界条件
```java
class Solution{
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0){
            return "";
        }
        int lenth = strs[0].length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lenth; i++) {
            Character ch = strs[0].charAt(i);
            for (int ii = 1; ii < strs.length; ii++) {
                if (i >= strs[ii].length()  || !ch.equals(strs[ii].charAt(i))) {
                    return sb.toString();
                }
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}
```

## 3. java特性解法
java使用startsWith()和substring()就搞定

仅供参考
```java
class Solution{
    public String longestCommonPrefix(String[] strs) {
        String shortest = strs[0];
        for (String str : strs) {
            if (str.length() < shortest.length()) {
                shortest = str;
            }
        }
        String res = shortest;
        for (int i = 0; i < shortest.length(); i++) {
            for (String str : strs) {
                if (!str.startsWith(res)) {
                    res = res.substring(0, res.length() - 1);
                }
            }
        }
        return res;
    }

}
```
