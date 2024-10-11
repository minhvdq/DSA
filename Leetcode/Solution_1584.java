package Leetcode;

import java.util.*;

/**
 * Leetcode 1584. Min Cost to Connect All Points
 */
class Solution_1584 {
    int[] parent;
    List<Edge> list = new ArrayList<>();
    int minWeightTotal = 0;
    public int minCostConnectPoints(int[][] points) {
        int leng = points.length;
        parent = new int[leng];
        for( int i = 0; i < leng; i ++ ) {
            parent[i] = i;
            for( int j = i + 1; j < leng; j ++ ){
                int[] point1 = points[i];
                int[] point2 = points[j];
                int w = Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1] );
                list.add( new Edge( i, j, w ) );
            }
        }

        Collections.sort(list, new Comparator<Edge>(){
            @Override
            public int compare( Edge e1, Edge e2 ) {
                return e1.weight - e2.weight;
            }
        });


        int cnt = 0;
        for( Edge e : list ) {
            if( cnt == leng - 1 ){
                break;
            }

            int pa = find(e.src);
            int pb = find(e.dest);
            if( pa != pb ) {
                minWeightTotal += e.weight;
                cnt ++;
                union( pa, pb );
            }
        }

        return minWeightTotal;
    }

    private int find( int a ) {
        if( parent[a] == a ) {
            return a;
        }

        return find( parent[a] );
    }

    private void union( int a, int b ) {
        int pa = find(a);
        int pb = find(b);

        parent[pa] = pb;
    }

    class Edge {
        int src;
        int dest;
        int weight;
        public Edge( int s, int d, int w ){
            src = s;
            dest = d;
            weight = w;
        }
    }
}

