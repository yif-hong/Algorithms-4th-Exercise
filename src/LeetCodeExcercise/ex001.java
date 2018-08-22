package LeetCodeExcercise;

import java.util.HashMap;

/**
 * 1. 两数之和
 * <p>
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * <p>
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class ex001 {
    public int[] twoSum(int[] nums, int target) {
        int[] temp = new int[2];
        if (null == nums || nums.length < 2) {
            return temp;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                temp[0] = map.get(target - nums[i]);
                temp[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return temp;
    }
}
