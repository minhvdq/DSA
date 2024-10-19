package Leetcode;

import java.util.*;

/**
 * Leetcode 787: Cheapest Flights Within K Stops
 */
public class Solution_787 {
    class Node{ 
        int val;
        List<Node> neighbors;
        List<Integer> weights;

        public Node( int val ){
            this.val = val;
            neighbors = new ArrayList<>();
            weights = new ArrayList<>();
        }
    }

    int n;
    Node[] nodes;
    int[][] dp;
    int source ;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        this.n = n;
        nodes = new Node[n];
        dp = new int[n + 1][k + 3];
        source = src;

        for( int i = 0; i < n; i ++ ) {
            for( int j = 0; j <= k + 2; j ++ ){
                dp[i][j] = -1;
            }
        }

        for( int i = 0; i < n; i ++ ) {
            Node node = new Node(i);
            nodes[i] = node;
        }

        for( int[] flight : flights ) {
            Node from = nodes[ flight[0] ];
            Node to = nodes[ flight[1] ];
            int weight = flight[2];
            to.neighbors.add(from);
            to.weights.add(weight);
        }

        int ans =  getDp(dst, k + 1);
        if( Math.abs(ans) >= 1000000 ){
            return -1;
        }
        return ans;
    }

    private int getDp( int dest, int mxStop ) {
        if( mxStop < 0 ) {
            System.out.println(dest );
            return 1000000;
        }
        if( dest == source ) {
            return 0;
        }
        if( dp[dest][mxStop] != -1 ){
            return dp[dest][mxStop];
        }

        Node node = nodes[dest];
        int minPath = Integer.MAX_VALUE;
        for( int i = 0; i < node.neighbors.size() ; i ++ ) {
            Node nb = node.neighbors.get(i);
            int w = node.weights.get(i);
            int path = getDp(nb.val, mxStop - 1) + w;
            if( path < minPath ){
                minPath = path;
            }
        }

        dp[dest][mxStop] = minPath < 0 || minPath > 1000000 ?  1000000 : minPath;
        return dp[dest][mxStop];
    }

}