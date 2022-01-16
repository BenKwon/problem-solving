package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1303_전쟁전투 {
    static int n,m;
    static char[][] graph;
    static int[][] visit;
    static int[] rowMove = {-1, 1, 0, 0};
    static int[] colMove = {0, 0, -1, 1};
    public static int dfs(int row, int col, char type){
        if(visit[row][col] == 1) return 0;
        int count = 1;
        visit[row][col] = 1;
        for (int i = 0; i < 4; i++) {
            int nrow = row + rowMove[i];
            int ncol = col + colMove[i];
            if(!validation(nrow, ncol)) continue;
            if(graph[nrow][ncol] != type) continue;
            count += dfs(nrow, ncol, type);
        }
        return count;

    }
    static boolean validation(int row ,int col){
        if(row < 0 || row >= n || col < 0 || col >= m) return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graph = new char[n][m];
        visit = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = str.charAt(j);
            }
        }
        int sumTypeW = 0;
        int sumTypeB = 0;
        for(int i = 0; i < n; i++){
            int count = 0;
            for(int j = 0 ; j < m ; j++){
                count = dfs(i, j, graph[i][j]);
                if(graph[i][j] == 'W'){
                    sumTypeW += count * count;
                }else{
                    sumTypeB += count * count;
                }
            }
        }
        System.out.printf("%d %d",sumTypeW,sumTypeB);

    }
}
