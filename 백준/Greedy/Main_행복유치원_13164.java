package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main_행복유치원_13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] diff = new int[n - 1][3];
        int[] height = new int[n];
        int[] visit = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n - 1; i++) {
            int tmp = height[i + 1] - height[i];
            diff[i][0] = tmp;
            diff[i][1] = i;
            diff[i][2] = i + 1;
        }
        Arrays.sort(diff, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int two_loop = n - k;
        int group = 0;
        int idx = 0;
        int answer = 0;
        while (group < two_loop) {
            int[] d = diff[idx];
            group++;
            answer += d[0];
            idx++;
        }
        System.out.println(answer);
    }
}
