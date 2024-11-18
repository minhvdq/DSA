package Leetcode;

import java.util.LinkedList;

/**
 * 862. Shortest Subarray with Sum at Least K
*/
public class Solution_862 {
    public int shortestSubarray(int[] nums, int k) {
        int leng = nums.length;
        long[] pref = new long[leng + 1];
        for( int i = 1; i <= leng; i ++ ){
            pref[i] = pref[i - 1] + nums[i - 1];
        }

        LinkedList<Integer> dq = new LinkedList<>();

        int ans = leng + 1;

        dq.offer(0);

        for( int i = 1; i <= leng; i ++ ){
            while( !dq.isEmpty() && pref[i] - pref[dq.peekFirst()] >= k){
                ans = Math.min(ans, i - dq.pollFirst() );
            }
            while( !dq.isEmpty() && pref[dq.peekLast()] >= pref[i] ){
                dq.pollLast();
            }
            dq.offer(i);
        }
        return ans == leng + 1 ? -1 : ans;
    }
}
