package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12851_숨바꼭질2 {
    static int[] visit;

    public static class pos {
        int time;
        int axis;

        public pos(int time, int axis) {
            this.time = time;
            this.axis = axis;
        }

        @Override
        public String toString() {
            return "pos{" +
                    "time=" + time +
                    ", axis=" + axis +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<pos> q = new LinkedList<>();
        q.offer(new pos(0, n));
        visit = new int[100001];
        for (int i = 0; i < visit.length; i++) {
            visit[i] = 10000001;
        }
        int solution = 0;
        int solutionCount = 0;
        visit[n] = 0;
        while (!q.isEmpty()) {
            pos poll = q.poll();
            if (poll.axis == k) {
                solution = poll.time;
                solutionCount++;
//                break;
            }
            if (poll.axis + 1 <= 100000 && (poll.time + 1 <= visit[poll.axis + 1])) {
                visit[poll.axis + 1] = poll.time + 1;
                q.offer(new pos(poll.time + 1, poll.axis + 1));
            }
            if (poll.axis - 1 >= 0 && (poll.time + 1 <= visit[poll.axis - 1])) {
                visit[poll.axis - 1] = poll.time + 1;
                q.offer(new pos(poll.time + 1, poll.axis - 1));
            }
            if (poll.axis * 2 <= 100000 && (poll.time + 1 <= visit[poll.axis * 2])) {
                visit[poll.axis * 2] = poll.time + 1;
                q.offer(new pos(poll.time + 1, poll.axis * 2));
            }

        }
        System.out.println(solution);
        System.out.println(solutionCount);
    }
}
