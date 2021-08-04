package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * d날에 호랑이에게 준 떡의 개수가 k개라면
 * d-1날에 호랑이에게 준 떡의 개수는 반드시  k/2+1개가 된다.
 * 따라서 d-1날에 올 수 있는 떡의 갯수는 k/2 + 1개부터 k -1 개의 사이 이다
 * d-1날에 떡의 갯수가 정해지면 나머지 날의 떡의 갯수는 정해지므로
 * 반복문을 통하여 구한다.
 * 다만 만약 a < b < c 라고 할때 a날에오는 떡의 갯수가 b날에 오는 떡의 갯수보다 많아지면 그 경우는 안되는 경우이므로
 * 패스한다.
 */
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
                if(rice_cake[j] > rice_cake[j+1] || rice_cake[j] < 0){ //안되는 경우
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
