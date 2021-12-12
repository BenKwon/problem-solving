package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1106_호텔 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] dp = new int[2001];
        for (int i = 1; i <= 2000; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            int[] copy = dp.clone();
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            for (int j = 0; j <= 2000; j++) {
                if (dp[j] != Integer.MAX_VALUE) {
                    int cur = dp[j];
                    for (int m = 1; j + (m * value) < 2001; m++) {
                        if (dp[j + (m * value)] > (cost * m) + cur) {
                            if (copy[j + (m * value)] > (cost * m) + cur){
                                copy[j + (m * value)] = (cost * m) + cur;
                            }
                        }
                    }
                }
            }
            dp = copy.clone();
        }
        int min = Integer.MAX_VALUE;
        for (int i = c; i <= 2000; i++) {
            min = Math.min(min, dp[i]);
        }
        System.out.println(min);
    }
}
