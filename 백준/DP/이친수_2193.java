package 백준.DP;

import java.util.Scanner;

//2193
public class 이친수_2193 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long[] one = new long[N];
        long[] zero = new long[N];
        long result;
        if(N == 1 || N ==2){
            System.out.println(1);
            return;
        }
        one[0] = 1;
        zero[0] = 0;
        one[0] = 0;
        zero[1] = 1;
        for (int i = 2; i < N; i++) {
            one[i] = zero[i - 1];
            zero[i] = one[i - 1] + zero[i - 1];
        }
        result = one[N - 1] + zero[N - 1];
        System.out.println(result);
    }
}
