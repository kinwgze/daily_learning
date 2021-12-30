1. 两数之和
   给定一个整数数组`nums`和一个整数目标值`target`，请你在该数组中找出和为目标值`target`的那
两个整数，并返回它们的数组**下标**。

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums.length == 0 || nums == null)  return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            int tempNum = target - nums[i];
            if (map.containsKey(tempNum)) {
                return new int[] {i, map.get(tempNum)};
            } else {
                map.put(nums[i], i); // 这里一开始没转过来弯，写了半天的put(tempNum, i)
            }
        }
        return null;
    }
}
```

题和思路都不难。但是卡了还是有一会，主要是map的API不熟。一开始有点转不过来弯。
也刚好复习一下map的几个常用API。

`boolean containsKey(Object k)` 如果此映射将一个或多个键映射到指定值，则返回true。主要用来
查看map中key是否存在，比如这道题。

`Set entrySet( )` 返回此映射中包含的映射关系的 Set 视图。拿到所有的key-value对。

`Object get(Object k)` 返回指定键所映射的值；如果此映射不包含该键的映射关系，则返回 null。（常用）

`Set keySet( )` 返回此映射中包含的键的 Set 视图。

`Object remove(Object k)` 如果存在一个键的映射关系，则将其从此映射中移除（可选操作）。

`int size( )` 返回此映射中的键-值映射关系数。

` boolean containsValue(Object k)` 主要用来判断Map集合中是否包含指定的键值。