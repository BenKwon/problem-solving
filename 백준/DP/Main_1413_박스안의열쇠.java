package 백준.DP;

import java.util.*;
import java.io.*;

public class Main_1413_박스안의열쇠 {
    static long[] mul(long[] a, long[] b) {
        long nOn = a[0] * b[0];
        long nUnder = a[1] * b[1];
        return new long[]{nOn, nUnder};
    }

    static long[] sum(long[] a, long[] b) {
        if(a[0] == 0 || b[0] == 0) return new long[]{a[0] + b[0], a[1] + b[1]};
        if (a[1] == b[1]) return new long[]{a[0] + b[0], a[1]};
        long on, under;
        if (a[1] % b[1] == 0) {
            on = a[0] + (b[0] * (a[1] / b[1]));
            under = a[1];
        } else if (b[1] % a[1] == 0) {
            on = b[0] + (a[0] * (b[1] / a[1]));
            under = b[1];
        } else {
            on = (a[0] * b[1]) + (b[0] * a[1]);
            under = a[1] * b[1];
        }
        return new long[]{on, under};
    }
    static long GCD(long a, long b)
    {
        if(b==0)return a;
        else return GCD(b,a%b);
    }
    static long[] minimize(long[] a) {
        long result = GCD(a[0],a[1]);
        return new long[]{a[0]/result, a[1]/result};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[][][] dp = new long[n + 1][m + 1][2];
        for (int i = 0; i <= m; i++) {
            dp[0][i][0] = 1;
            dp[0][i][1] = 1;
            dp[1][i][0] = 1;
            dp[1][i][1] = 1;
        }
        dp[1][0][0] = 0;
        dp[1][0][1] = 0;

        Arrays.fill(dp[0][0], 1);
//        int[] a ={0,0};
//        int[] b ={3,12};
//        int[] c = sum(a, b);
//        System.out.printf("%d / %d\n",c[0],c[1]);
        for (int i = 2; i <= n; i++) {
//            System.out.println("----------------------------");
//            System.out.println("i = " + i);
            for (int j = 1; j <= m; j++) {
//                System.out.println("!!-----> j = " + j);
                int k = 0;
                long[] next = new long[2];
                while (k < 2) {
                    int mul = k == 0 ? i - 1 : 1;
                    long nextOn = dp[i - 1][j - k][0] * mul;
                    long nextUnder = dp[i - 1][j-k][1];
//                    System.out.printf("pnext : %d/%d\n",nextOn,nextUnder);
                    if (k == 0) {
                        next[0] = nextOn;
                        next[1] = nextUnder;
                    } else {
                        next = (sum(next, new long[]{nextOn, nextUnder}));
                    }
                    k++;
                }
//                System.out.printf("[%d, %d] : %d / %d\n",i,j,next[0],next[1]);
                next = (mul(next, new long[]{1, i}));
                dp[i][j][0] = next[0];
                dp[i][j][1]= next[1];
            }
        }
        long[] sol = minimize(dp[n][m]);
        System.out.printf("%d/%d", sol[0], sol[1]);
    }
}
