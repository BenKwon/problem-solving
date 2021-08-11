package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_과제_13904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] assignment = new int[7][2];
        int max_deadline = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            assignment[i][0] = Integer.parseInt(st.nextToken());
            assignment[i][1] = Integer.parseInt(st.nextToken());
            max_deadline = Math.max(max_deadline, assignment[i][0]);
        }
        Arrays.sort(assignment, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                } else {
                    return b[0] - a[0];
                }

            }
        });
        int answer = 0;
        int cur_pos = 0;

        for (int i = max_deadline ; i >= 1; i--) {
            System.out.println("======================= ");
            System.out.println("i = " + i);
            System.out.printf("curpos : %d\n", cur_pos);

            int day = cur_pos;
            int max = 0;
            int max_idx = cur_pos - 1;
            int score = 0 ;
            while (assignment[day][0] >= i) {
                if (max < assignment[day][1]) {
                    max = assignment[day][1];
                    max_idx = day;
                }
//                System.out.println("day = " + day);
                day++;
                if(day == n) break;
            }
            cur_pos = cur_pos + 1;
            if(assignment[cur_pos][0] > i+1){

            }
            System.out.printf("max : %d\n", max);
            answer += max;
        }
        System.out.println(answer);

    }
}
