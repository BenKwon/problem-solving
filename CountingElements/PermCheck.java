package CountingElements;

public class PermCheck {
    public static void main(String[] args) {
        int A[] = {1,2,3};
        System.out.println("solution(A) = " + solution(A));
    
    }
    
    public static int solution(int A[]){
        int length = A.length;
        int max_value = A[0];

        for (int i = 0; i < length; i++) {
            if (max_value < A[i]) {
                max_value = A[i];
            }
        }

        if(max_value > A.length){
            return 0;
        }

        int[] store = new int[length];

        for (int i = 0; i < length; i++) {
            store[A[i]-1] = 1;
        }

        
        for (int i: store) {
            System.out.println("i = " + i);
            if(i == 0) return 0;
        }

        return 1;
    }
}
