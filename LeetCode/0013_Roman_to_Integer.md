## 13.罗马数字转整数
罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个罗马数字，将其转换成整数。


## 思路，解法一
按位转化为数字，并加到sum中，如果遇到特殊情况，比如IV，IX，XL，XC，CD，CM，减去对应的值即可，
比如IX，先按位加，两位是11，此时发现其是一个组合，即9，减去加上的第一位，再减去组合的差值，即-2,11-2=9
```java
class Solution {
    public int romanToInt(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] ches = s.toCharArray();
        if (ches.length == 1) {
            return roman2Integer(ches[0]);
        }

        int sum = roman2Integer(ches[0]);
        for (int i = 1; i < ches.length; i++) {
            sum += roman2Integer(ches[i]);

            if ((ches[i] == 'V' || ches[i] == 'X') && ches[i - 1] == 'I') {
                sum -= 2;
            }

            if ((ches[i] == 'L' || ches[i] == 'C') && ches[i - 1] == 'X') {
                sum -= 20;
            }

            if ((ches[i] == 'D' || ches[i] == 'M') && ches[i - 1] == 'C') {
                sum -= 200;
            }
        }

        return sum;

    }

    public int roman2Integer(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
```

## 优化思路？解法二
通常情况下，罗马数字中小的数字在大的数字的右边。若输入的字符串满足该情况，那么可以将每个字符视作一个单独的值，累加每个字符对应的数值即可。

例如 XXVII 可视作 X+X+V+I+I=10+10+5+1+1=27X+X+V+I+I=10+10+5+1+1=27 

若存在小的数字在大的数字的左边的情况，根据规则需要减去小的数字。对于这种情况，我们也可以将每个字符视作一个单独的值，若一个数字右侧的数字比它大，则将该数字的符号取反。

例如 XIV 可视作 X-I+V=10-1+5=14。

```java
class Solution {
    Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < s.length(); i ++) {
            int value = map.get(s.charAt(i));
            if (i < s.length() - 1 && value < map.get(s.charAt(i + 1))) {
                sum -= value;
            } else {
                sum += value;
            }
        }
        return sum;
    }
}
```

值得注意的是，解法二虽然比解法一简洁明了，但是其运行时间和消耗内存都比不上解法一。
