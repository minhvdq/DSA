import java.util.*;

/**
 * Fun calculator class
 */
public class Calculator {
    Stack<Integer> vars = new Stack<>(); 
    Stack<Character> ops = new Stack<>();
    public static void main( String[] args) {
        Calculator calc = new Calculator();
        String inp = "1+2*9+3/3";
        calc.process(inp);
        if( !calc.ops.isEmpty() ){
            calc.popping();
        }
        System.out.println(calc.vars.pop());
    }

    private void process( String s ) {
        int leng = s.length();
        int lastInd = -1;
        for( int i = 0; i < leng; i ++ ){
            char c = s.charAt(i);
            if(c < 48 || c > 57 ) {
                char curOp = c;
                String curNumString = s.substring( lastInd + 1, i );
                System.out.println( curNumString);
                int curNum = stringToInt(curNumString);
                vars.push( curNum);
                if( curOp == '+' || curOp == '-' ) {
                    popping();
                }
                ops.push(curOp);
                lastInd = i;
            } 
        }

        int lastInt = stringToInt(s.substring(lastInd + 1, leng));
        vars.push(lastInt);
        System.out.println( "last num " + lastInt);
    }

    private void popping(){
        while(!ops.isEmpty() ) {
            char topOp = ops.pop();
            if(vars.isEmpty() ) {
                System.err.println("Stack empty with" + topOp);
                return;
            }
            int var1 = vars.pop();
            int var2 = vars.pop();

            int res = cal(var1, var2, topOp);
            vars.push(res);
        }
    }

    private int stringToInt( String s ) {
        int ans = 0;
        int pLevl = 0;
        int leng = s.length();
        for( int i = leng - 1; i >= 0; i -- ){
            int dig = s.charAt(i) - 48;
            ans += dig * Math.pow(10, pLevl);
            pLevl ++;
        }
        return ans;
    }

    private int cal( int v1, int v2, char op ) {
        int ans = 0;
        switch (op) {
            case '+':
                ans = v1 + v2;
                break;
            case '-':
                ans = v1 - v2;
                break;
            case '*':
                ans = v1 * v2;
                break;
            case '/':
                ans = v1 / v2;
                break;
            case '^':
                ans = (int) Math.pow(v1, v2);
                break;
            default:
                break;
        }
        return ans;
    }
}
