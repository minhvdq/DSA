package Leetcode;
import java.util.*;

/**
 * Leetcode 778: Swim in Rising Water
 */
public class Solution_778 {
    int numRows, numCols;
    int[][] grid;
    boolean[][] mark;
    public int swimInWater(int[][] grid) {
        this.grid = grid;
        numRows = grid.length;
        numCols = grid[0].length;

        mark = new boolean[numRows][numCols];

        PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> {
            return e1[2] - e2[2];
        });

        int[] root = {0, 0, grid[0][0]};
        pq.add(root);
        int curMax = 0;
        while( !pq.isEmpty() ){
            int[] top = pq.poll();
            int row = top[0];
            int col = top[1];
            int val = top[2];
            mark[row][col] = true;

            curMax = Math.max( val, curMax );
            if(row == numRows - 1 && col == numCols - 1 ){
                return curMax;
            }
            

            int[][] pairs = {{row - 1,col}, {row + 1, col}, {row, col - 1}, {row, col + 1}};

            for( int[] pair : pairs ){
                int curR = pair[0];
                int curC = pair[1];
                if( curR < 0 || curC < 0 || curR >= numRows || curC >= numCols || mark[curR][curC] ){
                    continue;
                }
                int[] newPair = {curR, curC, grid[curR][curC]};
                pq.add(newPair);
            }
        }

        return -1;
    }
}