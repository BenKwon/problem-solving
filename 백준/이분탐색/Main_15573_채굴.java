package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
public class Main_15573_채굴 {
    static int n, m, k;
    static int[][] board;
    static ArrayList<int[]> start = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(i == 0 || j == 0 ||  j == n -1){
                    start.add(new int[]{i, j});
                }
            }
        }
        solution();
    }
    static boolean inRange(int row, int col) {
        if(row < 0 || row >= n|| col < 0 || col >= m) return false;
        return true;
    }
    static int bfs(int degree){
        Queue<int[]> q = new LinkedList<>();
        int[][] visit = new int[n][m];
        int count  = 0;
        for(int[] o : start){
            int row = o[0];
            int col = o[1];
            if(visit[row][col] == 0 && board[row][col] <= degree){
                visit[row][col] = 1;
                q.add(o);
            }
        }
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int row = poll[0];
            int col = poll[1];
            count++;
            for (int i = 0; i < 4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                if(!inRange(nrow, ncol) || visit[nrow][ncol] == 1 || board[nrow][ncol] > degree) continue;
                visit[nrow][ncol] = 1;
                q.add(new int[]{nrow, ncol});
            }
        }
        return count;
    }

    static int solution(){
        int low = 1;
        int high = 1000_000;
        int min = 1000_000;
        while(low <= high){
            int mid = (low + high)/2;
            int count = bfs(mid);
            if(count < k){ //k개를 캐지 못할 때
                low = mid + 1;
            }else{ //k개를 캘 수 있을때
                high = mid - 1;
                min = mid;
            }
        }
        System.out.println(min);
        return min;
    }
}
