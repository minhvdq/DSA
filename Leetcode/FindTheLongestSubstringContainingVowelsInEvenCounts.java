package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class FindTheLongestSubstringContainingVowelsInEvenCounts {
    // u a e o i
    int[] cur = {0, 0, 0, 0, 0};
    int[] mx;
    Map<Integer, Integer> map = new HashMap<>();
    public int findTheLongestSubstring(String s) {
        int leng = s.length();
        int mx = 0;
        map.put(0, -1);
        for( int i = 0; i < leng; i ++ ) {
            char c = s.charAt(i);
            if( c== 'u'){
                cur[0] = 1 - cur[0];
            } else if( c== 'a'){
                cur[1] = 1 - cur[1];
            } else if( c== 'e'){
                cur[2] = 1 - cur[2];
            } else if( c== 'o'){
                cur[3] = 1 - cur[3];
            } else if( c== 'i'){
                cur[4] = 1 - cur[4];
            }

            int num = calNum( cur );
            int dis = 0;
            if( map.get(num) == null ) {
                map.put(num, i);
            }else{
                int mnInd = map.get(num);
                dis = i - mnInd;
            }
            if( dis > mx ){
                mx = dis;
            }
        }

        return mx;
    }
    private int calNum( int[] arr ){
        int a = 0;
        int ans = 0;
        for( int i = 4; i >= 0; i -- ){
            if( arr[i] == 1 ){
                ans += Math.pow(2, a);
            }
            a ++;
        }
        return ans;
    }
}
