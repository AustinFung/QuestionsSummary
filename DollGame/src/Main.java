import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            // 特殊情况
            if(n==0 || m==0 || k<=0 || k>n) {
                System.out.println(0);
            } else {
                // 参与游戏的人抢到的礼物不能比左右两边的人多两个或以上
                // 即小招左右两边的人最少分别为小招的玩偶数减一，即max-1
                // 为了让小招玩偶数尽量多，离校招越远的人玩偶数越少，呈依次减一的情形
                for (int max = m; max >0; max--) {
                    int sum = max;
                    for (int i = 1; i <= k-1; i++) {
                        sum += (max-i)>0?(max-i):0;
                    }
                    for (int i = 1; i <= n-k; i++) {
                        sum += (max-i)>0?(max-i):0;
                    }
                    if(sum<=m) {
                        System.out.println(max);
                        break;
                    }
                }
            }
        }
    }
}