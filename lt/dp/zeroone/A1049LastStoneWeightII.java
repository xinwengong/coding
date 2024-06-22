package coding.lt.dp.zeroone;

/**
 * @author gongxw <gongxinwen@kuaishou.com>
 * Created on 2024-06-22
 */
public class A1049LastStoneWeightII {

    public static int lastStoneWeightII(int[] stones) {
        if (stones == null) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int weight = sum / 2;
        int[] dp = new int[weight + 1];

        for (int i = 0; i < stones.length; i++) {
            for (int j = weight; j > 0; j--) {
                if (j >= stones[i]) {
                    dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
                }

            }
        }

        int ans = sum - dp[weight] * 2;
        return ans;

    }

    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        System.out.println(lastStoneWeightII(stones));
    }
}
