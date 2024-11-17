import java.util.*;

public class solution{
    
    public static void main( String[] args ){
        Scanner scanner = new Scanner(System.in);
        int numLine = scanner.nextInt();
        Stack<String> stack = new Stack<>();
        Stack<String> op = new Stack<>();
        for( int i = 0; i < numLine; i ++ ){
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            if( words[0].equals("cd")){
                if( words[1].equals("..")){
                    stack.pop();
                }else{
                    stack.push(words[1]);
                }
            }else if( words[0].equals("nano")){
                String fileName = words[1];
                String str = getDir( stack );
                op.push(str + fileName);
            }
        }

        boolean mark = op.isEmpty() ? true : false;
        
        while(!op.isEmpty()) {
            String str = op.pop();
            System.out.println("git " + "add " + str );
        }
        if( mark == false ){
            System.out.println("git commit");
            System.out.println("git push");
        }
    }
    
    private static String getDir( Stack<String> stack ){
        Iterator<String> iter = stack.iterator();
        String ans = "";
        while (iter.hasNext()){
            ans += iter.next() + "/";
        }
        if(!ans.endsWith("/") ){
            ans += "/";
        }
        return ans;
    }
}