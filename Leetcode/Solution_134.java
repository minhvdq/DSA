package Leetcode;
/*
 * Leetcode 134: Gas Station
 */
class Solution_134 {
    public static void main( String[] args ){
        System.err.println("asdad");
    }
    int[] pref;
    int leng;
    public int canCompleteCircuit(int[] gas, int[] cost) {
        leng = gas.length;
        pref = new int[leng];
        int total = 0;
        for( int i = 0; i < leng; i ++ ) {
            int gap = gas[i] - cost[i];
            pref[i] = gap;
            total += gap;
        }
        if( total < 0 ) {
            return -1;
        }

        int cur = 0;
        int mn = Integer.MAX_VALUE;
        int eIndex = 0;
        for( int i = 0; i < 2*leng; i ++ ){
            cur = Math.min( pref[i % leng], cur + pref[i % leng] );
            if( cur < mn ) {
                mn = cur;
                eIndex = i % leng;
            }
        }

        int chosen = (eIndex + 1) % leng;
        
        if( test(chosen) ) {
            return chosen;
        }

        return -1;
    }

    private boolean test( int index ) {
        int total = 0;
        for( int i = 0; i < leng; i ++ ){
            total += pref[(index + i) % leng];
            if( total < 0 ){
                return false;
            }
        }
        return true;
    }
}