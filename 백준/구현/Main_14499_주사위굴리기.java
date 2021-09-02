package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14499_주사위굴리기 {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken()); // 행
        m = stoi(st.nextToken()); // 열
        int x = stoi(st.nextToken()); //행
        int y = stoi(st.nextToken()); //열
        int k = stoi(st.nextToken());
        int[][] graph = new int[n][m]; // graph[x][y]

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = stoi(st.nextToken());
            }
        }
        Queue<Integer> command = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            command.add(stoi(st.nextToken()));
        }
        Dice left = new Dice(4, 0);
        Dice right = new Dice(4, 0);
        Dice[] middle = new Dice[4];
        middle[0] = new Dice(2, 0);
        middle[1] = new Dice(1, 0); //middle[1] = 윗면
        middle[2] = new Dice(5, 0);
        middle[3] = new Dice(6, 0); //middle[3] = 바닥면

        while (!command.isEmpty()) {
            int poll = command.poll();
            int n_x = x;
            int n_y = y;
            if (poll == 1) { //동
                n_y += 1;
                if (vaildCheck(n_x, n_y)) {
                    Dice[] tmp = new Dice[4];
                    tmp[0] = middle[0];
                    tmp[1] = left;
                    tmp[2] = middle[2];
                    tmp[3] = right;
                    left = middle[3];
                    right = middle[1];
                    middle = tmp.clone();
                }

            } else if (poll == 2) { //서쪽
                n_y -= 1;
                if (vaildCheck(n_x, n_y)) {
                    Dice[] tmp = new Dice[4];
                    tmp[0] = middle[0];
                    tmp[1] = right;
                    tmp[2] = middle[2];
                    tmp[3] = left;
                    left = middle[1];
                    right = middle[3];
                    middle = tmp.clone();
                }
            } else if (poll == 3) { //북쪽
                n_x -= 1;
                if (vaildCheck(n_x, n_y)) {
                    Dice[] tmp = new Dice[4];
                    tmp[0] = middle[1];
                    tmp[1] = middle[2];
                    tmp[2] = middle[3];
                    tmp[3] = middle[0];
                    middle = tmp.clone();
                }
            } else { //poll ==4 남쪽
                n_x += 1;
                if (vaildCheck(n_x, n_y)) {
                    Dice[] tmp = new Dice[4];
                    tmp[0] = middle[3];
                    tmp[1] = middle[0];
                    tmp[2] = middle[1];
                    tmp[3] = middle[2];
                    middle = tmp.clone();
                }


            }
            if (vaildCheck(n_x, n_y)) {
                if (graph[n_x][n_y] == 0) {
                    graph[n_x][n_y] = middle[3].value;
                } else {
                    middle[3].value = graph[n_x][n_y];
                    graph[n_x][n_y] = 0;
                }
                x = n_x;
                y = n_y;
                System.out.println(middle[1].value);
            }
        }
    }

    public static boolean vaildCheck(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= m) return false;
        return true;
    }

    static class Dice {
        int key;
        int value;

        public Dice(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static int stoi(String a) {
        return Integer.parseInt(a);
    }
}
