package Leetcode;

import java.util.*;

/**
 * Leetcode 332 : Reconstruct Itinerary
 */
public class Solution_332 {
    class Node {
        String value;
        PriorityQueue<Node> connections = new PriorityQueue<>((n1, n2) -> {
            return n1.value.compareTo(n2.value);
        });

        public Node( String value) {
            this.value = value;
        }

        public void addConnection( Node node ) {
            connections.add(node);
        }
    }

    HashMap<String, Node> map = new HashMap<>();
    
    public List<String> findItinerary(List<List<String>> tickets) {
        for( List<String> ticket : tickets ){
            String from = ticket.get(0);
            String to = ticket.get(1);

            if( map.get(from) == null ){
                map.put(from , new Node(from));
            }

            if( map.get(to) == null ){
                map.put(to, new Node(to ) );
            }

            map.get(from).addConnection(map.get(to));
        }

        Stack<Node> stack = new Stack<>();
        LinkedList<String> list = new LinkedList<>();
        stack.add(map.get("JFK"));

        while( !stack.isEmpty() ){
            Node top = stack.peek();
            
            if( top.connections.isEmpty() ){
                list.addFirst(stack.pop().value);
            }else{
                stack.add(top.connections.poll());
            }
        }

        return list;
    }
}
