package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11967_불켜기 {
    static int n, m;
    static ArrayList<int[]>[][] switches;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] visit;
    static int[][] light;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        switches = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                switches[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switches[x][y].add(new int[]{a, b});
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 1});
        int count = 1;
        visit = new int[n + 1][n + 1];
        light = new int[n + 1][n + 1];
        visit[1][1] = 1;
        light[1][1] = 1;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int row = poll[0];
            int col = poll[1];
            ArrayList<int[]> connects = switches[row][col];
            for (int[] room : connects) {
                int nrow = room[0];
                int ncol = room[1];
                if(light[nrow][ncol] == 1) continue;
                light[nrow][ncol] = 1;
                count++;
            }

            //상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                if(!inRange(nrow ,ncol) || light[nrow][ncol] == 0 || visit[nrow][ncol] == 1)continue;
                visit[nrow][ncol] = 1;
                q.add(new int[]{nrow, ncol});
            }

            //방금 불킨 곳에서 갈 수 있는곳 탐색
            for (int[] room : connects) {
                int nrow = room[0];
                int ncol = room[1];
                if(!isPossible(nrow,ncol)) continue;
                if(visit[nrow][ncol] == 1) continue;
                visit[nrow][ncol] = 1;
                q.add(new int[]{nrow, ncol});
            }
        }
        System.out.println(count);

    }

    static boolean isPossible(int row, int col) {
        boolean possible = false;
        for (int i = 0; i < 4; i++) {
            int nrow = row + dx[i];
            int ncol = col + dy[i];
            if(!inRange(nrow ,ncol))continue;
            if(visit[nrow][ncol] == 1) possible = true;
        }
        return possible;
    }
    static boolean inRange(int row, int col) {
        return !(row < 1 || row > n || col < 1 || col > n);
    }
}
