package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 점프_1890_Main {
    public static int[][] graph;
    public static long[][] dp;

    public static int n;
    public static int sol = 0;
    public static long find(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= n) return 0;
        if(x== n-1 && y == n-1 ) {
            sol++;
            return 1;
        }
        int value = graph[x][y];
        if(dp[x][y] < 0 )return 0;
        if(dp[x][y] > 0 ){
            sol++;
            return dp[x][y];
        }
        if(value == 0) {
            dp[x][y] = -1;
            return 0;
        }
        long find1 = find(x + value, y);
        long find2 = find(x, y + value);
        dp[x][y] = find1 + find2;
        return dp[x][y];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        dp = new long[n][n];
        for(int i = 0; i < n ; i++){
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            for(int j = 0 ; j < n ; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find(0, 0);
//        for (long[] ints : dp) {
//            System.out.println("===============");
//            for (long anInt : ints) {
//                System.out.println("anInt = " + anInt);
//            }
//
//        }
        System.out.println(dp[0][0]);
    }
}
