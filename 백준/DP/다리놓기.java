package 백준.DP;

import java.util.Scanner;

public class 다리놓기 {
    public static int[][] dp_store;
    public static int[][] tests;
    public static int[][] visit;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int test_num = scanner.nextInt();
        int N, M;
        tests = new int[test_num][2];
        for (int i = 0; i < test_num; i++) {
            tests[i][0] = scanner.nextInt();
            tests[i][1] = scanner.nextInt();
        }
        for(int j = 0 ; j < test_num; j++){
            N = tests[j][0]; //1
            M = tests[j][1]; //5
            visit = new int[N][M];
           dp_store = new int[N][M];
            for (int i = 0; i < M; i++) {
                dp_store[N - 1][i] = 1;
            }
            int sum = 0;
            for(int i = 0; i < M; i++){
                sum+= solution(0,i,tests[j]);
            }
            System.out.println(sum);

        }

    }

    //test = [N,M]
    public static int solution(int row_n, int col_m, int test[]) {
        if (dp_store[row_n][col_m] == 0 && visit[row_n][col_m] == 0) {
            for (int i = col_m + 1; i < test[1]; i++) {
                dp_store[row_n][col_m] += solution(row_n+1, i, test);
                visit[row_n][col_m] = 1;
            }
        }

        return dp_store[row_n][col_m];
    }
}
