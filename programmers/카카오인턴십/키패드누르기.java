package programmers.카카오인턴십;

import java.util.HashMap;
import java.util.logging.Handler;

/**
 * 2020 카카오 인턴 키패드 누르기
 */
public class 키패드누르기 {
    static HashMap<Integer, int[]> pad_cord = new HashMap<>();

    static String solution(int[] numbers, String hand) {
        int num = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (num == 11) {
                    pad_cord.put(0, new int[]{3, 1});
                    continue;
                }
                pad_cord.put(num++, new int[]{i, j});

            }
        }
        int left_row = 3;
        int left_col = 0;
        int right_row = 3;
        int right_col = 2;
        int main_hand = 1; //오른손잡이 1 왼손잡이 0
        if(hand.compareTo("left") == 0) main_hand =0;
        String answer = "";
        for (int i = 0; i < numbers.length; i++) {
            int next = numbers[i];
            int next_row = pad_cord.get(next)[0];
            int next_col = pad_cord.get(next)[1];
//            System.out.printf("next : %d ,next_row :%d , next_col : %d \n", next ,next_row, next_col);
//            System.out.printf("left : [%d, %d] , right : [%d, %d] \n",left_row,left_col, right_row,right_col);
            if (next == 1 || next == 4 || next == 7) {
                left_row = next_row;
                left_col = next_col;
                answer += "L";
            } else if (next == 3 || next == 6 || next == 9) {
                right_row = next_row;
                right_col = next_col;
                answer += "R";
            } else {
                int diff_left = Math.abs(next_row - left_row) + Math.abs(next_col - left_col);
                int diff_right = Math.abs(next_row - right_row) + Math.abs(next_col - right_col);
                if (diff_left < diff_right) {
                    left_row = next_row;
                    left_col = next_col;
                    answer += "L";
                } else if (diff_left > diff_right) {
                    right_row = next_row;
                    right_col = next_col;
                    answer += "R";
                } else {
                    if(main_hand == 1){
                        right_row = next_row;
                        right_col = next_col;
                        answer += "R";
                    }else{
                        left_row = next_row;
                        left_col = next_col;
                        answer += "L";
                    }
                }
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        int num = 1;
        System.out.println(solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left"));
    }
}
