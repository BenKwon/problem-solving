package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_택배_8980 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int[][] delivery_info =new int[m][3]; //stat , end ,box갯수
        for(int i = 0 ; i <  m ; i++){
            st = new StringTokenizer(br.readLine());
            delivery_info[i][0] = Integer.parseInt(st.nextToken());
            delivery_info[i][1] = Integer.parseInt(st.nextToken());
            delivery_info[i][2] = Integer.parseInt(st.nextToken());
        }
        int[] village = new int[n];
        Arrays.fill(village, c);
        Arrays.sort(delivery_info, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int answer = 0;
        for (int i = 0; i < m; i++) {
            boolean can_take = true;
            int min = Integer.MAX_VALUE;
            for (int j = delivery_info[i][0]; j < delivery_info[i][1]; j++) {
                min = Math.min(min, village[j]);
            }
            if (delivery_info[i][2] <= min) {
                for (int j = delivery_info[i][0]; j < delivery_info[i][1]; j++) {
                    village[j] -= delivery_info[i][2];
                }
                answer += delivery_info[i][2];
            }else{
                for (int j = delivery_info[i][0]; j < delivery_info[i][1]; j++) {
                    village[j] -= min;
                }
                answer += min;

            }
        }
        System.out.println(answer);

    }
}
