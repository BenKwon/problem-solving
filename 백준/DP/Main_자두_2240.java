package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_자두_2240 { //여자는 항상 1에 서있는 채로 시작 한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[] pos = new int[t + 1];
        int[][][] dp = new int[t + 1][w + 1][2];  //{먹은 자두 갯수, 현재 여자의 위치) -> dp[t][w] 시간 t초때 w만큼이동해서 먹은 자두갯수와 현재 여자의 위치 이동가능 칸은 2개 뿐이므로 t초때 w번이동햇으면 위치는 항상 똑같다.
        for (int i = 1; i <= t; i++) {
            pos[i] = Integer.parseInt(br.readLine());
        }
        if(pos[1] == 1){
            dp[1][0] = new int[]{1, 1};
            dp[1][1] = new int[]{0, 2};
        }else{
            dp[1][0] = new int[]{0, 1};
            dp[1][1] = new int[]{1, 2};
        }

        int sol = Integer.MIN_VALUE;
        for (int i = 2; i <= t; i++) {
            for (int j = 0; j <= Math.min(w,t); j++) {
//                dp[i][j] = dp[i - 1][j], dp[i - 1][j - 1] 이두개를 보면 구할 수있다.
                int result1 = 0;
                int result2 = 0;
                if(j==0){
                    if(dp[i - 1][j][1] == pos[i]){
                        result1 = dp[i - 1][j][0] + 1;
                        dp[i][j] = new int[]{result1, dp[i - 1][j][1]};
                    }else{
                        dp[i][j] = new int[]{dp[i - 1][j][0], dp[i - 1][j][1]};
                    }
//                    System.out.printf("dp[%d][%d] : [%d, %d] \n", i, j , dp[i][j][0], dp[i][j][1]);
                    sol = Math.max(sol, dp[i][j][0]);
                    continue;
                }

                //dp[i-1][j] 탐색
                int[] ints = dp[i - 1][j];
                if(dp[i - 1][j][1] == pos[i]){
                    result1 = dp[i - 1][j][0] + 1;
                }else{
                    result1 = dp[i - 1][j][0];
                }
                //dp[i-1][j-1] 탐색
                ints = dp[i - 1][j-1];
                if(find_cur_pos(dp[i - 1][j-1][1]) == pos[i]){
                    result2 = dp[i - 1][j-1][0] + 1;
                }else{
                    result2 = dp[i - 1][j-1][0];
                }
                int max = Math.max(result1, result2);
                dp[i][j] = new int[]{max, find_cur_pos(dp[i - 1][j-1][1])};
//                System.out.printf("dp[%d][%d] : [%d, %d] \n", i, j , dp[i][j][0], dp[i][j][1]);
                sol = Math.max(sol, dp[i][j][0]);
            }
        }
//        for (int i = 0; i <= w; i++) {
//            System.out.printf("%d ", dp[t][i][0]);
//        }
        if(sol < 0){
            System.out.println(1);
            return;
        }
        System.out.printf("%d",sol);
    }
    static int find_cur_pos(int ex_pos){
        if(ex_pos == 1) return 2;
        return 1;
    }
}
