# 0021.Merge Two Sorted Lists 合并两个有序链表
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]

## 思路
思路挺简单的，就是一个个的往下比较，把较小的值放到结果中。

### 结果
执行结果：
通过

执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户

内存消耗： 37.7 MB , 在所有 Java 提交中击败了 78.22% 的用户

## 代码
```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode previous = new ListNode();
        ListNode result = previous;
        // previous.next = result;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                result.next = list1;
                list1 = list1.next;
                result = result.next;
            } else {
                result.next = list2;
                list2 = list2.next;
                result = result.next;
            }
        }
        if (list1 != null) {
            result.next = list1;
        }
        if (list2 != null) {
            result.next = list2;
        }
        return previous.next;
    }
}
```

## 优化/其他方法
也有不需要新建结果链表的方法，直接合并两个链表。这里不再做过多的描述。核心思路都是类似的。

奇怪的是，同样的代码，在iDEA中跑出来的结果和在LeetCode上跑出来的结果有出入。idea中跑出来得结果会比LeetCode上跑出来的提早一位。
比如对于 '1 2 4 '和'1 3 4'。同样在使用新节点作为头部的情况下，idea跑出来的为 '1 1 2 3 4 4'，LeetCode上跑出来的是'0 1 1 2 3 4 4'。
可能原因是对方法的输出的处理不同吧。

