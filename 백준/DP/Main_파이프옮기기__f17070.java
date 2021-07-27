package 백준.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * %
 */
public class Main_파이프옮기기_f_17070 {
    public static int[][][] dp;
    public static int[][] visit;
    public static int[][] graph;

    public static int n;
    public static int solution = 0;
    public static int[] row_move = new int[]{0, 1, 1};
    public static int[] col_move = new int[]{1, 0, 1};

    public static boolean valid_coordinate(int row, int col){
        if(row <= 0 || col <= 0 || row >n || col >n) return false;
        return true;
    }
    public static void dfs(int row, int col, int direction){ //direction -> 0: 가로 인상태 1: 세로인 상태 2: 대각선인 상태
        if(row == n && col == n  && graph[n][n] != 1){
            solution++;
        }

        for(int i = 0; i < 3; i++){
            if(i == 1 && direction == 0) continue;
            if(i == 0 && direction == 1) continue;
//            if(i == 0 && graph[row][col+1] == 1) continue;
//            if(i == 1 && graph[row + 1][col] == 1) continue;
            if(i == 2){
                if(graph[row+1][col] == 1 || graph[row][col+1] ==1  || graph[row+1][col+1] == 1) continue;
            }
            int nrow = row + row_move[i];
            int ncol = col + col_move[i];
            if(!valid_coordinate(nrow,ncol) || graph[nrow][ncol] == 1) continue;
            dfs(nrow, ncol, i);

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n + 2][n + 2];
        dp = new int[n + 2][n + 2][3]; //0 : 가로로 가는것 1 : 세로로 가는것 2 : 대각선으로 가는 것
        visit = new int[n + 2][n + 2]; //0 : 가로로 가는것 1 : 세로로 가는것 2 : 대각선으로 가는 것

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(1, 2, 0);
        System.out.println(solution);
    }



}
