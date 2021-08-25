package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * dfs로 풀었는데 dfs인자에 ArrayList로 치킨집을 고른 경우의수를 모아서
 * 전해준다. 그런데 이렇게 푸는 방식은 개인적으로 좋다고 생각안함.
 * 해당 문제가 메모리 제한 512MB라서 이렇게 풀어도 된다고 생각해서 풀었지만
 * 대부분의 경우 이런식으로 인자에 경로를 넘겨주면 메모리 초과가 난다.
 * 보통 비트마스킹이나 백트래킹으로 해야한다.
 * 백트래킹 + DFS를 이용한 풀이 -> (https://steady-coding.tistory.com/23)
 */
public class Main_15686_G_치킨배달 {
    static int n,m;
    static int chicken_num, home_num;
    static ArrayList<Coordinate> chicken;
    static ArrayList<Coordinate> home;
    static int[][] graph;
    static int sol = Integer.MAX_VALUE;
    static public class Coordinate{
        int row;
        int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void dfs(int start, int level, ArrayList<Integer> store) {
        if (level == m) {
            int sum = 0;
            for (Coordinate h : home) {
                int tmp = Integer.MAX_VALUE;
                for (int i = 0; i < store.size(); i++) {
                    Coordinate c = chicken.get(store.get(i));
                    tmp = Math.min(tmp, Math.abs(h.row - c.row) + Math.abs(h.col - c.col));
                }
                sum += tmp;
            }
            sol = Math.min(sol, sum);
            return;
        }

        for (int i = start + 1; i < chicken_num; i++) {
            ArrayList<Integer> tmp = (ArrayList<Integer>) store.clone();
            tmp.add(i);
            dfs(i, level+ 1,tmp);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m  = Integer.parseInt(st.nextToken());
        graph = new int[n + 1][n + 1];
        chicken = new ArrayList<>();
        home = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) {//집
                    home.add(new Coordinate(i, j));
                } else if (graph[i][j] == 2) {
                    chicken.add(new Coordinate(i, j));
                }//치킨집
            }
        }
        chicken_num = chicken.size();
        home_num = home.size();
        for (int i = 0; i <= chicken_num - m; i++) {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(i);
            dfs(i, 1, tmp);
        }

        System.out.println(sol);
    }
}
