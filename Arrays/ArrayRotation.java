package Arrays;

public class ArrayRotation {
    public static void main(String[] args) {
        int[] a = {};
        a = solution(a,4);

        return;
    }
    public static int[] solution(int[] A, int K) {
        // write your code in Java SE 8
        int length = A.length;
        int temp;
        if(length==0 || length==1){
            return A;
        }
        for(int i = 0; i < K ;i++){
            temp = A[0];
            A[0] = A[length-1];
            for(int j = length-2;j>=1; j--){
                A[j+1] = A[j];
            }
            A[1] = temp;
        }

        return A;
    }
}
