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
        for (int i = 0; i < length; i++) {
            if (max_value < A[i]) {
                max_value = A[i];
            }
        }
        if (max_value <= 0) return 1;

        int store[] = new int[max_value + 1];

        for (int i = 0; i < length; i++) {
            if (A[i] >= 0) {
                store[A[i]] = 1;
            }
        }

        for (int i = 1; i < max_value; i++) {
            if (store[i] == 0) return i;
        }

        return max_value + 1;

    }
}
