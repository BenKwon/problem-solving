package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main_1학년_5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashSet<Long>[] dp = new HashSet[n];
        HashSet<Integer>[] dp_reverse = new HashSet[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] seq = new long[n];
        long[][] tmp = new long[n][21];
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = new HashSet<>();
        dp[0].add(seq[0]);
        tmp[0][(int) seq[0]] = 1;
        // + - 음수가 나오면 안되고 20이 넘어도 안된다.
        for (int i = 1; i < n; i++) {
            Iterator<Long> iterator = dp[i - 1].iterator();
            dp[i] = new HashSet<>();
            while (iterator.hasNext()) {
                long next = iterator.next();
                if (next + seq[i] >= 0 && next + seq[i] <= 20) {
                    dp[i].add(next + seq[i]);
                    tmp[i][(int) (next + seq[i])] += tmp[i - 1][(int) next];
                }
                if (next - seq[i] >= 0 && next - seq[i] <= 20) {
                    dp[i].add(next - seq[i]);
                    tmp[i][(int) (next - seq[i])] += tmp[i - 1][(int) next];
                }
            }
        }
        System.out.println(tmp[n - 2][(int) seq[n - 1]]);
    }
}
