package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main_2602_돌다리건너기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String paper = br.readLine();
        String devil = br.readLine();
        String angel = br.readLine();
        HashMap<Character, ArrayList<Integer>> map_devil = new HashMap<>();
        HashMap<Character, ArrayList<Integer>> map_angel = new HashMap<>();
        int[] devil_dp = new int[devil.length()];
        int[] angel_dp = new int[angel.length()];
        for (int i = 0; i < devil.length(); i++) {
            char c = devil.charAt(i);
            if (map_devil.get(c) == null) {
                map_devil.put(c, new ArrayList<Integer>());
            }
            map_devil.get(c).add(i);
        }
        for (int j = 0; j < angel.length(); j++) {
            char c = angel.charAt(j);
            if (map_angel.get(c) == null) {
                map_angel.put(c, new ArrayList<>());
            }
            map_angel.get(c).add(j);
        }
        char last_point = paper.charAt(paper.length() - 1);
        if (map_devil.get(last_point) != null) {
            ArrayList<Integer> last_devil = map_devil.get(last_point);
            for (Integer integer : last_devil) {
                devil_dp[integer] = 1;
//            System.out.printf("devil_dp[%d] : %d \n", integer,devil_dp[integer]);
            }
        }
        if (map_angel.get(last_point) != null) {
            ArrayList<Integer> last_angel = map_angel.get(last_point);

            for (Integer integer : last_angel) {
                angel_dp[integer] = 1;
//            System.out.printf("angel_dp[%d] : %d \n", integer,angel_dp[integer]);
            }
        }

        for (int i = paper.length() - 2; i >= 0; i--) { //두루마리 문자를 뒤에서 부터
//            System.out.println("hello");
//            System.out.println("===========================");
            char c = paper.charAt(i);
            char next = paper.charAt(i + 1);
            //devil
            ArrayList<Integer> devils_c = map_devil.get(c);
            ArrayList<Integer> angels_next = map_angel.get(next);
            ArrayList<int[]> tmp_store = new ArrayList<>();
            if(devils_c != null){
                for (int j = 0; j < devils_c.size(); j++) {
                    int c_pos = devils_c.get(j);
                    int sum = 0;
                    if(angels_next != null){
                        for (Integer integer : angels_next) {
                            if (integer > c_pos) {
                                sum += angel_dp[integer];
                            }
                        }
                    }

//                if(sum != 0){
//                    devil_dp[c_pos] = sum;
                    tmp_store.add(new int[]{c_pos, sum});
//                }
//                System.out.printf("devil_dp[%d] : %d \n", c_pos, sum);
//                System.out.println("sum = " + sum);
                }
            }

            //angel
//            System.out.println("+++++++++++++++++++++++++");
            ArrayList<Integer> angels_c = map_angel.get(c);
            ArrayList<Integer> devils_next = map_devil.get(next);
            if(angels_c != null){
                for (int j = 0; j < angels_c.size(); j++) {
                    int c_pos = angels_c.get(j);
//                System.out.println("c = " + c);
//                System.out.println("next = " + next);
//                System.out.println("c_pos = " + c_pos);
                    int sum = 0;
                    if(devils_next != null){
                        for (Integer integer : devils_next) {
//                    System.out.println("integer = " + integer);
                            if (integer > c_pos) {
//                        System.out.println("hello");
//                        System.out.printf("devil_dp[%d] : %d \n",integer,devil_dp[integer]);
                                sum += devil_dp[integer];
                            }
                        }
                    }
//                if(sum != 0){
                    angel_dp[c_pos] = sum;
//                }
//                System.out.printf("devil_dp[%d] : %d \n", c_pos, sum);

//                System.out.println("sum = " + sum);
                }
            }

            for (int[] ints : tmp_store) {
                devil_dp[ints[0]] = ints[1];
            }

        }

        long answer = 0;
        char c = paper.charAt(0);
        if (map_devil.get(c) != null) {
            ArrayList<Integer> first_devil = map_devil.get(c);
            for (Integer integer : first_devil) {
                answer += devil_dp[integer];
            }
        }
        if ( map_angel.get(c)!= null) {
            ArrayList<Integer> first_angel = map_angel.get(c);
            for (Integer integer : first_angel) {
                answer += angel_dp[integer];
            }
        }

        System.out.println(answer);
    }
}
