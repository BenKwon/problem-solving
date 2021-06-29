package 백준.Greedy;

import java.util.Scanner;

public class 전구와스위치_2138 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        String init = scanner.next();
        String result = scanner.next();
        String[] init_str_arr = init.split("");
        String[] result_str_arr = result.split("");
        char[] init_arr_v1 = new char[N + 1];
        char[] init_arr_v2 = new char[N + 1];
        char[] result_arr = new char[N];
        for (int i = 0; i < N; i++) {
            init_arr_v1[i] = init_str_arr[i].charAt(0);
            init_arr_v2[i] = init_str_arr[i].charAt(0);
            result_arr[i] = result_str_arr[i].charAt(0);
        }


        /* 첫번째 전구와 두번째 전구의 상태를 변경 */
        init_arr_v2[0] = switch_zero_one(init_arr_v2[0]);
        init_arr_v2[1] = switch_zero_one(init_arr_v2[1]);


        int v1 = find_solution(N, init_arr_v1, result_arr);
        int v2 = find_solution(N, init_arr_v2, result_arr) + 1;

        if(v1 < 0 && v2 < 0){
            System.out.println(-1);
        }
        else {
            if(v1 < 0){
                System.out.println(v2);
            }
            else if(v2 < 0){
                System.out.println(v1);
            }
            else{
                System.out.println(Math.min(v1,v2));
            }
        }
    }

    /* 양수 -> 솔루션 찾음  음수 -> 솔루션 없음 */
    public static int find_solution(int n, char[] init, char[] result) {
        int swap = 0;
        for (int i = 1; i < n; i++) {
            if (result[i - 1] != init[i - 1]) {
                init[i - 1] = switch_zero_one(init[i-1]);
                init[i] = switch_zero_one(init[i]);
                init[i + 1] = switch_zero_one(init[i+1]);
                swap++;
            }
        }

        if(init[n-1] == result[n-1])return swap;
        else return -2;
    }

    public static char switch_zero_one(char c){
        if(c=='0'){
            return '1';
        }else{
            return '0';
        }

    }

}
