package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_떡먹는호랑이_2502 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] rice_cake = new int[d + 1];
        rice_cake[d] = k;
        int start =(k / 2) + 1;
        for (int i = start; i < k; i++) {
            boolean solution_find = true;
            rice_cake = new int[d + 1];
            rice_cake[d] = k;
            rice_cake[d - 1] = i;
            for (int j = d - 2; j >= 1; j--) {
                rice_cake[j] = rice_cake[j + 2] - rice_cake[j + 1];
                if(rice_cake[j] > rice_cake[j+1] || rice_cake[j] < 0){
                    solution_find = false;
                }
            }
            if(solution_find){
                break;
            }
        }
        System.out.println(rice_cake[1]);
        System.out.println(rice_cake[2]);

    }
}
