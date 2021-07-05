package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
해당 문제를 처음에는 O(n)으로 풀려고 했으나 너무 복잡해지고 틀렸습니다가 나왔다.
하지만 안풀려서 인터넷을 찾아보니 O(n^2)으로 풀어도 된다고한다.
그 이유는 n 이 10000으로 제곱을 해도 100000000(1억)이다. 시간 제한은 2초이다,
보통 1억번 연산에 1초로 보면되니 n^2을 하여도 된다.

풀이 : 보상을 기준으로 내림차 순을 정렬하고 시간 스케쥴 배열을 만들어서
해당 강연의 데드라인부터 시간 1까지 내려가면서 비어있는 시간을 발견하면 바로 강연을
그때로 스케쥴링한다.
 */
public class 순회강연_2109 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[][] work = new int[n][2];
        int[] time_schedule = new int[10001];
        int max = 0;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            work[i][0] = Integer.parseInt(st.nextToken());
            work[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(work, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o2[0] == o1[0]){
                    return o2[1] - o1[1];
                }
                else{
                    return o2[0] - o1[0];
                }
            }
        });
        int sol = 0;

        for(int i = 0 ; i < n; i++){
            int deadline = work[i][1];
            for(int j = deadline ; j >= 1 ; j--){
                if(time_schedule[j] == 0){
                    time_schedule[j] = 1;
                    sol += work[i][0];
                    break;
                }
            }
        }

        System.out.println(sol);


    }

}
