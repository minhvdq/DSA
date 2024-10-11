package Leetcode;

import java.util.*;

/**
 * Leetcode 1942. The Number of the Smallest Unoccupied Chair
 */
public class Solution_1942 {
    class Pair{
        public int val1;
        public int val2;
        public Pair(int val1, int val2){
            this.val1 = val1;
            this.val2 = val2;
        }
        
    }
    class PairComparator implements Comparator<Pair> {
        public int compare( Pair p1, Pair p2 ){
            return p1.val1 - p2.val1;
        }
    }
    HashMap<Pair, Integer> map = new HashMap<>();
    PairComparator comp = new PairComparator();
    PriorityQueue<Pair> timeHeap = new PriorityQueue<>(comp);
    PriorityQueue<Integer> avaChairs = new PriorityQueue<>();
    public int smallestChair(int[][] times, int targetFriend) {
        int leng = times.length;
        Pair[] friends = new Pair[leng];
        for( int i = 0; i < leng; i ++ ){
            friends[i] = new Pair(times[i][0], times[i][1]);
            map.put(friends[i], i);
            avaChairs.add(i);
        }

        Arrays.sort(friends, comp);

        for( int i = 0; i < leng; i ++ ) {
            Pair friend = friends[i];
            int a = map.get(friend);
            int join = friend.val1;
            int leave = friend.val2;
            while( !timeHeap.isEmpty() ){
                Pair pair = timeHeap.peek();
                if( pair.val1 > join ){
                    break;
                }

                pair = timeHeap.poll();
                avaChairs.add(pair.val2);
            }

            int topChair = avaChairs.poll();
            if( a == targetFriend ) {
                return topChair;
            }
            Pair p = new Pair( leave, topChair );
            timeHeap.add(p);
        }
        return 0;
    }
}