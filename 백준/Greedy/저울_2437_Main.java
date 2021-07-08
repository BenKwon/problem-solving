package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 저울 풀이 방법
 * 일단 추를 오름차순으로 정렬한다.
 * 만약 무게 1짜리 추가 없다면 1이 정답.
 * 무게 1짜리가 있다면 만들 수 있는 최소 무게와 최대 무게를 1로 초기화 시킨다.
 * 그 다음 오름차 순으로 다음 추를 조회 하며 이 전에 만들수 있는 최대 무게가 이전의 최소 무게와 지금 추를 이용해  만들어지는
 * 무게 보다 2이상 크면 중간에 못만드는 무게가 생기므로 그곳이 solution
 *
 *  고려해야할 사항
 *  추 min  max
 *  1  1    1
 *  1  1(2) 2
 *  3  1(4) 5
 *
 *  이 경우 3번째 추와 이전의 만들수있던 최소무게의 합이 4라서 이전의 최대 무게와 2이상 차이 나지만
 *  3번째추 자체가 그 간극을 매꿔주므로 통과해야한다.
 *  즉 다음추 next 와 max + 1이 같다면 2이상 차이가 나도 통과
 */
public class 저울_2437_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] weight = new int[n];
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        for(int i=0 ; i < n ;i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weight);
        int min = weight[0];
        int max = weight[0];
        if(weight[0] != 1){
            System.out.println(1);
            return;
        }
        int solution = 1;
        for(int i = 1 ; i < n ;  i++){
            int next = weight[i];
            if(min + next > max + 1 && next != max +1){
                solution = max + 1;
                break;
            }
            max += next;
            solution = max + 1;
        }

        System.out.println(solution);
        br.close();

    }
}
