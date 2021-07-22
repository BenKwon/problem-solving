package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  %
 *  결국 구글링을 하였지만 조금만 더 생각 하면 풀수 있었을 것 같다.
 *  중요한 부분은 dp[i][j] = dp[i][k] + dp[k+1][j] + sum(i to j)(i <= k < j )
 *
 */
public class Main_파일합치기_f_11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            int k = Integer.parseInt(br.readLine());
            int[][] dp = new int[k][k];
            int[][] tmp = new int[k][k];

            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Integer> file = new ArrayList<>();
            int sum = 0;
            for(int i = 0 ; i < k ; i++){
                file.add(Integer.parseInt(st.nextToken()));
                tmp[i][i] = file.get(i);
                sum += file.get(i);
            }

            for(int i = 0; i < k - 1  ; i++){
                dp[i][i + 1] = tmp[i][i] + tmp[i + 1][i + 1];
            }

            for (int i = 0; i < k - 2; i++) {
                for(int  j = 0 ; j < k - 2 - i ; j++){
//                    dp[j][j + 2 +i]
                    int min = Integer.MAX_VALUE;
                    sum = 0;
                    for(int l = j ; l <= j+2+i;l++){
                        sum += file.get(l);
                    }
                    for(int r = j; r < j+2+i; r++){
                        min = Math.min(dp[j][r] + dp[r + 1][j + 2 + i], min);
                    }
                    dp[j][j + 2 + i] += sum + min;
                }
            }

            System.out.println(dp[0][k-1]);

        }
    }
}
