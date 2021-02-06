package DP백준;

import java.util.Scanner;

//14501 퇴사
/*
점화식을 사용해서 풀이
0<=k<N-1 -> dp[k]= Max(dp[k+1], P[k]+ dp[K+T[k]] (if k +T[k] <7)
         -> dp[k] = Max(dp[k+1], P[k] (if k +T[k] ==7)
         -> dp[k] = dp[k+1] (if k+T[k] > 7)

k=N-1 -> dp[k]=P[k] (if k +T[k] <=7) or dp[k]=0 if(k+T[k]>7 )
Bottom Up방식으로 해결결
 */
public class 퇴사 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] T = new int[N];
        int[] P = new int[N];
        int[] dp_array = new int[N];
        for (int i = 0; i < N; i++) {
            T[i] = scanner.nextInt();
            P[i] = scanner.nextInt();
        }

        for (int k = N - 1; k >= 0; k--) {
            if (k != N - 1) {
                if (k + T[k] > N) {
                    dp_array[k] = dp_array[k + 1];
                } else {
                    dp_array[k] = Math.max(dp_array[k + 1], k + T[k] == N ? P[k] : P[k] + dp_array[k + T[k]]);
                }
            } else {// k==N-1 IS TRUE
                if (k + T[k] > N) {
                    dp_array[k] = 0;
                } else {
                    dp_array[k] = P[k];
                }

            }
        }
        System.out.println(dp_array[0]);
    }

}
