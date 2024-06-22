package coding.lt.greedy;

/**
 * @author gongxw <gongxinwen@kuaishou.com>
 * Created on 2024-06-22
 */
public class MakingChange1 {

    /**
     * 最少数目
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int count = 0;
        int coinIndex = coins.length - 1;
        while (coinIndex >= 0) {
            int coin = coins[coinIndex];
            int coinNum = amount / coin;

            count += coinNum;
            amount = amount % coin;
            if (amount == 0) {
                return count;
            }

            coinIndex--;
        }
        return -1;
    }

    ///////////////////////////////////
    // 递归+回溯
    ///////////////////////////////////
    int getMinCoinCountOfValue(int total, int[] values, int valueIndex) {
        int valueCount = values.length;
        if (valueIndex == valueCount) { return Integer.MAX_VALUE; }

        int minResult = Integer.MAX_VALUE;
        int currentValue = values[valueIndex];
        int maxCount = total / currentValue;

        for (int count = maxCount; count >= 0; count --) {
            int rest = total - count * currentValue;

            // 如果rest为0，表示余额已除尽，组合完成
            if (rest == 0) {
                minResult = Math.min(minResult, count);
                break;
            }

            // 否则尝试用剩余面值求当前余额的硬币总数
            int restCount = getMinCoinCountOfValue(rest, values, valueIndex + 1);

            // 如果后续没有可用组合
            if (restCount == Integer.MAX_VALUE) {
                // 如果当前面值已经为0，返回-1表示尝试失败
                if (count == 0) { break; }
                // 否则尝试把当前面值-1
                continue;
            }

            minResult = Math.min(minResult, count + restCount);
        }

        return minResult;
    }

    int getMinCoinCountLoop(int total, int[] values, int k) {
        int minCount = Integer.MAX_VALUE;
        int valueCount = values.length;

        if (k == valueCount) {
            return Math.min(minCount, getMinCoinCountOfValue(total, values, 0));
        }

        for (int i = k; i <= valueCount - 1; i++) {
            // k位置已经排列好
            int t = values[k];
            values[k] = values[i];
            values[i]=t;
            minCount = Math.min(minCount, getMinCoinCountLoop(total, values, k + 1)); // 考虑后一位

            // 回溯
            t = values[k];
            values[k] = values[i];
            values[i]=t;
        }

        return minCount;
    }

    int getMinCoinCountOfValue() {
        int[] values = { 5, 3 }; // 硬币面值
        int total = 11; // 总价
        int minCoin = getMinCoinCountLoop(total, values, 0);

        return (minCoin == Integer.MAX_VALUE) ? -1 : minCoin;  // 输出答案
    }


    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int[] coins2 = {3, 5};

        int amount = 11;
        System.out.println(coinChange(coins, amount));
        System.out.println(coinChange(coins2, amount));
    }
}
