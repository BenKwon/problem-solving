package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3055_탈출 {
    static int r, c;
    static char[][] graph;
    static int[][] map;
    static class Node{
        int row;
        int col;
        int time;

        public Node(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        graph = new char[r][c];
        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = 100000000;
            }
        }
        int startRow = 0;
        int startCol = 0;
        int endRow = 0;
        int endCol =0;
        Queue<Node> q = new LinkedList<>();
        int[][] visit = new int[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                if(str.charAt(j) == '*'){
                    graph[i][j] = '*';
                    map[i][j] = 0;
                    q.add(new Node(i,j,0));
                    visit[i][j] = 1;
                }else if(str.charAt(j) == 'X'){
                    graph[i][j] = 'X';
                }else if(str.charAt(j) == 'S'){
                    graph[i][j] = 'S';
                    startRow = i;
                    startCol = j;
                }else if(str.charAt(j) == 'D'){
                    graph[i][j] = 'D';
                    endRow = i;
                    endCol = j;
                }
            }
        }

        //MAP 완성하기
        int[] rowMove= {-1,1,0,0};
        int[] colMove={0,0,-1, 1};
        while(!q.isEmpty()){
            Node poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nrow = poll.row + rowMove[i];
                int ncol = poll.col + colMove[i];
                if(nrow < 0 || nrow>=r || ncol<0 ||ncol >= c) continue;
                if(graph[nrow][ncol] == 'D' ) continue;
                if(graph[nrow][ncol] == 'X' ) continue;
                if(visit[nrow][ncol]== 1) continue;
                visit[nrow][ncol] = 1;
                map[nrow][ncol] = poll.time + 1;
                q.add(new Node(nrow,ncol,poll.time+1));
            }
        }

//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) {
//                System.out.printf("%d ", map[i][j]);
//            }
//            System.out.println();
//        }
        q.add(new Node(startRow, startCol, 0));
        visit = new int[r][c];
        visit[startRow][startCol] = 1;
        while(!q.isEmpty()){
            Node poll = q.poll();
//            System.out.printf("row : %d, col :%d . time :%d \n",poll.row,poll.col,poll.time);
            if(poll.row == endRow && poll.col == endCol){
                System.out.println(poll.time);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nrow = poll.row + rowMove[i];
                int ncol = poll.col + colMove[i];
                if(nrow < 0 || nrow>=r || ncol<0 ||ncol >= c) continue;
                if(graph[nrow][ncol] == 'X' ) continue;
                if(visit[nrow][ncol]== 1) continue;
                if(map[nrow][ncol] <= poll.time + 1) continue;
                visit[nrow][ncol] = 1;
                map[nrow][ncol] = poll.time + 1;
                q.add(new Node(nrow,ncol,poll.time+1));
//                System.out.printf("add [%d, %d]\n",nrow,ncol);
            }
        }
        System.out.println("KAKTUS");
    }
}
