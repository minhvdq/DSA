package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 2707. Extra Characters in a String
 * @param word
 */
public class Solution_2707 {
    TrieNode root = new TrieNode('*');
    int[][] dp;
    int leng;
    String str;
    public int minExtraChar(String s, String[] dictionary) {
        leng = s.length();
        str = s;
        dp = new int[leng + 1][leng + 1];
        for( String word : dictionary ) {
            insert( word );
        }

        for( int i = 0; i <= leng; i ++ ) {
            for( int j = 0; j <= leng; j ++ ) {
                dp[i][j] = -1;
            }
        }
        return getDP( 0, leng   );

    }

    private int getDP( int i, int j ) {
        if( dp[i][j] != -1 ) {
            return dp[i][j];
        }
        
        String sb = str.substring( i, j );
        if( search(sb) ) {
            dp[i][j] = 0;
            return 0;
        }
        if(sb.length() == 1 ) {
            dp[i][j] = 1;
            return 1;
        }

        int mn = leng;
        for( int k = i + 1; k < j; k ++ ) {
            int a = getDP(i, k);
            int b = getDP(k, j);
            if( a + b < mn ) {
                mn = a + b;
            }
        }
        dp[i][j] = mn;
        return mn;
    }

    private boolean search( String word ){
        TrieNode pointer = root;
        for( int i = 0; i < word.length(); i ++ ) {
            char c = word.charAt(i);
            pointer = pointer.neighbors.get(c);
            if(pointer == null ) {
                return false;
            }
        }
        return pointer.isWord ? true : false;
    }

    private void insert( String word ) {
        TrieNode pointer = root;
        for( int i = 0; i < word.length(); i ++ ) {
            char c = word.charAt(i);
            pointer.neighbors.putIfAbsent( c, new TrieNode( c ) );
            pointer = pointer.neighbors.get(c);
        }
        pointer.isWord = true;
        
    }

    class TrieNode{
        char c;
        Map<Character, TrieNode> neighbors;
        boolean isWord;
        public TrieNode( char c ) {
            this.c = c;
            neighbors = new HashMap<>();
        }

        public void connect( TrieNode node ) {
            neighbors.put( node.c, node);
        }
    }
} {
    
}
