package Leetcode;
import java.util.*;
/**
 * Leetcode 241: Different Ways to Add Parentheses
 */
public class Solution_241 {
    List<Integer>[][] dp;
    List<Integer> nums = new ArrayList<>();
    List<Character> ops = new ArrayList<>();
    public List<Integer> diffWaysToCompute(String expression) {
        // Generate nums and ops
        String s = "";
        for( int i = 0; i < expression.length(); i ++) {
            char c = expression.charAt(i);
            if( c == '+' || c == '-' || c == '*' || c == '/' ){
                int num = Integer.parseInt(s);
                s = "";
                nums.add( num);
                ops.add(c);
            }else{
                s += c;
            }
        }
        int num = Integer.parseInt(s);
        nums.add( num);

        int numSize = nums.size();
        dp = new ArrayList[numSize + 10][numSize + 10];

        // return getDP( 0, numSize - 1 );
        return new ArrayList<>();
    }

    private List<Integer> getDP( int i, int j ){
        if( dp[i][j] != null ) {
            return dp[i][j];
        }
        List<Integer> ans = new ArrayList<>();
        if(i == j) {
            ans.add( nums.get(i) );
            dp[i][j] = ans;
            return ans;
        }
        if( i == j - 1 ) {
            char op = ops.get(i);
            int result = calculate( nums.get(i), op, nums.get(j));
            ans.add( result );
            dp[i][j] = ans;
            return ans;
        }

        for( int k = i; k < j; k ++ ) {
            char op = ops.get(k );
            List<Integer> left = getDP( i, k );
            List<Integer> right = getDP( k + 1, j );
            for( int l : left ) {
                for( int r : right ){
                    int result = calculate( l, op, r );
                    ans.add(result);
                }
            }
        }

        dp[i][j] = ans;
        return ans;
    }

    private int calculate( int a, char op, int b ) {
        if( op == '+' ){
            return a + b;
        }
        if( op == '-' ){
            return a - b;
        }
        if( op == '*' ){
            return a * b;
        }

        return a / b;
    }
}