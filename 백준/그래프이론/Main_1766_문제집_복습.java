package 백준.그래프이론;

import java.io.*;
import java.util.*;

public class Main_1766_문제집_복습 {
    static int n , m;
    static int[] inDegree;
    static HashSet<Integer> set = new HashSet<>();
    static ArrayList<ArrayList<Integer>> connectInfo = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        inDegree = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            connectInfo.add(new ArrayList<>());
            set.add(i);
        }
        set.remove(0);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(set.contains(b)) set.remove(b);
            inDegree[b]++;
            connectInfo.get(a).add(b);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : set) {
            pq.add(s);
        }
        while(!pq.isEmpty()){
            int poll = pq.poll();
            sb.append(poll +" ");
            ArrayList<Integer> connects = connectInfo.get(poll);
            for (int next : connects) {
                if(--inDegree[next] == 0){
                    pq.add(next);
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
