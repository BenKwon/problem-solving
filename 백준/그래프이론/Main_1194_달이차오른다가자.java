package 백준.그래프이론;

import java.util.*;
import java.io.*;

public class Main_1194_달이차오른다가자 {
    static class pos {
        int row;
        int col;
        int count;
        int bitmask; //bit mask for keys of the doors

        public pos(int row, int col, int count, int bitmask) {
            this.row = row;
            this.col = col;
            this.count = count;
            this.bitmask = bitmask;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;

    static boolean inRange(int row, int col) {
        return !(row < 0 || row >= n || col < 0 || col >= m);
    }

    static boolean isDoor(char c){
        return ('A' <= c && c <= 'F');
    }
    static boolean isKey(char c){
        return ('a' <= c && c <= 'f');
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        char[][] board = new char[n][m];
        int initRow = 0;
        int initCol = 0;
        int destRow = 0;
        int destCol = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == '0') {
                    initRow = i;
                    initCol = j;
                } else if (board[i][j] == '1') {
                    destRow = i;
                    destCol = j;
                }
            }
        }
        int[][][] visit = new int[n][m][(1<<7)-1];
        Queue<pos> q = new LinkedList<>();
        q.add(new pos(initRow, initCol, 0, 0));
        while (!q.isEmpty()) {
            pos poll = q.poll();
            int row = poll.row;
            int col = poll.col;
            int count = poll.count;
            int bitmask = poll.bitmask;
//            System.out.printf("row :%d, col :%d, count : %d\n",row,col, count);
            if(board[row][col] == '1'){
//                System.out.println("정답 : "  +count);
                System.out.println(count);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                if(!inRange(nrow, ncol)) continue;
                if(board[nrow][ncol] =='#') continue;
                char next = board[nrow][ncol];
                if(isDoor(next)){ //열쇠있는지 확인하고 있다면 이동
                    int bitMove= 1<<(next -'A');
                    if((bitmask & bitMove) == 0)continue;
                    if(visit[nrow][ncol][bitmask] == 1) continue;
                    visit[nrow][ncol][bitmask] = 1;
                    q.add(new pos(nrow, ncol, count + 1, bitmask));
                }else if(isKey(next)){ // 키있는 곳이면 주워서 다시 이동
                    int bitMove= 1<<(next -'a');
                    if(visit[nrow][ncol][bitmask | bitMove] == 1) continue;
                    visit[nrow][ncol][bitmask | bitMove] = 1;
                    q.add(new pos(nrow, ncol, count + 1, bitmask | bitMove));
                }else{//빈칸 일때
                    if(visit[nrow][ncol][bitmask] == 1) continue;
                    visit[nrow][ncol][bitmask] = 1;
                    q.add(new pos(nrow, ncol, count + 1, bitmask));
                }
            }
        }
        System.out.println(-1);

    }
}
