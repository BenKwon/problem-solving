package 백준.트리;

import java.io.*;
import java.util.*;

public class Main_14267_회사문화 {
    static int n, m;
    static int[] seq;
    static int[] parent;
    static ArrayList<ArrayList<Integer>> connectInfo = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        seq = new int[n + 1];
        parent = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i <= n; i++) {
            connectInfo.add(new ArrayList<>());
        }

        st.nextToken(); //루트 노드인 1번 사장 스킵
        parent[1] = 1;
        for (int i = 2; i <= n; i++) {
            int next = Integer.parseInt(st.nextToken());
            connectInfo.get(next).add(i);
            parent[i] = next;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            seq[num] += w;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while (!q.isEmpty()) {
            int poll = q.poll();
            seq[poll] += seq[parent[poll]];
            ArrayList<Integer> connect = connectInfo.get(poll);
            for(int next : connect){
                q.add(next);
            }
        }
        for (int i = 1; i <= n; i++) {
            bw.write(seq[i] +" ");
        }
        bw.flush();
    }
}
