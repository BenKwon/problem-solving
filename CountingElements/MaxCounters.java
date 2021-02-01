package CountingElements;

import java.util.Arrays;

public class MaxCounters {
    public static void main(String[] args) {
        int A[] = {3,4,4,6,1,4,4,2,6,1};
        A = solution2(5, A);
        for (int i : A) {
            System.out.println(i);
        }
    }
    public static int[] solution2(int N, int[] A) {
        int[] counters = new int[N];
        int[] pre_counters = new int[N];
        int length = A.length;
        int max_value = 0; // {value, index}
        //    int max_index;
        int counters_idx;
        int max_tmp = 0;
        int counter_value;
        for (int i = 0; i < length; i++) {
            if (1 <= A[i] && A[i] <= N) {
                counters_idx = A[i] - 1;
                if(counters[counters_idx] < max_tmp){
                        counters[counters_idx] = max_tmp;
                }

                counters[counters_idx] += 1;
                counter_value = counters[counters_idx];
                if (counter_value> max_value) {
                    max_value = counter_value;
                    //            max_index = tmp;
                }
            } else if (A[i] == N + 1) {
                max_tmp = max_value;
            }
        }

        for (int j = 0; j < N; j++) {
            if (counters[j] < max_tmp) {
                counters[j] = max_tmp;
            }
        }
        return counters;
    }

    public static int[] solution(int N, int[] A) {
        int[] counters = new int[N];
        int length = A.length;
        int max_value = 0; // {value, index}
        //    int max_index;
        int tmp;
        int max_tmp = 0;
        int counter_value;
        for (int i = 0; i < length; i++) {
            if (1 <= A[i] && A[i] <= N) {
                tmp = A[i] - 1;
                counters[tmp]++;
         //       System.out.println(counters[4]);
                counter_value = counters[tmp] + max_tmp;
                if (counter_value> max_value) {
                    max_value = counter_value;
                    //            max_index = tmp;
                }
            } else if (A[i] == N + 1) {
                if (max_value != 0) {
                    max_tmp = max_value;
                }
             //   System.out.println("max tmp" + max_tmp);
                max_value = 0;
                counters = new int[N];
            }
        }

        for (int j = 0; j < N; j++) {
            if (counters[j] <= max_value) {
                counters[j] += max_tmp;
            }
        }
        return counters;
    }


}
