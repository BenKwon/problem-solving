package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_이동하기_11048 {
    public static int[][] graph;
    public static int[] row_move = new int[]{1,0,1};
    public static int[] col_move = new int[]{0,1,1};
    public static int[][] visit; //visit 표시용
    public static int n, m;
    public static int dfs(int x, int y){
        if(visit[x][y] == 1) return graph[x][y];
        visit[x][y] = 1;
        int max = 0;
        for(int i = 0 ; i < 3;i++){
            int row = x + row_move[i];
            int col = y + col_move[i];
            if(row >= 1 && row <= n && col >= 1 && col <= m)max =Math.max(max, dfs(row,col));
        }
        graph[x][y] += max;
        return graph[x][y];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n + 1][m + 1];
        visit = new int[n + 1][m + 1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <=m ;j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        graph[n][m - 1] += graph[n][m];
        visit[n][m-1] = 1;
        graph[n-1][m] += graph[n][m];
        visit[n - 1][m] = 1;
        visit[n][m] = 1;

        System.out.println(dfs(1,1));

    }
}
