package 백준.그래프이론;

import java.io.*;
import java.util.*;

/**
 *  위상정렬
 */
public class Main_2252_줄세우기 {
    static int n, m;
    static ArrayList<Integer>[] connected_info;
    static HashSet<Integer> start = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        connected_info = new ArrayList[n + 1];
        int[] info = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            start.add(i);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int snode = Integer.parseInt(st.nextToken());
            int nnode = Integer.parseInt(st.nextToken());
            if (connected_info[snode] == null) {
                connected_info[snode] = new ArrayList<>();
            }
            if (snode == nnode) continue;
            connected_info[snode].add(nnode);
            if (start.contains(nnode)) {
                start.remove(nnode);
            }
            info[nnode]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (Integer integer : start) {
            q.add(integer);
        }

        while (!q.isEmpty()) {
            int poll = q.poll();
            ArrayList<Integer> nexts = connected_info[poll];
            if (nexts != null) {
                for (int next : nexts) {
                    info[next]--;
                    if (info[next] == 0) {
                        q.add(next);
                    }
                }
            }
            bw.write(poll + " ");
        }

//        for (Integer integer : start) {
//            System.out.println("start = " + integer);
//            System.out.println();
//        }
        bw.flush();
        bw.close();
        br.close();
    }
}
