package Leetcode;
import java.util.*;

/**
 * Leetcode 632. Smallest Range Covering Elements from K Lists
 */
public class Solution_632 {
    int[] indices;
    int minIndex = 0;
    int[] range = {0, Integer.MAX_VALUE};
    public int[] smallestRange(List<List<Integer>> nums) {
        int leng = nums.size();
        indices = new int[leng];

        while(true){
            int inf = Integer.MAX_VALUE, sup = -Integer.MAX_VALUE, minInd = 0;
            for( int i = 0; i < leng; i ++ ) {
                int num = nums.get(i).get(indices[i]);

                if( num < inf ){
                    inf = num;
                    minInd = i;
                }

                if( num > sup ) {
                    sup = num;
                }
            }
            if( sup - inf < range[1] - range[0] ){
                range[0] = inf;
                range[1] = sup;
            }

            indices[minInd] ++;
            if( indices[minInd] == nums.get(minInd).size() ){
                break;
            }
        }
        return range;
    }
}