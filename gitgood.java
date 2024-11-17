import java.util.*;

public class gitgood{
    
    public static void main( String[] args ){
        Scanner scanner = new Scanner(System.in);
        int numLine = scanner.nextInt();
        Stack<String> stack = new Stack<>();
        PriorityQueue<String> op = new PriorityQueue<>();
        Set<String> set = new HashSet<>();

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
                String str = getDir( stack ) + fileName;
                if( !set.contains(str)){
                    op.add(str);
                    set.add(str );
                }
                
            }
        }


        boolean mark = op.isEmpty() ? true : false;
        while(!op.isEmpty()) {
            String str = op.poll();
            System.out.println("git " + "add " + str );
        }
        if( !mark ){
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
        return ans;
    }
}