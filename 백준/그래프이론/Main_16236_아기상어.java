package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236_아기상어 {
    static int n;
    static int[][] graph;
    static int shark_row = 0 ;
    static int shark_col = 0 ;
    static int[] row_m = {-1, 1, 0, 0};
    static int[] col_m = {0, 0, -1, 1};
    static class node{
        int row;
        int col;
        int time;

        public node(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
    public static boolean vali(int row ,int col){
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 9){
                    shark_row = i;
                    shark_col = j;
                }
            }
        }
        int time = 0;
        int eat = 0;
        int shark_level = 2;

        while(true){
            Queue<node> q = new LinkedList<>();
            q.add(new node(shark_row, shark_col, 0));
            int[][] visit = new int[n][n];
            visit[shark_row][shark_col] = 1;
            int min_len = Integer.MAX_VALUE;
            node max_node = new node(1000, 1000, 0);
//            System.out.println("========================================");
//            System.out.printf("상어위치 [%d %d]\n",shark_row,shark_col);

            while (!q.isEmpty()) {
                node poll = q.poll();
//                System.out.printf("poll -> [ %d , %d ]  , %d s\n", poll.row, poll.col,poll.time);
                for (int i = 0; i < 4; i++) {
                    int nrow = poll.row + row_m[i];
                    int ncol = poll.col + col_m[i];
                    if(!vali(nrow,ncol)) continue;
                    if(visit[nrow][ncol] == 1) continue;
                    if(shark_level < graph[nrow][ncol]) continue;
                    q.add(new node(nrow, ncol, poll.time + 1));
                    visit[nrow][ncol] = 1;
//                    System.out.printf("shark lv : %d, graph[%d][%d] : %d\n",shark_level,nrow,ncol,graph[nrow][ncol]);
                    if (shark_level > graph[nrow][ncol] && graph[nrow][ncol] > 0) {
                        int len = poll.time + 1;
//                        System.out.printf("len :%d\n",len);
                        if(len < min_len){
                            min_len = len;
                            max_node = new node(nrow,ncol, poll.time + 1);
//                            System.out.printf("후보변경  [%d, %d]\n",max_node.row,max_node.col);
                        }else if(len == min_len){
                            if(nrow < max_node.row){
                                min_len = len;
                                max_node = new node(nrow,ncol, poll.time + 1);
//                                System.out.printf("후보변경  [%d, %d]\n",max_node.row,max_node.col);
                            }else if(nrow == max_node.row){
                                if(ncol < max_node.col){
                                    min_len = len;
                                    max_node = new node(nrow,ncol, poll.time + 1);
//                                    System.out.printf("후보변경  [%d, %d]\n",max_node.row,max_node.col);
                                }
                            }
                        }
                    }
                }
            }
            if(min_len == Integer.MAX_VALUE){
                break;
            }else{
//                System.out.printf("shark eat : [%d, %d]\n",max_node.row,max_node.col);
                time += max_node.time;
                eat++;
                if(eat == shark_level){
                    shark_level++;
                    eat = 0;

                }
//                System.out.printf("time : %d\n", time);
                graph[shark_row][shark_col] = 0;
                shark_row = max_node.row;
                shark_col = max_node.col;
                graph[max_node.row][max_node.col] = 0;
            }
        }
        System.out.println(time);
    }
}
