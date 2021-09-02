package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_뱀_3190 {
    public static int n, k;
    public static int[][] graph;
    public static HashMap<Integer, Character> dir_change_info = new HashMap<>();
    static Queue<int[]> snake = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        ArrayList<int[]> apple_pos = new ArrayList<>();
        graph = new int[n + 1][n + 1];
        StringTokenizer st;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            graph[row][col] = 1;
        }

        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            dir_change_info.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }
        int cur_row = 1;
        int cur_col = 1;
        int head_direction = 1; // 0 : 상 , 1 : 우 , 2 : 하 , 3 : 좌
        int timer = 0;
        snake.add(new int[]{cur_row, cur_col});
        while (true) {
            timer++;
            int n_row;
            int n_col;
            boolean valid_flag;
            if (head_direction == 0) { // 상
                n_row = cur_row - 1;
                n_col = cur_col;
                valid_flag = valid_check(n_row, n_col);
            } else if (head_direction == 2) { //하
                n_row = cur_row + 1;
                n_col = cur_col;
                valid_flag = valid_check(n_row, n_col);
            } else if (head_direction == 3) { //좌
                n_row = cur_row;
                n_col = cur_col - 1;
                valid_flag = valid_check(n_row, n_col);
            } else { //head_direction = 1 우
                n_row = cur_row;
                n_col = cur_col + 1;
                valid_flag = valid_check(n_row, n_col);
            }
            if(valid_flag){
                cur_row = n_row;
                cur_col = n_col;
                snake.add(new int[]{n_row, n_col});
//                System.out.printf("[%d 초] -> head_direction : %d , 현재 좌표 : [%d, %d]\n",timer, head_direction,cur_row,cur_col);
                if(graph[n_row][n_col] == 0){
//                    System.out.println("pop");
                    snake.poll();
                }else{
//                    System.out.println("apple");
                    graph[n_row][n_col] = 0;
                }
//                System.out.printf("뱀 몸 : %d\n",snake.size());

                if(dir_change_info.get(timer) != null){
//                    System.out.println("i am here");
                    char dir = dir_change_info.get(timer);
                    if (dir == 'L') {//왼쪽 으로 90도
                        if(head_direction == 0){
                            head_direction = 3;
                        }else{
                            head_direction -= 1;
                        }
                    }else if(dir == 'D'){//오른쪽으로 90도
                        if(head_direction == 3){
                            head_direction = 0;
                        }else{
                            head_direction += 1;
                        }
                    }
                }
//                System.out.printf("hi : %d\n",head_direction);

            }else break;
        }
        System.out.println(timer);
    }

    public static boolean valid_check(int row, int col) {
        //그래프의 범위를 벗어났을때때
//        System.out.println("========================");
//        System.out.printf("row : %d , col :%d\n",row,col);
        if (row < 1 || row > n || col < 1 || col > n) return false;
        Iterator<int[]> iterator = snake.iterator();
        //만약에 뱀이 자기 몸을 마주췄을떼
        while (iterator.hasNext()) {
            int[] next = iterator.next();
//            System.out.printf("snake : [ %d, %d ] \n", next[0],next[1]);
            if (next[0] == row && next[1] == col) {
                return false;
            }
        }
        return true;
    }
}
