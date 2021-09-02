package 백준.DP;

import java.io.*;
import java.util.*;

/**
 * 최적해 = BFS
 * 경로 메모제이션, 같은 곳 visit배열이용 재방문 방지로 시간초과 방지
 */
public class Main_13913_숨바꼭질4 {
    static int n, k;
    static int[] visit = new int[100001];
    static int[] way = new int[100001];
    static int sol = 0;

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visit[n] = 1;
        way[n] = n;
        while (!q.isEmpty()) {
            int poll = q.poll();
            int a = poll - 1;
            int b = poll + 1;
            int c = poll * 2;
            if (a >= 0) {
                if (visit[a] == 0) {
                    way[a] = poll;
                    visit[a] = 1;
                    q.add(a);
                }
            }
            if (b <= 100000) {
                if (visit[b] == 0) {
                    way[b] = poll;
                    visit[b] = 1;
                    q.add(b);
                }
            }
            if (c <= 100000) {
                if (visit[c] == 0) {
                    way[c] = poll;
                    visit[c] = 1;
                    q.add(c);
                }

            }
            if (a == k || b == k || c == k) break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        //탐색시작
        bfs();
        Stack<Integer> solution = new Stack<>();
        solution.push(k);
        while (true) {
            int i = way[k];
            if (i == k) break;
            solution.push(i);
            k = i;
        }
        sol = solution.size() - 1;
        bw.write(sol + "\n");
        while (solution.size() > 0) {
            bw.write(solution.pop() + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
