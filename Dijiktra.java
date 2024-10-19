import java.util.*;

/**
 * Finding the shortest path from sorce to destination using Dijiktra
 */
class Dijiktra {
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

    int[] sPath;
    Set<Integer> set = new HashSet<>();
    int n;
    Node[] nodes;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst) {
        sPath = new int[n];
        nodes = new Node[n];
        this.n = n;
        for( int i = 0; i < n; i ++ ) {
            Node node = new Node(i);
            nodes[i] = node;
        }

        for( int[] flight : flights ) {
            Node from = nodes[ flight[0] ];
            Node to = nodes[ flight[1] ];
            int weight = flight[2];
            from.neighbors.add(to);
            from.weights.add(weight);
        }

        for( int i = 0; i < n; i ++ ) {
            if( i == src ) {
                sPath[i] = 0;
            }else{
                sPath[i] = Integer.MAX_VALUE;
            }
        }

        dijiktra();
        return sPath[dst];
    }

    private void dijiktra( ){
        while( set.size() < n ) {
            int mnIndex = findMinInd();
            set.add(mnIndex);
            Node node = nodes[mnIndex];
            for( int i = 0; i < node.neighbors.size(); i ++ ){
                Node nb = node.neighbors.get(i);
                int w = node.weights.get(i);
                sPath[nb.val] = sPath[mnIndex] + w; 
            }
        }
    }

    private int findMinInd() {
        int ans = 0;
        int mnValue = Integer.MAX_VALUE;
        for( int i = 0; i < n; i ++ ){
            if( !set.contains(i) && sPath[i] < mnValue ) {
                ans = i;
                mnValue = sPath[i];
            }
        }
        return ans;
    }
}