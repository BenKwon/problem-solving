package 백준.그래프이론;

import java.io.*;
import java.util.*;

public class Main_2623_음악프로그래밍 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Integer> tmp = new ArrayList<>();
        HashSet<Integer>[] info_1 = new HashSet[n + 1];
        HashSet<Integer>[] info_2 = new HashSet[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            tmp.clear();
            for (int j = 0; j < k; j++) {
                tmp.add(Integer.parseInt(st.nextToken()));
            }
            for (int p = tmp.size() - 1; p >= 0; p--) {
                if (info_1[tmp.get(p)] == null) info_1[tmp.get(p)] = new HashSet<>();
                for (int l = 0; l < p; l++) {
                    info_1[tmp.get(p)].add(tmp.get(l));
                }
            }
            for (int p = 0; p < tmp.size(); p++) {
                if (info_2[tmp.get(p)] == null) info_2[tmp.get(p)] = new HashSet<>();
                for (int l = p + 1; l < tmp.size(); l++) {
                    info_2[tmp.get(p)].add(tmp.get(l));
                }
            }
        }
        int[] info_one = new int[n + 1]; // i 이전에 완료되어야 하는 가수
        int[] info_two = new int[n + 1]; // i 이후에 완료되어야 하는 가수
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {

            if (info_2[i] != null && info_1[i].size() > 0) {
                info_one[i] = info_1[i].size();
            }else {
                q.add(i);
            }
            if (info_2[i] != null && info_2[i].size() > 0) {
                info_two[i] = info_2[i].size();
            }
            
        }
        ArrayList<Integer> sol = new ArrayList<>();
        while (!q.isEmpty()) {
            int poll = q.poll();
            if (info_2[poll] != null) {
                for (int integer : info_2[poll]) {
                    info_one[integer]--;
                    if(info_one[integer] == 0) q.add(integer);
                }
            }
            sol.add(poll);
        }
        if(sol.size() != n || sol.size() == 0){
            System.out.println(0);
        }else{
            for (int i = 0; i < sol.size(); i++) {
                bw.write(sol.get(i) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
