package 백준;

import java.util.*;
import java.io.*;

public class Main_2448_별찍기 {
    static int[][] blueprint;
    static int N;
    static void recursive(int size, int row, int col){
        if(size == 3) {
            drawBluePrint(row, col);
            return;
        }
        recursive(size/2, row, col);
        recursive(size/2, row + (size/2), col - (size/2));
        recursive(size/2, row + (size/2), col + (size/2));
    }

    static void drawBluePrint( int row, int col) {
//        if (level == N / 3) return;
        int[][] drawGuide = {{-1, -1, 1, -1,-1},
                {-1, 1, -1, 1, -1},
                {1, 1, 1, 1, 1}};
        for (int i = row; i < row + 3; i++) {
            for (int k = col - 2; k <= col + 2; k++) {
                int type = drawGuide[i - row][k - (col - 2)];
                if (type < 0) continue;
                blueprint[i][k] = 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        blueprint = new int[N][5 * (N / 3) + ((N / 3) - 1)];
//        System.out.println(5 * (N / 3) + ((N / 3) - 1));
        recursive(N,0, (5 * (N / 3) + ((N / 3) - 1)) / 2);
//        drawBluePrint( 0, (5 * (N / 3)) / 2);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 5 * (N / 3) + ((N / 3) - 1); j++) {
//                System.out.printf("%d",blueprint[i][j]);
                if(blueprint[i][j] == 1){
                    bw.write("*");
                }else{
                    bw.write(" ");
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
