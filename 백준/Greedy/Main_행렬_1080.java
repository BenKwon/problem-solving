package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_행렬_1080 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] init = new int[n][m];
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                init[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                result[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        int sol  = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < m - 2; j++) {
//                System.out.printf("result[%d][%d] : %d , init[%d][%d] : %d \n",i,j,result[i][j],i,j,init[i][j]);
//                System.out.println("============");
                if (result[i][j] != init[i][j]) {
                    sol++;
                    for (int row = 0; row < 3; row++) {
                        for (int col = 0; col < 3; col++) {
                            init[i + row][j + col] = 1 - init[i + row][j + col] ^ 0;
//                            System.out.printf("init[%d][%d] : %d \n ",i,init[i+row][j+col]);

                        }
                    }
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.println();
//            for (int j = 0; j < m; j++) {
//                System.out.printf("%d ",init[i][j]);
//            }
//        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (result[i][j] != init[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(sol);
    }
}
