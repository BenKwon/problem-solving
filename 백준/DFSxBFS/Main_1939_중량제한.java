package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1939_중량제한 {
    static int n, m, start, end;
    static ArrayList<ArrayList<Node>> connect_info = new ArrayList<>();
    static int[] visit;

    static class Node {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            connect_info.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            connect_info.get(a).add(new Node(b,c));
            connect_info.get(b).add(new Node(a,c));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        int low = 1;
        int high = 1000000000;
        int solution = Integer.MIN_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (bfs(mid)) {
                low = mid + 1;
                solution =  mid;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(solution);

    }

    static boolean bfs(int mid) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visit = new int[n + 1];
        visit[start] = 1;

        while(!q.isEmpty()) {
            int temp = q.poll();

            if(temp == end) return true;

            for(int i = 0; i < connect_info.get(temp).size(); i++) {
                if(connect_info.get(temp).get(i).weight >= mid && visit[connect_info.get(temp).get(i).num] == 0) {
                    visit[connect_info.get(temp).get(i).num] = 1;
                    q.offer(connect_info.get(temp).get(i).num);
                }
            }
        }
        return false;
    }
}
