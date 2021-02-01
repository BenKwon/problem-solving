package TimeComplexity;

import java.util.Arrays;

public class PermMissingElem {
    public static void main(String[] args) {
        int A[] = {2};
        System.out.println(solution(A));
    }

    public static int solution(int[] A){
        int B[] = new int[A.length+2];
        B[0] = 1;
        for(int i : A){
            B[i] = 1;
        }
        int k = 0;
        for(int i : B){
            if(i == 0){
                return k;
            }
            k++;
        }
        /*
        Double length = Double.parseDouble(String.valueOf(A.length))+ 1L;
        Double sum = ((1L + length)/2L)*length;
        Double result = sum - (double) Arrays.stream(A).sum();
        return Integer.parseInt(String.valueOf(Math.round(result)));
                 */
        return k;
    }
}
