import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 根据定义，证明一种判断position的性质的方法的正确性，只需证明三个命题： 
 * 1、这个判断将所有terminal position判为P-position；
 * 2、根据这个判断被判为N-position的局面一定可以移动到某个P-position；
 * 3、根据这个判断被判为P-position的局面无法移动到某个P-position。
 * 第一个命题显然，terminal position只有一个，就是全0，异或仍然是0。
 * 第二个命题，对于某个局面(a1,a2,...,an)，若a1^a2^...^an<>0，一定存在某个合法的移动，将ai改变成ai'后满足a1^a2^...^ai'^...^an=0。不妨设a1^a2^...^an=k，则一定存在某个ai，它的二进制表示在k的最高位上是1（否则k的最高位那个1是怎么得到的）。这时ai^k<ai一定成立。则我们可以将ai改变成ai'=ai^k，此时a1^a2^...^ai'^...^an=a1^a2^...^an^k=0。
 * 第三个命题，对于某个局面(a1,a2,...,an)，若a1^a2^...^an=0，一定不存在某个合法的移动，将ai改变成ai'后满足a1^a2^...^ai'^...^an=0。因为异或运算满足消去率，由a1^a2^...^an=a1^a2^...^ai'^...^an可以得到ai=ai'。所以将ai改变成ai'不是一个合法的移动。证毕。
 * 根据这个定理，我们可以在O(n)的时间内判断一个Nim的局面的性质，且如果它是N-position，也可以在O(n)的时间内找到所有的必胜策略。Nim问题就这样基本上完美的解决了。
 */
public class nim {
	static String[] room = {"A", "B", "C"};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(",");
		
		int[] a = new int[strs.length];
		int k = 0;
		for(int i = 0; i < strs.length; i++) {
			a[i] = Integer.parseInt(strs[i]);
			k ^= a[i];       					// ^是位异或运算符,按二进制位每个对应为做异或, 同为0，异为1
		}
		
		if(k == 0) {
            //P-position，先手必败
            System.out.println(1);
		} else {
			//N-position，先手必胜
			for(int i = 0; i < a.length; i++) {
				//寻找N-position移动到某个P-position的方法
				int num = k ^ a[i], tmpXor = num ^ num;
				if(a[i] - num >= 0 && tmpXor == 0) {
					System.out.println(room[i] + "," + (a[i] - num));
					break;
				}
			}
		}
	}
}
