package Leetcode;
import java.util.*;

/**
 * Leetcode 3203: Find Minimum Diameter After Merging Two Trees
 */
public class Solution_3203 {
    class Node {
        int val;
        List<Node> neighbors = new ArrayList<>();

        public Node( int val ){
            this.val = val;
        }
    }
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        HashMap<Integer, Node> tree1 = buildTree( edges1 );
        HashMap<Integer, Node> tree2 = buildTree( edges2 );

        int dim1 = calcDiameter( tree1 );
        int dim2 = calcDiameter( tree2 );
        
        int half1 = dim1 / 2;
        int half2 = dim2 / 2;

        int newDim = dim1 - half1 + dim2 - half2 + 1;
        int dim = Math.max(dim1, dim2);
        return Math.max(dim, newDim );
    }

    HashMap<Integer, Node> buildTree( int[][] edges ){
        HashMap<Integer, Node> tree = new HashMap<>();
        for( int[] edge : edges ){
            if( !tree.containsKey(edge[0]) ){
                tree.put( edge[0], new Node(edge[0]) );
            }

            if( !tree.containsKey(edge[1]) ){
                tree.put( edge[1], new Node(edge[1]) );
            }

            tree.get(edge[0]).neighbors.add(tree.get(edge[1]));
            tree.get(edge[1]).neighbors.add(tree.get(edge[0]));
        }

        return tree;
    }

    int calcDiameter( HashMap<Integer, Node> tree ){
        if( tree.size() == 0 ){
            return 0;
        }
        Node root = tree.get(0);

        Node curNode = findFurthestNode(root);

        return furthestPath(curNode);
    }

    Node findFurthestNode( Node node ) {
        Queue<Node> q = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        q.add(node);
        set.add(node);
        
        Node furthestNode = node;
        while( !q.isEmpty() ){
            Node top = q.poll();
            furthestNode = top;
            for( Node nb : top.neighbors ){
                if( !set.contains(nb) ){
                    q.add(nb);
                    set.add(nb);
                }
            }
        }

        return furthestNode;
    }

    int furthestPath( Node node ) {
        Queue<Node> q = new LinkedList<>();
        HashMap<Node, Integer> map = new HashMap<>();
        q.add(node);
        map.put(node, 0);
        int cnt =0;

        while( !q.isEmpty() ){
            Node top = q.poll();
            if( map.get(top) > cnt ) {
                cnt = map.get(top);
            }
            for(Node nb : top.neighbors ){
                if(!map.containsKey( nb ) ){
                    map.put(nb, map.get(top) + 1 );
                    q.add(nb);
                }
            }
        }

        return cnt;
    }
}