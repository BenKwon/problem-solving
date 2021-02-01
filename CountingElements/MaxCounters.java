package CountingElements;

import java.util.Arrays;

public class MaxCounters {
    public static void main(String[] args) {
        int A[] = {3,4,4,6,1,4,4,2,6,1};
        A = solution(5, A);
        for (int i : A) {
            System.out.println(i);
        }
    }
    /*
이 문제는 그냥 편하게 풀면 일반 적으로 O(N*M)의 시간 복잡도를 가질것이다. (WORST CASE기준)
따라서 + if A[K] = N + 1 then operation K is max counter.
인 상황에서의 연산을 없앨수 있는 방법을 고안하면 O(N+M)으로 만들수 있을 것이다.

알고리즘 설명
    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4
  (0, 0, 1, 0, 0)
  (0, 0, 1, 1, 0)
  (0, 0, 1, 2, 0)
  (0, 0, 1, 2, 0) A[i] = N+1 --> 이때 max_tmp에 2를 저장해둠 실제로는 모든 카운터들이 2가 되어야하지만 일단 생략
  (3, 0, 1, 2, 0) A[4] = 1 --> 이때 counters[1-1]의 값이 0 < max_tmp이므로 counters[1-1]을 max_tmp로 초기화 시켜주고 1을 더해준다
  (3, 0, 1, 3, 2) A[5] = 4 --? 이때 counters[4-1]의 값이 2로 max_tmp와 같으므로 그냥 counters[4-1]에 1을 더해준다
  (3, 0, 1, 4, 2) A[6] = 4 --? 이때 counters[4-1]의 값이 3으로 max_tmp보다 크므로 그냥 counters[4-1]에 1을 더해준다

그럼 배열 A를 순환 했으므로 시간 복잡도 M(A 배열의 길이)이 소요되었다.
이때 위의 예제에서는 (3, 0, 1, 4, 2)이 최신값이다
즉 해당 배열의 2번째 3번째 값이 최근 A[i] = N+1 일때 MAX_VALUE로 초기화 되지 못했으므로
max_tmp값을 넣어준다.
(3, 2, 2, 4, 2)

결론
이 문제를 쉽게 풀면 단순히 if (A[i] == N + 1) 일때마다 최댓값을 구해서 모든 카운터에 최댓값을
넣어주고 진행하면 된다
하지만 이 경우 worst case때 O(N*M)의 시간 복잡도 이다.
하지만  if (A[i] == N + 1) 일때마다의 최댓값을 max_tmp에 계속해서 최신값으로 갱신해두고
특정 카운터에 카운터를 증가시켜야할때 그 카운터의 값이 max_tmp보다 작으면 max_tmp로 세팅해두고 1증가시킨다.
이렇게만 하면 최후에 증가시켜야될 카운터가 되지 못한 카운터들은 if (A[i] == N + 1) 일때 최댓값으로 갱신
못한셈이 된다. 따라서 마지막에 이러한 카운터들에 max_tmp로 초기화 시켜주면 O(N+M) 시간복잡도를 얻을 수 있다.
     */
    public static int[] solution(int N, int[] A) {
        int[] counters = new int[N];
        int length = A.length;
        int max_value = 0; // {value, index}
        //    int max_index;
        int counters_idx; // 카운터 인덱스
        int max_tmp = 0; // 최근 N+1이였을때의 최댓값을 임시로 저장
        int counter_value;
        //A 배열을 순환
        for (int i = 0; i < length; i++) {
            if (1 <= A[i] && A[i] <= N) {
                counters_idx = A[i] - 1;
                //즉 모든 카운터가 최댓값으로 초기화 되었을때 초기화 되기전에 최댓값이 아니었던 카운터들을
                //최댓값으로 초기화 시켜줌
                if(counters[counters_idx] < max_tmp){
                        counters[counters_idx] = max_tmp;
                }
                //일반적인  카운터 1초 추가
                counters[counters_idx] += 1;
                counter_value = counters[counters_idx];
                //최댓값 갱신
                if (counter_value> max_value) {
                    max_value = counter_value;
                    //            max_index = tmp;
                }
            } else if (A[i] == N + 1) {
                //모든 카운터를 최댓값으로 갱신할 타이밍의 최댓값을 저장
                max_tmp = max_value;
            }
        }

        //모든 카운터를 최근 N+1 최댓값 초기화 타이밍에서의 카운터 최댓값으로 설정
        for (int j = 0; j < N; j++) {
            if (counters[j] < max_tmp) {
                counters[j] = max_tmp;
            }
        }
        return counters;
    }



}
