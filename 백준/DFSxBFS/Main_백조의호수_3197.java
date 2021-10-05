package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
import java.io.*;

/**
 * 큐를 2개만든다..
 * 하나는 찾아가는 백조가 방문하는 큐
 * 하나는 water queue...
 * 찾아가는 백조가 모든 물을 탐색하고 탐색하고나면
 * 얼음이 녹고 하루가 지난다.
 * 찾아가는 백조가 만나야하는 백조를 만나면 종료
 *
 * 주변에 한번 얼음을 녹인 물 좌표는 더 이상 얼음을 녹일 필요가 없다..
 * 따라서 물을 녹일때 bfs로 모든 물을 탐색할 필요가 없다.. 녹여서 갓 물이된 애들만 탐색하면서 녹이면 된다.
 *
 */
public class Main_백조의호수_3197 {
    static char[][] graph_char; // 1 : 물 / 2 /빙판 /3 백조
    static int r, c;
    static int start_r, start_c, dest_r, dest_c;
    static ArrayList<pos> init_water = new ArrayList<>();
    static int[][] visit;
    static int[] row_move = {-1, 1, 0, 0};
    static int[] col_move = {0, 0, -1, 1};
    static int sol = Integer.MAX_VALUE;
    static class pos {
        int row;
        int col;
        public pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static boolean validate_pos(int row, int col) {
        if(row < 0 || row >= r || col < 0 ||col>= c) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        graph_char = new char[r][c];
        visit = new int[r][c];

        String str;
        boolean start_set = false;
        for (int i = 0; i < r; i++) {
            str = br.readLine();
            for (int j = 0; j < c; j++) {
                graph_char[i][j] = str.charAt(j);
                if (str.charAt(j) == '.') {
                    init_water.add(new pos(i, j));
                } else if (str.charAt(j) == 'X') {
                } else { // L
                    init_water.add(new pos(i, j));
                    if (!start_set) {
                        start_r = i;
                        start_c = j;
                        start_set = true;
                    } else {
                        dest_r = i;
                        dest_c = j;
                    }
                }
            }
        }
        Queue<pos> lq = new LinkedList<>();
        Queue<pos> wq = new LinkedList<>();
        for (pos pos : init_water) {
            wq.add(pos);
        }
        lq.add(new pos(start_r, start_c));
        visit[start_r][start_c] = 1;
        boolean sol = false;
        int day = 0;
        while(!sol){
            Queue<pos> next_lq = new LinkedList<>();
            while (!lq.isEmpty()) {
                pos poll = lq.poll();
                for (int i = 0; i < 4; i++) {
                    int nrow = poll.row + row_move[i];
                    int ncol = poll.col + col_move[i];
                    if(!validate_pos(nrow,ncol)) continue;
                    if(visit[nrow][ncol] == 1) continue;
                    visit[nrow][ncol] = 1;
                    if(nrow == dest_r && ncol == dest_c){
                        sol = true;
                        break;
                    }
                    if(graph_char[nrow][ncol] == '.'){
                        lq.offer(new pos(nrow, ncol));
                    } else if (graph_char[nrow][ncol] == 'X') {
                        next_lq.offer(new pos(nrow, ncol));
                    }
                }
            }
            if(sol) break;
            lq = next_lq;

            int size = wq.size();
            //물을 녹이자
            for (int i = 0; i < size; i++) {
                pos poll = wq.poll();

                for (int k = 0; k < 4; k++) {
                    int nrow = poll.row + row_move[k];
                    int ncol = poll.col + col_move[k];
                    if(!validate_pos(nrow,ncol)) continue;
                    if (graph_char[nrow][ncol] == 'X') {
                        graph_char[nrow][ncol] = '.';
                        wq.offer(new pos(nrow, ncol));
                    }
                }
            }
            day++;
        }
        System.out.println(day);

    }
}
