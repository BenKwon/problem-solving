package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_14503_로봇청소기 {
    static int[][] graph;
    static int n, m;
    static int[][] clear;
    static ArrayList<Integer> d_arr = new ArrayList<>();
    static int answer = 0;

    public static int dfs(int row, int col, int d) {
//        System.out.printf("row : %d , col :%d  , d: %d \n", row, col, d);
        if(clear[row][col] != 1){
            clear[row][col] = 1;
            answer++;
        }

        int[] seq;
        boolean should_back = true;
        int[] row_move = new int[4];
        int[] col_move = new int[4];
        int[] dir = new int[4];
        int[] cur_d_idx = new int[4];
        if (d == 0) {
            row_move = new int[]{0, 1, 0, -1};//보는방햑 , 북 ,동 ,남 서,
            col_move = new int[]{-1, 0, 1, 0};
            dir = new int[]{3, 2, 1, 0};
            cur_d_idx = new int[]{0, 1, 2, 3};
        } else if (d == 1) { //동쪽을 바라보고있을때
            row_move = new int[]{-1, 0, 1, 0};//보는방햑 , 동 ,남 서,북
            col_move = new int[]{0, -1, 0, 1};
            dir = new int[]{0, 3, 2, 1};
            cur_d_idx = new int[]{1, 2, 3,0};


        } else if (d == 2) {
            row_move = new int[]{0, -1, 0, 1};//보는방햑 ,,남 서,북 동
            col_move = new int[]{1, 0, -1, 0};
            dir = new int[]{1, 0, 3, 2};
            cur_d_idx = new int[]{2, 3,0,1};

        } else if (d == 3) {
            row_move = new int[]{1, 0, -1, 0};//보는방햑 ,서,북 동,남
            col_move = new int[]{0, 1, 0, -1};
            dir = new int[]{2, 1, 0, 3};
            cur_d_idx = new int[]{3,0,1,2};

        }
        int cur_d = d;
        for (int i = 0; i < 4; i++) {
            int n_row = row + row_move[i];
            int n_col = col + col_move[i];
//            System.out.printf("nrow : %d, ncol :%d\n",n_row, n_col);
            cur_d = cur_d_idx[i];
//            System.out.printf("clear[%d][%d] : %d , graph[%d][%d] : %d \n",n_row,n_col,clear[n_row][n_col],n_row,n_col,graph[n_row][n_col]);
            if (clear[n_row][n_col] == 1 || graph[n_row][n_col] == 1) continue;
            if (n_row < 0 || n_row >= n || n_col < 0 || n_col >= m) {
                continue;
            }
            else {
                should_back = false;
//                if (dir[i] != 3) //왼쪽을 검사했을때 청소가 안되어있으면 왼쪽으로 방향을 틀어서 전진
                dfs(n_row, n_col, dir[i]);
//                else //서쪽 바라보고 왼쪽을 검사했을때 북쪽으로 방향을 틀어서 전진
//                    dfs(n_row, n_col, 0);
                break;
            }
        }
        int[] back_row_move = new int[]{1, 0, -1, 0}; // 북 동 남서
        int[] back_col_move = new int[]{0, -1, 0, 1}; // 북 동 남서

        if (should_back) {
//            System.out.printf("curd : %d\n", cur_d);
            int n_row = row + back_row_move[d];
            int n_col = col + back_col_move[d];
            if (n_row < 0 || n_row >= n || n_col < 0 || n_col >= m || graph[n_row][n_col] == 1) return 0;
            dfs(n_row, n_col, d);
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n ][m ];
        clear = new int[n ][m ];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d);
        System.out.println(answer);

//        for (int i = 0; i < n; i++) {
//            System.out.println();
//            for (int j = 0; j < m; j++) {
//                System.out.printf("%d ", clear[i][j]);
//            }
//        }

    }
}
