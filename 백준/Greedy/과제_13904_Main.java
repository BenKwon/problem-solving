package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
public class 과제_13904_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] assignments = new int[n][2];
        int[] days = new int[1001];
        for(int i = 0; i < n ;i++){
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            assignments[i][0] = Integer.parseInt(st.nextToken());
            assignments[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(assignments,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        int total_point = 0;
        for(int i = 0 ; i < n  ; i++){
            int[] assignment = assignments[i];
            int day_idx = assignment[0];
            if(days[day_idx] == 0){
                days[day_idx] = assignment[1];
                total_point += assignment[1];
            }else{
                while(day_idx > 0){
                    if(days[day_idx] == 0){
                        days[day_idx] = assignment[1];
                        total_point += assignment[1];
                        break;
                    }
                    day_idx--;
                }
            }
        }
        System.out.println(total_point);
    }
}
