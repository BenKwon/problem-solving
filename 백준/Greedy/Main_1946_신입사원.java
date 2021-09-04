package 백준.Greedy;

import java.io.*;
import java.util.*;

public class Main_1946_신입사원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] worker = new int[n][2];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                worker[i][0] = Integer.parseInt(st.nextToken());
                worker[i][1] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(worker, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0]-o2[0];
                }
            });
            int min = Integer.MAX_VALUE;
            int sol  = 0 ;
            for (int i = 0; i < n; i++) {
                if (worker[i][1] < min) {
                    min = worker[i][1];
                    sol++;
                }
            }
            bw.write(sol + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
