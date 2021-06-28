package 백준.Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class 등수매기기_2021 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] expect_score = new int[N];

        for (int i = 0; i < N; i++) {
            expect_score[i] = scanner.nextInt();
        }

        Arrays.sort(expect_score);



        long sol = 0;
        for (int j = 0; j < N; j++){
            sol += Math.abs(expect_score[j] - (j+1));
        }

        System.out.println(sol);
    }
}
