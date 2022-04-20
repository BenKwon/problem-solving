package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2146_다리만들기 {
    static int n;
    static int[][] origin;
    static int[][] board;
    static int[][] visit;
    static int[][][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        origin = new int[n][n];
        board = new int[n][n];
        visit = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] visit = new int[n][n];
        int islandId = 1;
        //대륙 나누기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(origin[i][j] == 1 && visit[i][j] == 0){
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i,j,islandId});
                    visit[i][j] = 1;
                    board[i][j] = islandId++;
                    while (!q.isEmpty()) {
                        int[] poll = q.poll();
                        int row = poll[0];
                        int col = poll[1];
                        int id = poll[2];
                        for (int d = 0; d < 4; d++) {
                            int nrow = row + dx[d];
                            int ncol = col + dy[d];
                            if(!inRange(nrow,ncol)) continue;
                            if(visit[nrow][ncol] == 1) continue;
                            if(origin[nrow][ncol] == 0) continue;
                            visit[nrow][ncol] = 1;
                            board[nrow][ncol] = id;
                            q.add(new int[]{nrow, ncol, id});
                        }

                    }
                }
            }
        }
//        for (int i = 0; i < n ; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.printf("%d ", board[i][j]);
//            }
//            System.out.println();
//        }
        Queue<int[]> q = new LinkedList<>();
        check = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 0) continue;
                q.add(new int[]{i, j, 0,board[i][j]});
            }
        }
        int answer = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int row = poll[0];
            int col = poll[1];
            int count = poll[2];
            int id = poll[3];
//            System.out.printf("[%d, %d] / count : %d , id : %d\n",row,col,count,id);
            for (int i = 0; i < 4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                if(!inRange(nrow,ncol)) continue;
                if(board[nrow][ncol] != 0 )continue;
                if(check[nrow][ncol][0] != 0 && check[nrow][ncol][1] != id){
//                    System.out.printf("[%d, %d] => [%d, %d]\n",row,col,nrow,ncol);
                    answer = Math.min(answer, count + check[nrow][ncol][0]);
                }else if(check[nrow][ncol][0] == 0){
                    check[nrow][ncol][0] = count + 1;
                    check[nrow][ncol][1]= id;
                    q.add(new int[]{nrow, ncol, count + 1,id});
                }
            }
        }
        System.out.println(answer);
    }

    static boolean inRange(int row, int col) {
        if(row < 0 || row >= n || col < 0 || col >= n)return false;
        return true;
    }
}
