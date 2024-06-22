package coding.lt.dp.zeroone;

/**
 * @author gongxw <gongxinwen@kuaishou.com>
 * Created on 2024-06-22
 */
public class A494FindTargetSumWays {

    /**
     * left + right = sum
     * left - (sum - left) = target 推导出 left = (target + sum)/2 。
     * 此时问题就是在集合nums中找出和为left的组合。
     * target是固定的，sum是固定的，left就可以求出来。
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        //如果target的绝对值大于sum，那么是没有方案的
        if (Math.abs(target) > sum) {
            return 0;
        }
        //如果(target+sum)除以2的余数不为0，也是没有方案的
        if ((target + sum) % 2 == 1) {
            return 0;
        }

        int bagSize = (target + sum) / 2;
        int[] dp = new int[bagSize + 1];
        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[bagSize];
    }


    /**
     * // left + right = sum
     * // left - (sum - left) = target
     * // left = (sum + target) / 2;
     */
    public static int findTargetSumWays2(int[] nums, int target) {
        if (nums == null) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (Math.abs(target) > sum) {
            return 0;
        }
        if ((sum + target) % 2 == 1) {
            return 0;
        }

        int w = (sum + target) / 2;
        int[] dp = new int[w + 1];
        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = w; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[w];
    }

    public static void main(String[] args) {
        int[] nums = {7, 9, 3, 8, 0, 2, 4, 8, 3, 9};
        int target = 0;

        System.out.println(findTargetSumWays(nums, target));
        System.out.println(findTargetSumWays2(nums, target));
    }
}
