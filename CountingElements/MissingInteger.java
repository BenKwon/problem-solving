package CountingElements;

public class MissingInteger {
    // System.out.println("this is a debug message");
    public static void main(String[] args) {
        int A[] = {-1, 0};
        System.out.println("solution = " + solution(A));
    }

    public static int solution(int[] A) {
        // write your code in Java SE 8
        int length = A.length;
        int max_value = A[0];

        // 배열 A속의 최댓값을 찾는다
        for (int i = 0; i < length; i++) {
            if (max_value < A[i]) {
                max_value = A[i];
            }
        }

        // 만약 최댓값이 양수가 아니면 1을 반환
        if (max_value <= 0) return 1;

        // A에 존재하지 않는 가장 작은 양수를 찾기 위해 max_value+1의 배열 선언
        int store[] = new int[max_value + 1];

        // A에 포함된 원소가 인덱스가 되어 store의 해당 인덱스에 1을 표기하여 A에 존재하는 수라고 표기
        for (int i = 0; i < length; i++) {
            if (A[i] >= 0) {
                store[A[i]] = 1;
            }
        }
        //store을 순환하다가 만약 0인 원소가 있으면 A에 없는 가장 작은 양수이므로 반환
        for (int i = 1; i < max_value; i++) {
            if (store[i] == 0) return i;
        }

        //만약 0을 발견 못했다. (모든값이 연속일 경우) 최댓값 + 1 을 반환
        return max_value + 1;

    }
}
