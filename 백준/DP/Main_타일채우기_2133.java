package 백준.DP;

import java.util.Scanner;

public class Main_타일채우기_2133 {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        int[][] dp = new int[4][n + 1];
        if(n%2 == 1){
            System.out.println(0);
            return;
        }
        dp[3][2] = 3;
        if(n==2){
            System.out.println(dp[3][2]);
            return;
        }
        dp[1][2] = 1;
        dp[2][1] = 1;
        dp[2][2] = 2;
        dp[2][3] = 3;
        dp[3][4] = 11;
        dp[3][0] = 1;
        if(n==4){
            System.out.println(dp[3][4]);
            return;
        }
//        dp[3][1] = 0;

        for(int i = 5 ; i <= n ; i++){
            dp[3][i] = dp[3][2] * dp[3][i - 2] ;
            if(i % 2 == 0 ){
//                dp[3][i] += (i/2 - 2)*3*2*dp[3][i-6];
                for(int d = 4; i-2-d >= 0 ; d += 2){
                    dp[3][i] += dp[3][i-d]  * 2;
                }
                dp[3][i] += 2;

            }
        }

        System.out.println(dp[3][n]);

    }

//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int n = scan.nextInt();
//        int [] dp = new int[n+1];
//
//        dp[0] = 1;
//        for(int i=2; i<=n; i++) {
//            dp[i] = 3*dp[i-2];
//            for(int j=i-4; j>=0; j-=2)
//                dp[i]+=2*dp[j];
//        }
//        System.out.println(dp[n]);
//    }


}
