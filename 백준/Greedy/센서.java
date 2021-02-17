package 백준.Greedy;

import java.util.Arrays;
import java.util.Scanner;

//2212번
public class 센서{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sensor_num = scanner.nextInt(); //센서의 갯수
        int con = scanner.nextInt(); // 기지국 갯수
        int[] sensor = new int[sensor_num]; // 센서별 포지션 배열
        for(int i = 0 ; i < sensor_num ; i++){
            sensor[i] = scanner.nextInt();
        }
        if(con>=sensor_num){
            System.out.println(0);
            return;
        }
        Arrays.sort(sensor);
        int[] gaps = new int[sensor_num];
        for(int i = 1 ; i < sensor_num; i++){
            gaps[i] = sensor[i] - sensor[i - 1];
        }
        int gap_sum = Arrays.stream(gaps).sum();
        Arrays.sort(gaps);
        for(int i = 0; i < con-1; i++){
            gap_sum -= gaps[sensor_num - 1 - i];
        }

        System.out.println(gap_sum);
    }
}
