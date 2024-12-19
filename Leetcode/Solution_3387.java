package Leetcode;
import java.util.*;

public class Solution_3387{
    class Edge {
        Node dest;
        double weight;
        public Edge( Node dest, double weight ){
            this.dest = dest;
            this.weight = weight;
        }
    }
    class Node{
        boolean mark1 = false;
        boolean mark2 = false;
        String value;
        List<Edge> nbOne = new ArrayList<>();
        List<Edge> nbTwo = new ArrayList<>();

        public Node( String value ) {
            this.value = value;
        }
    }
    HashMap<String, Node> nodeMap = new HashMap<>();
    double mxConverse = 1;
    String initialCurrency;
    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        this.initialCurrency = initialCurrency;
        for( int i = 0; i < pairs1.size(); i ++ ){
            List<String> pair = pairs1.get(i);
            if( nodeMap.get(pair.get(0)) == null ) {
                nodeMap.put( pair.get(0), new Node(pair.get(0)) );
            }
            if( nodeMap.get(pair.get(1)) == null ) {
                nodeMap.put( pair.get(1), new Node(pair.get(1)) );
            }

            Node a = nodeMap.get(pair.get(0));
            Node b = nodeMap.get(pair.get(1));

            double weight = rates1[i];

            a.nbOne.add(new Edge(b, weight));
            b.nbOne.add(new Edge(a, 1/weight));
        }

        for( int i = 0; i < pairs2.size(); i ++ ){
            List<String> pair = pairs2.get(i);
            if( nodeMap.get(pair.get(0)) == null ) {
                nodeMap.put( pair.get(0), new Node(pair.get(0)) );
            }
            if( nodeMap.get(pair.get(1)) == null ) {
                nodeMap.put( pair.get(1), new Node(pair.get(1)) );
            }

            Node a = nodeMap.get(pair.get(0));
            Node b = nodeMap.get(pair.get(1));

            double weight = rates2[i];

            a.nbTwo.add(new Edge(b, weight));
            b.nbTwo.add(new Edge(a, 1/weight));
        }

        Node startNode = nodeMap.get(initialCurrency);
        dfs( startNode, 1, 1);

        return mxConverse;
    }

    private void dfs( Node curNode, int day, double curCurrency){
        if(day == 1 ){
            curNode.mark1 = true;
            for( Edge edge : curNode.nbOne ) {
                Node dest = edge.dest;
                double weight = edge.weight;

                if( dest.mark1 ){
                    continue;
                }

                dfs( dest, 1, curCurrency * weight );
            }

            for( Edge edge : curNode.nbTwo){
                Node dest = edge.dest;
                double weight = edge.weight;

                dfs(dest, 2, curCurrency * weight );
            }

            curNode.mark1 = false;
        }
        else{
            if( curNode.value.equals(initialCurrency )) {
                if( curCurrency > mxConverse ) {
                    mxConverse = curCurrency;
                }
                return;
            }

            curNode.mark2 = true;
            for( Edge edge : curNode.nbTwo){
                Node dest = edge.dest;
                double weight = edge.weight;

                if( dest.mark2 ){
                    continue;
                }

                dfs(dest, 2, curCurrency * weight );
            }

            curNode.mark2 = false;
        }
    }
}