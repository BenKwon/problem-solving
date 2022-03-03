package 백준;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main_2668_숫자고르기 {
    static int n;
    static int[] seq;
    static HashSet<Integer> answer = new HashSet<>();
    static HashSet<Integer> visit = new HashSet<>();
    static HashSet<Integer> tmp = new HashSet<>();
    static void dfs(int index){
        int num = seq[index];
        tmp.add(num);
        boolean check = true;
        if(visit.contains(num)){
            for(int i : visit){
                if(!tmp.contains(i)){
                    check = false;
                    break;
                }
            }
            if(check){
                for(int i : visit){
                    answer.add(i);
                }
            }
        }else{
            visit.add(num);
            dfs(num);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        seq = new int[n + 1];
        for (int i = 0; i < n; i++) {
            seq[i + 1] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= n; i++) {
            tmp.clear();
            visit.clear();
            visit.add(i);
            dfs(i);
        }
        bw.write(answer.size() +"\n");
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i : answer){
            pq.add(i);
        }
        while(!pq.isEmpty()){
            bw.write(pq.poll() +"\n");
        }
        bw.flush();
    }
}
