package 백준.그래프이론;

import java.io.*;
import java.util.*;
public class Main_4991_로봇청소기 {
    static int w,h;
    static char[][] board;
    static int[][][] visit;
    static int[][] dirtyIds;
    static int[] start;
    static int dirtyNum;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int INF = Integer.MAX_VALUE;
    static class Node{
        int row;
        int col;
        int count;
        int bitmask;

        public Node(int row, int col, int count, int bitmask) {
            this.row = row;
            this.col = col;
            this.count = count;
            this.bitmask = bitmask;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true) {
            StringTokenizer st =new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) break; //테스트 케이스 종료
            dirtyNum = 0;
            dirtyIds = new int[h][w];
            board = new char[h][w];
            start = new int[2];
            for (int i = 0; i < h; i++) {
                String row = br.readLine();
                for (int j = 0; j < w; j++) {
                    board[i][j] = row.charAt(j);
                    if(board[i][j] == 'o'){
                        start[0] = i;
                        start[1] = j;
                    }else if(board[i][j] =='*'){
                        dirtyIds[i][j] = dirtyNum++;
                    }
                }
            }
            visit = new int[h][w][(1 << dirtyNum)];
            bw.write(solution() +"\n");
        }
        bw.flush();
    }

    static boolean inRange(int row, int col) {
        return !(row < 0 || row >= h || col < 0 || col >= w);
    }
    static int solution(){
        int answer = INF;
        int completeMasking = (1 << dirtyNum) - 1;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start[0], start[1], 0, 0));

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Arrays.fill(visit[i][j],INF);
            }
        }
        visit[start[0]][start[1]][0] = 0;

        while(!q.isEmpty()){
            Node poll = q.poll();
            if(poll.bitmask == completeMasking){
                answer = Math.min(answer, poll.count);
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nrow = poll.row + dx[i];
                int ncol = poll.col + dy[i];
                if(!inRange(nrow,ncol) || board[nrow][ncol] == 'x') continue;
                int newMasking = poll.bitmask;
                if(board[nrow][ncol] == '*'){
                    int dirtyId = dirtyIds[nrow][ncol];
                    newMasking |= (1 << dirtyId);
                }
                if(visit[nrow][ncol][newMasking] <= poll.count + 1) continue;
                visit[nrow][ncol][newMasking] = poll.count +  1;
                q.add(new Node(nrow, ncol, poll.count + 1, newMasking));
            }
        }

        return answer == INF ?  -1 : answer;
    }
}
