package 백준.Greedy;

import java.util.Arrays;
import java.util.Scanner;
//2217번 백준
public class 로프_2217 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] rope = new int[N];
        for (int i = 0 ; i < N ; i++){
            rope[i] = scanner.nextInt();
        }
        Arrays.sort(rope);
        int max = rope[N - 1];
        for(int i = N-2 ; i >= 0 ; i--){
            if(rope[i]*(N-i) > max){
                max = rope[i]*(N-i);
            }
            else{
                //  System.out.println(max);
            }
        }
        System.out.println(max);
        return;
    }
}
