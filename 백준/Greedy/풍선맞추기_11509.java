package 백준.Greedy;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class 풍선맞추기_11509 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] height = new int[N];
        int[] arrow_height = new int[1000001];
        for (int i = 0; i < N; i++) {
            height[i] = scanner.nextInt(); //위치
        }
        int sol = 0;
        for(int i = 0 ; i < N ; i++){
            int cur_balloon = height[i];
            if(arrow_height[cur_balloon] > 0){
                arrow_height[cur_balloon-1]++;
                arrow_height[cur_balloon]--;
            }else{
                sol++;
                arrow_height[cur_balloon-1]++;
            }
        }

        System.out.println(sol);
    }
}
