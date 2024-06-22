package coding.lt.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 *
 * @author gongxw <gongxinwen@kuaishou.com>
 * Created on 2024-06-22
 */
public class A39CombinationSum {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        backtracking(candidates, target, path, 0, 0);
        return ans;
    }


    public void backtracking(int[] candidates, int target, List<Integer> path, int sum, int index) {

        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            sum += candidates[i];

            backtracking(candidates, target, path, sum, i);

            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }

}
