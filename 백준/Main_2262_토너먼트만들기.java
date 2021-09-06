package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_2262_토너먼트만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n];  // 선수 순서 정보
        int[] dp = new int[n]; // i번째 선수부터 n번째 순서의 선수까지 토너먼트가 완료되는데 소모되는 최소 비용
        int[] min_save = new int[n]; // i번째부터 n번째 순서의 선수까지 중에 최소 순위
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        if (n == 1) {
            System.out.println(0);
            return;
        }
        min_save[n - 1] = seq[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            min_save[i] = Math.min(seq[i], min_save[i + 1]);
        }

        dp[n - 2] = Math.abs(seq[n - 1] - seq[n - 2]);

        for (int i = n - 3; i >= 0; i--) {
            dp[i] = Math.min(dp[i + 1] + Math.abs(seq[i] - min_save[i + 1]),
                    dp[i + 2] + Math.abs(seq[i] - seq[i + 1]) + Math.abs((Math.min(seq[i], seq[i + 1]) - min_save[i + 2])));
        }


        for (int i : dp) {
            System.out.printf("%d ", i);
        }


    }
}
