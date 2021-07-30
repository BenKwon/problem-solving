package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_앱_7579 {
    public static int n, m;
    public static int[] task_m, task_c;
    public static int[] visit;
    public static int min = Integer.MAX_VALUE;
    public static int bfs() {
        Queue<task> q = new LinkedList<>();
        q.offer(new task(0, 0, 0));

        while (!q.isEmpty()) {
            task poll = q.poll();
            if (poll.memory_sum >= m) {
//                System.out.printf("memory : %d , cost : %d\n",poll.memory_sum,poll.cost_sum);
//                for(int i = 0 ; i < poll.way.size() ; i++) System.out.printf("%d ", poll.way.get(i));
//                System.out.println();
                min = Math.min(min, poll.cost_sum);
                continue;
            }
//            if (poll.memory_sum > m) continue;
            if(poll.re_visit == 0){
                int m_sum = poll.memory_sum + task_m[poll.index];
                int c_sum = poll.cost_sum + task_c[poll.index];
                task task = new task(poll.index, m_sum, c_sum);
//                task.way = new ArrayList<>(poll.way);
//                task.way.add(poll.index);
                task.re_visit = 1;
                visit[poll.index] = poll.cost_sum;
                q.offer(task);

            }

            for (int t = poll.index + 1; t < n; t++) {
                task task = new task(t, poll.memory_sum, poll.cost_sum);
//                task.way = new ArrayList<>(poll.way);
                visit[t] = poll.cost_sum;
                q.offer(task);

            }
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        task_m = new int[n];
        task_c = new int[n];
        visit = new int[n];
        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            task_m[i] = Integer.parseInt(st.nextToken());
            task_c[i] = Integer.parseInt(st2.nextToken());
        }


        System.out.println(bfs());
    }

    static class task {
        int index;
        int memory_sum;
        int cost_sum;
        int re_visit = 0;
//        List<Integer> way = new ArrayList<>();


        public task(int index, int memory_sum, int cost_sum) {
            this.index = index;
            this.memory_sum = memory_sum;
            this.cost_sum = cost_sum;

        }
    }
}
