package 백준.DFSxBFS;

import java.util.*;
import java.io.*;

public class Main_6087_레이저통신 {
    static int w, h;
    static char[][] graph;
    static int startRow, startCol, endRow, endCol;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    static int[][][] visit;
    static int solution = Integer.MAX_VALUE;
    static class pos{
        int row;
        int col;
        int from;

        public pos(int row, int col, int from) {
            this.row = row;
            this.col = col;
            this.from = from;
        }
    }

    static boolean inRange(int row, int col) {
        if(row <0 || row>= h || col < 0 || col >= w) return false;
        return true;
    }

    static void dfs(int row, int col ,int turnNum, int from){
        if(row == endRow && col == endCol){
//            System.out.println("turnNum = " + turnNum);
            solution = Math.min(solution, turnNum);
            return;
        }
//        System.out.printf("row : %d, col : %d , turnnum: %d, from :%d\n",row,col,turnNum,from);
        if(visit[row][col][from] <= turnNum) return;
        visit[row][col][from] = turnNum;
        if(from == 1 || from == 0){ //위나 아래에서 현재 노드를 왔을 경우
            for (int i = 0; i < 4; i++) {
                int nrow = row + dRow[i];
                int ncol = col + dCol[i];
                if(!inRange(nrow,ncol) || graph[nrow][ncol] =='*') continue;
                if(i == 0){
                    dfs(nrow, ncol, turnNum, 1);
                }else if(i == 1){
                    dfs(nrow, ncol, turnNum, 0);
                }else if(i == 2){
                    dfs(nrow, ncol, turnNum + 1, 3);
                }else{
                    dfs(nrow, ncol, turnNum + 1, 2);
                }
            }
        }else{ //왼쪽이나 오른쪽에서 현재 노드를 왔을 경우
            for (int i = 0; i < 4; i++) {
                int nrow = row + dRow[i];
                int ncol = col + dCol[i];
                if(!inRange(nrow,ncol) || graph[nrow][ncol] =='*') continue;
                if(i == 0){
                    dfs(nrow, ncol, turnNum + 1, 1);
                }else if(i == 1){
                    dfs(nrow, ncol, turnNum + 1, 0);
                }else if(i == 2){
                    dfs(nrow, ncol, turnNum, 3);
                }else{
                    dfs(nrow, ncol, turnNum, 2);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        graph = new char[h][w];
        visit = new int[h][w][4];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Arrays.fill(visit[i][j], Integer.MAX_VALUE);
            }
        }
        int find = 0;
        for (int i = 0; i < h; i++) {
            String str = br.readLine();
            for (int j = 0; j < w; j++) {
                graph[i][j] = str.charAt(j);
                if (graph[i][j] == 'C') {
                    if (find == 0) {
                        startRow = i;
                        startCol = j;
                        find++;
                    } else {
                        endRow = i;
                        endCol = j;
                    }
                }
            }
        }
        Queue<pos> q = new LinkedList<>();
        visit[startRow][startCol][0] = -1;
        visit[startRow][startCol][1] = -1;
        visit[startRow][startCol][2] = -1;
        visit[startRow][startCol][3] = -1;

        for (int i = 0; i < 4; i++) {
            int nrow = startRow + dRow[i];
            int ncol = startCol + dCol[i];
            if(!inRange(nrow,ncol) || graph[nrow][ncol] =='*') continue;
            if(i == 0){
                dfs(nrow, ncol, 0, 1);
            }else if(i == 1){
                dfs(nrow, ncol, 0, 0);
            }else if(i == 2){
                dfs(nrow, ncol, 0, 3);
            }else{
                dfs(nrow, ncol, 0, 2);
            }
        }

        System.out.println(solution);
    }
}
