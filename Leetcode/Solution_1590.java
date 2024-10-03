package Leetcode;

import java.util.*;

/**
 * 1590: Make Sum Divisible by P
 */
class Solution_1590 {
    public int minSubarray(int[] nums, int p) {
        int totalRem = 0;
        Map<Integer, Integer> nearestInd = new HashMap<>();
        for( int i = 0; i < nums.length; i ++ ) {
            totalRem = (totalRem + nums[i]) % p;
        }
        int ans = nums.length + 1;
        if( totalRem == 0 ){
            return 0;
        }

        // System.out.println( "total remainder: " + totalRem );
        int total = 0;
        for( int i = 0; i < nums.length; i ++ ){
            total = (total + nums[i]) % p;
            int curRem = total % p < 0 ? total % p + p : total % p;
            int goal = curRem - totalRem < 0 ? curRem - totalRem + p : curRem - totalRem;
            // System.out.printf("cur %d Goal %d \n", curRem, goal);

            if( curRem == totalRem && i != nums.length - 1) {
                if( i + 1 < ans ) {
                    ans = i + 1;
                }
            }

            if( nearestInd.get(goal) != null ){
                int nInd = nearestInd.get(goal);
                
                if( i - nInd < ans ){
                    // System.out.println( i + " " + nInd );
                    ans = i - nInd; 
                }
            }

            nearestInd.put(curRem, i);
        }

        if( ans == nums.length + 1 ) {
            return -1;
        }
        return ans;
    }
}