import java.util.*;

public class optimizedcheating {
	static Queue<Long> q = new LinkedList<>();
	public static void main( String[] args ) {
		Scanner scanner = new Scanner(System.in);
		long n = scanner.nextLong();
		long m = scanner.nextLong();
		long k = scanner.nextLong();
		
		Set<Long> set = new HashSet<>();
		Map<Long, String> map= new HashMap<>();
		
		List<Character> ops = new ArrayList<>();
		List<Long> vals = new ArrayList<>();
		
		long curr = 0;
		for( int i = 1; i <= n; i ++ ) {
			long slot = scanner.nextLong();
			if( i != k ) {
				set.add(slot);
			}else {
				curr = slot;
			}
		}
		for( int i = 0; i < m; i ++ ) {
			String op = scanner.next();
			long val = scanner.nextLong();
			
			vals.add(val);
			ops.add(op.charAt(0));
		}
		
		q.add(curr);
		map.put(curr, "");
		while( !q.isEmpty() ) {
			long top = q.poll();
			if(!set.contains(top) ) {
				String str = map.get(top);
				System.out.println(str.length());
				for ( int i = 0; i < str.length(); i ++ ) {
					System.out.println(str.charAt(i));
				}
				return;
			}
			for( int i = 0; i  < ops.size(); i ++ ) {
				char op = ops.get(i);
				long val = vals.get(i);
				long result = calc(top, op, val);
				if( result < 0 ) {
					continue;
				}
				if( map.containsKey(result) ) {
					continue;
				}
				
				int ind = i + 1;
				String str = new String(map.get(top));
				str += ind;
				map.put(result, str);
				q.add(result);
			}
		}
		System.out.println(-1);
	}
	static long calc( long a, char op, long b ) {
		if( op == '+' ) {
			return a + b;
		}else if( op == '-' ) {
			return a - b;
		} else if( op == '*' ) {
			return a * b;
		} else if( op == '/' ) {
			long returnVal = a / b;
			return returnVal;
		}
		
		return a;
	}
}
