package Leetcode;

import java.util.*;

/**
 * 3043. Find the Length of the Longest Common Prefix
 */
public class Solution_3043 {
    Node root = new Node('*');
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        for( int num : arr1 ){
            String word = Integer.toString( num );
            insert(word);
        }

        int ans  = 0;
        for( int num : arr2 ) {
            String word = Integer.toString( num );
            int pref = commonPrefix( word );
            if( pref > ans ) {
                ans = pref;
            }
        }
        return ans;
    }

    private int commonPrefix( String word ){
        Node pointer = root;
        int cnt = 0;
        for( int i = 0; i < word.length(); i ++ ) {
            char c = word.charAt(i);
            Node n = pointer.neighbors.get(c);
            if( n == null ){
                return cnt;
            }

            pointer = n;
            cnt ++;
        }
        
        return cnt;
    }
    
    private void insert ( String word ) {
        Node pointer = root;
        for( int i = 0; i < word.length(); i ++ ) {
            char c = word.charAt(i );
            pointer.neighbors.putIfAbsent(c, new Node(c));
            pointer = pointer.neighbors.get(c);
        }
        pointer.isWord = true;
    }

    class Node{
        char c;
        Map<Character, Node> neighbors;
        boolean isWord = false;
        public Node( char c ) {
            this.c = c;
            neighbors = new HashMap<>();
        }
    }
}
