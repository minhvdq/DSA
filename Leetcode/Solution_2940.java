package Leetcode;

import java.util.*;

/**
 * 2940. Find Building Where Alice and Bob Can Meet
 */
public class Solution_2940 {
    int[] heights;
    int[] nexts;
    HashMap<Long, Integer> map = new HashMap<>();
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        this.heights = heights;
        int len = heights.length;
        nexts = new int[len];
        int[] ans = new int[queries.length];

        computeNextGreater(len);

        int i = 0;
         for( int[] query : queries ) {
            ans[i] = Math.max( nexts[query[0]], nexts[query[1]] ); 
            int mxInd = Math.max( query[0], query[1] );
            int mnInd = Math.min( query[0], query[1] );
            int mxHeight = Math.max( heights[query[0]], heights[query[1]] );
            int indWithMxHeight = heights[mnInd] >= heights[mxInd] ? mnInd : mxInd;
            if( (mnInd == mxInd ) || (heights[mxInd] == mxHeight && heights[mnInd] < mxHeight )) {
                ans[i] = mxInd;
                i ++;
                continue;
            }
            ans[i] = getNearestLargerInd(indWithMxHeight, mxInd);
            i ++;
        }

        return ans;
    }

     private void computeNextGreater(int len) {
        Deque<Integer> stack = new ArrayDeque<>();
        Arrays.fill(nexts, -1);

        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                nexts[i] = stack.peek();
            }
            stack.push(i);
        }
    }

    private int getNearestLargerInd( int numInd, int ind ) {
        long hashedKey = simpleHash( numInd, ind);
        if( map.get(hashedKey) != null ){
            return map.get(hashedKey);
        }
        int num = heights[numInd];
        if( heights[ind] > num ){
            return ind;
        }
        if( nexts[ind] == -1 ){
            return -1;
        }
        int val = getNearestLargerInd( numInd, nexts[ind] );
        map.put(hashedKey, val );
        return val;
    }

    private long simpleHash( int a, int b ){
        return (long) (a - 1) * 50000 + (b - 1);
    }
}