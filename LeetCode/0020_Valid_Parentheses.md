# 0020. Valid Parentheses 有效的括号
给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。


## 思路
思路还是挺简单的，使用栈就行了，左括号一律进栈，遇到右括号，从栈顶弹出一个符号，查看是否匹配。若不匹配，直接返回FALSE；

回顾一下java中栈的使用。
```java
    import java.util.Stack;

    Stack<Integer> stack = new Stack<>();
    
    // 常用方法
    序号	方法描述
    1	boolean empty()  // 测试堆栈是否为空。
    2	Object peek( )   // 查看堆栈顶部的对象，但不从堆栈中移除它。
    3	Object pop( )    // 移除堆栈顶部的对象，并作为此函数的值返回该对象。
    4	Object push(Object element)   // 把项压入堆栈顶部。
    5	int search(Object element)    // 返回对象在堆栈中的位置，以 1 为基数。 
        
    
```

### 结果
执行结果：
通过

执行用时： 1 ms , 在所有 Java 提交中击败了 98.87% 的用户

内存消耗： 36.6 MB , 在所有 Java 提交中击败了 31.28% 的用户

## 代码
```java
class Solution {
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[' || stack.empty()) {
                stack.push(ch);
                continue;
            }

            Character character = stack.pop();
            if ((ch == ')' && character != '(') || (ch == '}' && character != '{') || (ch == ']' && character != '[')) {
                return false;
            }
        }
        return stack.empty();
    }
}
```

## 优化/其他方法

### 不使用栈，使用char[]
```java
class Solution {
    public boolean isValid(String s) {
        char[] stack = new char[s.length() + 1];
        int top = 1;
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack[top++] = c;
            } else if (c == ')' && stack[--top] != '(') {
                return false;
            } else if (c == ']' && stack[--top] != '[') {
                return false;
            } else if (c == '}' && stack[--top] != '{') {
                return false;
            }
        }
        return top == 1;
    }
}
```
