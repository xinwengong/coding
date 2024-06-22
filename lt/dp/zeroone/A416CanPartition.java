package coding.lt.dp.zeroone;

/**
 * @author gongxw <gongxinwen@kuaishou.com>
 * Created on 2024-06-22
 */
public class A416CanPartition {

    /**
     * 转换成 0-1背包问题
     */
    public static boolean canPartition(int[] nums) {
        if (nums == null) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        //如果是奇数，肯定不行
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];

        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j > 0; j--) {
                // 防止越界
                if (j >= nums[i]) {
                    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                }

                if (dp[target] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums));
    }
}
