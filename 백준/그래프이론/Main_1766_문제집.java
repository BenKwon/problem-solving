package 백준.그래프이론;

import java.util.*;
import java.io.*;

public class Main_1766_문제집 {
    static int n, m;
    static int inDegree[];
    static ArrayList<Integer> solution = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> connectInfo;
    static HashSet<Integer> base = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        connectInfo = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            connectInfo.add(new ArrayList<>());
        }
        inDegree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            base.add(i);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(base.contains(b)) base.remove(b);
            connectInfo.get(a).add(b);
            inDegree[b]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Iterator<Integer> iter = base.stream().iterator();
        while (iter.hasNext()) {
            pq.add(iter.next());
        }
        while(!pq.isEmpty()){
            int poll = pq.poll();
            solution.add(poll);
            List<Integer> nextProblems = connectInfo.get(poll);
            for (int i = 0; i < nextProblems.size(); i++) {
                int next = nextProblems.get(i);
                if(--inDegree[next] == 0){
                    pq.add(next);
                }
            }
        }

        for (int p : solution) {
            System.out.printf("%d ",p);
        }
    }
}
