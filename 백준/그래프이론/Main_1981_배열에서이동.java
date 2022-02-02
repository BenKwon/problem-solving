package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1981_배열에서이동 {
    static int n;
    static int[][] board;
    static int[][] visit;
    static int[] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = 201;
    static int max = 0;

    static boolean inRange(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, board[i][j]);
                min = Math.min(min, board[i][j]);
            }
        }
        int low = 0;
        int high = max;
        int sol = 100000;
        while (low <= high) {
            int mid = (low + high) / 2;
            boolean curMidPass = false;
//            System.out.println("mid = " + mid);
            for (int i = 0; i <= max - mid; i++) {
                int start = i;
                int end = mid + i;
//                System.out.printf("start : %d, end : %d \n",start,end);
                bfs(start, end);
                if (visit[n - 1][n - 1] == 1) {
//                    System.out.println("ㄴ hit");
                    sol = Math.min(sol, mid);
                    curMidPass = true;
                    break;
                }
            }

            if (curMidPass) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
//        System.out.println("-----------------------");
        for (int i = 0; i <= max; i++) {
            int start = i;
            int end = i;
//            System.out.printf("start : %d, end : %d \n",start,end);
            bfs(start, end);
            if (check[start] == 1 && check[end] == 1 && visit[n - 1][n - 1] == 1) {
//                System.out.println("ㄴ hit");
                sol = Math.min(sol, 0);
                break;
            }
        }
        System.out.println(sol);
    }

    static void bfs(int start, int end) {
        visit = new int[n][n];
        check = new int[max + 1];
        Queue<int[]> q = new LinkedList<>();
        if (board[0][0] < start || board[0][0] > end) return;
        q.add(new int[]{0, 0});
        visit[0][0] = 1;
        check[board[0][0]] = 1;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int row = poll[0];
            int col = poll[1];
//            System.out.printf("row : %d, col :%d \n",row,col);
            for (int i = 0; i < 4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                if (!inRange(nrow, ncol) || visit[nrow][ncol] == 1) continue;
                if (board[nrow][ncol] < start || board[nrow][ncol] > end) continue;

                visit[nrow][ncol] = 1;
                check[board[nrow][ncol]] = 1;
//                System.out.printf("check[%d] !\n",board[nrow][ncol]);
                q.add(new int[]{nrow, ncol});
            }
        }
    }

}
