package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_연속합2_13398 {
    public static int n;
    public static int[] dp, seq;
    public static ArrayList<Integer> store_max, store_zero;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        seq = new int[n + 2];
        dp = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        if(seq[1] > 0){
            dp[1] = seq[0];
        }
        int max = Integer.MIN_VALUE;
        int tmp_max = Integer.MIN_VALUE;
        store_max = new ArrayList<>();
        store_zero = new ArrayList<>();
        for(int j = 1 ; j <= n ; j++){
            int result = seq[j] + dp[j - 1];
            if(result < 0 ) {
                if(result > max) {
                    max = result;
                }
                if(result > tmp_max){
                    tmp_max = result;
                }
                dp[j] = 0;
                store_zero.add(j);
                store_max.add(tmp_max);
                tmp_max = Integer.MIN_VALUE;
            }

            else{
                dp[j] = result;
                if(result > tmp_max){
                    tmp_max = result;
                }
                if(result > max) {
                    max = result;
                }
            }
        }
        store_zero.add(n + 1);
        store_max.add(tmp_max);

        for (int i = 0; i < store_zero.size(); i++) {
            System.out.printf("%d %d\n",store_zero.get(i),store_max.get(i));
        }
        if(max <= 0){
            System.out.println(max);
            return;
        }
//        System.out.println();
        System.out.println(sol(0));
//        System.out.println(max);

    }
    public static int sol(int start){
        int count = 0;
        if(start >= store_zero.size()) return 0;
        count = Math.max(store_max.get(start), dp[store_zero.get(start) - 1] + sol(start + 1));
        return count;
    }
}
