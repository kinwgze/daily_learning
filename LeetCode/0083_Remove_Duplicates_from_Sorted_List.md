# 0083.Remove Duplicates from Sorted List 删除排序链表中的重复元素

给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次。返回已排序的链表。

输入：head = [1,1,2]

输出：[1,2]


## 思路
遍历即可

**题目不难，但是一定要注意边界条件，开始和结束的边界条件**

### 结果
0ms  100.00%

40.8MB  21.83%

## 代码
```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = head;
        if (pre == null) {
            return head;
        }
        ListNode next = head.next;
        while (pre !=null && next != null) {
            while (next != null && next.val == pre.val) {
                next = next.next;
            }
            pre.next = next;
            pre = next;
            if (next != null) {
                next = next.next;
            }

        }
        return head;
    }
}
```

## 优化/其他方法
```java

```
