刷题顺序：200题以内的简单题目--200以内的中等题目--200以内的高级题目
记录刷题顺序

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums.length == 0 || nums == null)  return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            int tempNum = target - nums[i];
            if (map.containsKey(tempNum)) {
                return new int[] {i, map.get(tempNum)};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}



