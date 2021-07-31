package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_공통부분문자열_5582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = " " + br.readLine(); // " abcd"
        String s2 = " " + br.readLine(); // " cdfe"
//        char[] a1 = new char[4000];
//        Arrays.fill(a1,'A');
//        String s1 = " " +new String(a1);
//        String s2 = " " +new String(a1);
        int s1_len = s1.length();
        int s2_len = s2.length();
        int[][] dp = new int[s2_len][s1_len];
//        System.out.println(s2_len);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < s2_len; i++) {
            boolean tmp = true;
            for (int j = 1; j < s1_len; j++) {
                if (s2.charAt(i) == s1.charAt(j)) {
                    dp[i][j] += dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
//        for (int i = 1; i < s2_len; i++) {
//            System.out.println();
//            for (int j = 1; j < s1_len; j++) {
//                System.out.printf("%d ", dp[i][j]);
//            }
//        }
        System.out.printf("%d", max);
    }

}
