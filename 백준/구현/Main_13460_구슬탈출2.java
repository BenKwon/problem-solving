package 백준.구현;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13460_구슬탈출2 {
    static char[][] graph;
    static int n, m;
    static int r_row, r_col, b_row, b_col;
    static int[] row_move = new int[]{-1, 1, 0, 0};
    static int[] col_move = new int[]{0, 0, 1, -1};
    static int[][][][] visit = new int[100][100][100][100];

    static class Pos {
        int r_row;
        int r_col;
        int b_row;
        int b_col;
        int time;

        public Pos(int r_row, int r_col, int b_row, int b_col, int time) {
            this.r_row = r_row;
            this.r_col = r_col;
            this.b_row = b_row;
            this.b_col = b_col;
            this.time = time;
        }
    }

    public static void bfs() {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(r_row, r_col, b_row, b_col, 0));
        visit[r_row][r_col][b_row][b_col] = 1;
        boolean solution = false;
        while (!q.isEmpty()) {
            Pos poll = q.poll();
            int r_row = poll.r_row;
            int r_col = poll.r_col;
            int b_row = poll.b_row;
            int b_col = poll.b_col;
            if (poll.time >= 10) break;
//            System.out.println("====================");
//            System.out.printf("r_row : %d , r_col : %d \n", r_row, r_col);
//            System.out.printf("b_row : %d , b_col : %d \n", b_row, b_col);
//            System.out.println("time : " + poll.time);
            for (int i = 0; i < 4; i++) { // 0 : 위 , 1: 아래, 2 : 오른쪽 , 3: 왼쪽
//                System.out.println("======================");
                //빨간구슬과 파란구슬이 동시에 한칸씩 이동 가능할때 까지 이동한다
                boolean red_goal = false;
                boolean blue_goal = false;
                int n_r_row = r_row;
                int n_r_col = r_col;
                int n_b_row = b_row;
                int n_b_col = b_col;
                char[][] tmp_graph = new char[n][m];
                for (int k = 0; k < n; k++) {
                    for (int j = 0; j < m; j++) {
                        tmp_graph[k][j] = graph[k][j];
                    }
                }
                tmp_graph[r_row][r_col] = 'R';
                tmp_graph[b_row][b_col] = 'B';
                //아래 반복문에서 빨간 구슬과 파란구슬이 둘다 안움직이면 끝
                int tmp = 0;
                while (true) {
                    boolean r_move = true;
                    boolean b_move = true;
                    n_r_row += row_move[i];
                    n_r_col += col_move[i];
                    if (validation(n_r_row, n_r_col) && !red_goal) {
                        if (tmp_graph[n_r_row][n_r_col] != '.') {
                            if (tmp_graph[n_r_row][n_r_col] == 'O') {
                                tmp_graph[n_r_row - row_move[i]][n_r_col - col_move[i]] = '.';
                                red_goal = true;
                            } else {
                                r_move = false;
                                n_r_row -= row_move[i];
                                n_r_col -= col_move[i];
                            }
                        } else {
                            tmp_graph[n_r_row - row_move[i]][n_r_col - col_move[i]] = '.';
                            tmp_graph[n_r_row][n_r_col] = 'R';
                        }
                    } else {
                        r_move = false;
                        n_r_row -= row_move[i];
                        n_r_col -= col_move[i];
                    }
                    n_b_row += row_move[i];
                    n_b_col += col_move[i];
                    if (validation(n_b_row, n_b_col) && !blue_goal) {
                        if (tmp_graph[n_b_row][n_b_col] != '.') {
                            if (tmp_graph[n_b_row][n_b_col] == 'O') {
                                tmp_graph[n_b_row - row_move[i]][n_b_col - col_move[i]] = '.';
                                blue_goal = true;
                            } else {
                                b_move = false;
                                n_b_row -= row_move[i];
                                n_b_col -= col_move[i];
                            }
                        } else {
                            tmp_graph[n_b_row - row_move[i]][n_b_col - col_move[i]] = '.';
                            tmp_graph[n_b_row][n_b_col] = 'B';
                        }
                    } else {
                        b_move = false;
                        n_b_row -= row_move[i];
                        n_b_col -= col_move[i];
                    }
                    if (!b_move && !r_move) break;
//                    if (red_goal) {
//                        if (!blue_goal && !validation(n_b_row + row_move[i],n_b_col + row_move[i]) &&  graph[n_b_row + row_move[i]][n_b_col + row_move[i]] == '#') {
//                            solution = true;
//                            System.out.println(poll.time + 1);
//                            break;
//                        }
//                    }
                }
                if (red_goal) {
                    if (!blue_goal) {
                        solution = true;
                        System.out.println(poll.time + 1);
                        break;
                    } else {
                        continue;
                    }
                } else {
                    if (blue_goal) continue;
                }

                if (solution) {
                    break;
                }
                if (visit[n_r_row][n_r_col][n_b_row][n_b_col] == 0) {
//                    System.out.println("i : " + i);
//                    System.out.printf("n_r : [%d , %d] , n_b : [%d , %d] \n", n_r_row, n_r_col, n_b_row, n_b_col);
//                    System.out.println("add queue");
                    q.add(new Pos(n_r_row, n_r_col, n_b_row, n_b_col, poll.time + 1));
                    visit[n_r_row][n_r_col][n_b_row][n_b_col] = 1;
                }
//                for (int k = 0; k < n; k++) {
//                    for (int j = 0; j < m; j++) {
//                        System.out.printf("%c ", tmp_graph[k][j]);
//                    }
//                    System.out.println();
//                }
//                System.out.println("-------------------------------------------");

            }
            if (solution) break;
        }
        if (!solution) System.out.println(-1);

    }

    public static boolean validation(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= m) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n][m];

        r_row = 0;
        r_col = 0;
        b_row = 0;
        b_col = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = str.charAt(j);
                if (graph[i][j] == 'R') {
                    r_row = i;
                    r_col = j;
                    graph[i][j] = '.';
                } else if (graph[i][j] == 'B') {
                    b_row = i;
                    b_col = j;
                    graph[i][j] = '.';
                }
            }
        }
        bfs();

    }
}
