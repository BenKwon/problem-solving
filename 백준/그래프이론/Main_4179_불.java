package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4179_불 {
    static char[][] board;
    static int[][][] visit;
    static int r, c;
    static int srow,scol;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node{
        int row;
        int col;
        int time;
        int type;

        public Node(int row, int cow, int time, int type) {
            this.row = row;
            this.col = cow;
            this.time = time;
            this.type = type;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        Queue<Node> q = new LinkedList<>();
        board = new char[r][c];
        visit = new int[r][c][2];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = str.charAt(j);
                if(board[i][j] == 'J'){
                    srow = i;
                    scol = j;
                }else if(board[i][j] =='F'){
                    q.add(new Node(i,j,0,1));
                    visit[i][j][1] = 1;
                }
            }
        }
        q.add(new Node(srow, scol, 0, 0));
        visit[srow][scol][0] = 1;
        while(!q.isEmpty()){
            Node poll = q.poll();
            int row = poll.row;
            int col = poll.col;
            int time = poll.time;
            int type = poll.type;
            if(type == 0 &&(row == 0 || row == r-1 || col == 0 || col == c-1)){
                System.out.println(time + 1);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                if(!inRange(nrow, ncol) || visit[nrow][ncol][type] == 1) continue;
                if(board[nrow][ncol] == '#') continue;
                if(type == 1){
                    board[nrow][ncol] = 'F';
                }else if(board[nrow][ncol] == 'F') continue;
                visit[nrow][ncol][type] = 1;
                q.add(new Node(nrow, ncol, time + 1, type));
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    static boolean inRange(int row, int col) {
        if(row < 0 || row >= r || col < 0 || col >= c)return false;
        return true;
    }
}
