package 백준.DP;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_가장긴증가하는부분수열4_14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[n];
        int[] dp = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 1;
        map.put(0, 0);
        int max_dp_value = Integer.MIN_VALUE;
        int max_dp_index = 0;
        if(n==1){
            System.out.println(1);
            System.out.println(seq[0]);
            return;
        }
        for (int i = 1; i < n; i++) {
            int max = Integer.MIN_VALUE;
            int max_index = -1;
            for (int k = i - 1; k >= 0; k--) {
                if (seq[k] < seq[i]) {
                    if (dp[k] > max) {
                        max = dp[k];
                        max_index = k;
                    }
                }
            }
            if (max_index < 0) {
                dp[i] = 1;
                map.put(i, i);
            } else {
                dp[i] = max + 1;
                map.put(i, max_index);
            }
            if (dp[i] > max_dp_value) {
                max_dp_value = dp[i];
                max_dp_index = i;
            }
        }
        
        int tmp = max_dp_index;
        ArrayList<Integer> sol = new ArrayList<>();

        while (true) {
            if (tmp == map.get(tmp)) {
                sol.add(seq[tmp]);
                break;
            } else {
                sol.add(seq[tmp]);
                tmp = map.get(tmp);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = sol.size() - 1; i >= 0; i--) {
            if (i != 0)
                sb.append(sol.get(i) + " ");
            else
                sb.append(sol.get(i));
        }
        System.out.println(max_dp_value);
        System.out.println(sb);
    }
}
