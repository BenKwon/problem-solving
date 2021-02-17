package 백준.DP;


import java.io.BufferedWriter;
import java.util.Scanner;

//9461
public class 파도반수열 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int test_num = scanner.nextInt();
        int[] N = new int[test_num];
        int max = 0;
        long[] P;
        for(int i = 0 ; i < test_num; i++){
            N[i] = scanner.nextInt();
            if(max < N[i]){
                max = N[i];
            }
        }
        if(max <= 5){
            P = new long[6];
            P[1] = 1; P[2] =1; P[3]=1; P[4] =2; P[5]=2;
        }
        else{
            P = new long[101];
            P[1] = 1; P[2] =1; P[3]=1; P[4] =2; P[5]=2;
            int[] count = new int[101];
            count[1] =1; count[2] =1; count[3] =1; count[4] =1; count[5] = 2;
            int availabe_min = 1;

            for(int i = 6; i <= max; i++){
                count[i] = 2;
                if (count[availabe_min] <= 0) {
                    availabe_min++;
                }
                P[i] = P[i-1] + P[availabe_min];
                count[availabe_min]--;
                count[i-1]--;
            }
        }

        for(int i : N){
            System.out.println(P[i]);
        }
    }
}
