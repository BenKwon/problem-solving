package 백준.Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
import java.io.*;
public class Main_1202_보석도둑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] backpacks = new int[k];
        int[][] gemInfo = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            gemInfo[i][0] = weight;
            gemInfo[i][1] = value;
        }
        Arrays.sort(gemInfo, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0]==o2[0]){
                    return o2[1] - o1[1];
                }else{
                    return o1[0] - o2[0];
                }
            }
        });

        for (int i = 0; i < k; i++) {
            backpacks[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(backpacks);

        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        long sol = 0;
        for (int i = 0, last = 0; i < k; i++) {
            while(last < n && gemInfo[last][0] <= backpacks[i] ){
                q.offer(gemInfo[last++][1]);
            }
            if(!q.isEmpty()){
                sol += q.poll();
            }
        }
        System.out.println(sol);
    }
}
