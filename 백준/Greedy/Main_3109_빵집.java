package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_빵집 {
    static int r, c;
    static int[][] visit;
    static char[][] board;
    static boolean find;
    static int[] dx = {-1,0,1};
    static int[] dy = {1,1,1};
    static int totalPipe = 0;
    static void findWay(int row){
        dfs(row, 0);
    }

    static void dfs(int row, int col) {
        if(col == c - 1){
            find = true;

            return;
        }
        if(find) return;
        visit[row][col] = 1;
        for (int i = 0; i < 3; i++) {
            int nrow = row + dx[i];
            int ncol = col + dy[i];
            if(!inRange(nrow,ncol) || board[nrow][ncol] == 'x') continue;
            if(visit[nrow][ncol] != 0) continue;
            dfs(nrow, ncol);
        }
    }

    static boolean inRange(int row, int col) {
        if(row < 0 || row >= r || col < 0 ||col>= c)return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         r = Integer.parseInt(st.nextToken());
         c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        visit = new int[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        for (int i = 0; i < r; i++) {
            find = false;
            findWay(i);
            if(find) totalPipe++;
        }

        System.out.println(totalPipe);
    }
}
