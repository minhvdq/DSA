package Leetcode;
import java.util.*;

/**
 * 2416. Sum of Prefix Scores of Strings
 */
class Solution_2416 {
    Node root = new Node( '*' );
    String[] words;
    int leng;
    int[] score;
    public int[] sumPrefixScores(String[] words) {
        leng = words.length;
        score = new int[leng];
        this.words = words;

        for( int i = 0; i < leng; i ++ ) {
            insert(words[i]);
        }

        for(int i = 0; i < leng; i ++ ) {
            String word = words[i];
            Node pointer = root;
            for( int j = 0; j < word.length(); j ++ ){
                char c = word.charAt(j);
                pointer = pointer.neighbors.get(c);
                score[i] += pointer.pref;
            }
        }

        return score;
    }

    void insert(String word) {
        Node pointer = root;
        for( int i = 0; i < word.length(); i ++ ) {
            char c = word.charAt(i);
            Node next = pointer.neighbors.get(c);
            if( next == null ) {
                next = new Node(c);
                pointer.neighbors.put(c, next);
                next.pref = 1;
            }else{
                next.pref ++;
            }

            pointer = next;
        }
    }

    class Node{ 
        char c;
        Map<Character, Node> neighbors;
        int pref = 0;

        public Node( char c ) {
            this.c = c;
            neighbors = new HashMap<>();
        }
    }
}