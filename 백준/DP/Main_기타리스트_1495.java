package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Input
 * N S M (곡 갯수 : N 시작볼륨 : S 최대 볼륨 : M)
 * 3 5 10
 * 5 3 7
 * Output
 * 10
 *
 */
public class Main_기타리스트_1495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] music = new int[n + 1];
        HashSet<Integer>[] dp = new HashSet[n +1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            music[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = new HashSet<>();
        dp[0].add(s);
        for (int i = 1; i <= n; i++) {
            Iterator<Integer> iterator = dp[i - 1].iterator();
            dp[i] = new HashSet<>();
            while (iterator.hasNext()) {
                int next = iterator.next();
                if(music[i] + next <= m){
                    dp[i].add(music[i] + next);
                }
                if(next - music[i] >= 0 && next - music[i]<= m){
                    dp[i].add(next - music[i]);
                }
            }
        }
        Iterator<Integer> iterator = dp[n].iterator();
        if(dp[n].isEmpty()){
            System.out.println(-1);
            return;
        }
        int max = Integer.MIN_VALUE;
        while (iterator.hasNext()) {
            max = Math.max(max, iterator.next());
        }
        System.out.println(max);
    }
}
