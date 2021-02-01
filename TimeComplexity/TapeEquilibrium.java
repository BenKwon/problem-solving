package TimeComplexity;

import java.util.Arrays;

public class TapeEquilibrium {
    public static void main(String[] args) {

        int A[] = {-1,-2,-3,-4};

    }

    public static int solution(int[] A){
        int left_side = 0;
        int sum_array = 0;
        int right_side;
        int min_gap = 0;
        int current_gap = 0;
        for(int i : A){
            sum_array += i;
        }
        right_side = sum_array;
        for(int i = 1; i < A.length ; i++){
            right_side = right_side - A[i-1];
            left_side = left_side + A[i-1];
            current_gap = Math.abs(right_side - left_side);
            if(i == 1){
                min_gap = current_gap;
            }

            if(min_gap > current_gap) {
                min_gap = current_gap;
            }
        }
        return min_gap;
    }

}
