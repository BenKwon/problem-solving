package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7570_줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[n + 1];
        int[] indexStore = new int[1000001];
        for (int i = 0; i < n; i++) {
            int next = Integer.parseInt(st.nextToken());
            seq[i] = next;
            indexStore[next]= i;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            int next = seq[i];
            if(next == 1) continue;
            int index = indexStore[next - 1];
            if (index < i) {
                dp[next] = dp[next - 1] + 1;
                max = Math.max(max, dp[next]);
            }
        }
        System.out.println(n - max);

    }
}
