package 백준;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_1943_동전분배 {
    static int n;
    static int[] dp;
    static int[] visit;
    static HashSet<Integer> tmp = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int t = 0; t < 3; t++) {
            dp = new int[50001];
            visit = new int[50001];
            n = Integer.parseInt(br.readLine());
            dp[0] = 1;
            int total = 0;
            for (int c = 0; c < n; c++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int value = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                total += value * count;
                for (int v = 0; v <= 50000; v++) {
                    if (dp[v] == 1) {
                        for (int i = 1; i <= count; i++) {
                            if (v + (value * i) > 50000) break;
                            visit[v + (value * i)] = c + 1;
                        }
                    }
                }

                for (int v = 0; v <= 50000; v++) {
                    if(visit[v] == c + 1) {
                        dp[v] = 1;
                    }
                }
            }
            if ((total % 2 == 0) && (dp[total / 2] == 1)) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }
        bw.flush();
    }
}
