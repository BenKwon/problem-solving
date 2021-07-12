package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연속합_1912_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int[] seq = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        if(seq[0] > 0){
            dp[0] = seq[0];
        }
        int max = seq[0];
        for(int j = 1 ; j < n ; j++){
            int result = seq[j] + dp[j - 1];
            if(result < 0 ) {
                if(result > max) max = result;
                dp[j] = 0;
            }

            else{
                dp[j] = result;
                if(result > max) max = result;
            }
        }

        System.out.println(max);

    }
}
