package 백준.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1949_우수마을 {
    static int[] civilNums;
    static int n;
    static int[] visit;
    static ArrayList<ArrayList<Integer>> connectInfo = new ArrayList<>();
    static int[] leafCheck;
    static ArrayList<Integer> leaves = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        civilNums = new int[n + 1];
        leafCheck = new int[n + 1];
        visit = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        connectInfo.add(new ArrayList<>());
        for (int i = 1; i <= n; i++) {
            connectInfo.add(new ArrayList<>());
            civilNums[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int town1 = Integer.parseInt(st.nextToken());
            int town2 = Integer.parseInt(st.nextToken());
            connectInfo.get(town1).add(town2);
            connectInfo.get(town2).add(town1);
            leafCheck[town1]++;
            leafCheck[town2]++;
        }
        int start = 0;
        for (int i = 1; i <= n; i++) {
            if(leafCheck[i] == 1){
                leaves.add(i);
            }else{
                start = i;
            }
        }

        int[] answer = dfs(start);
        System.out.println(Math.max(answer[0],answer[1]));
    }
    static int[] dfs(int town ){
        if(leafCheck[town] == 1){ // leaves node;
            return new int[]{0, civilNums[town]};
        }
        visit[town] = 1;
        ArrayList<Integer> children = connectInfo.get(town);
        int[] result = new int[2];
        result[1] = civilNums[town];
        for (int child : children) {
            if(visit[child] == 1) continue;
            int[] childResult = dfs(child);
            result[1] += childResult[0];
            result[0] += Math.max(childResult[0], childResult[1]);
        }
        return result;
    }

}
